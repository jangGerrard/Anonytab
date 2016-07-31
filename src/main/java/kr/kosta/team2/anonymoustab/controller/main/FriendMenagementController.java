package kr.kosta.team2.anonymoustab.controller.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.kosta.team2.anonymoustab.controller.admin.LoginController;
import kr.kosta.team2.anonymoustab.domain.FriendList;
import kr.kosta.team2.anonymoustab.domain.Member;
import kr.kosta.team2.anonymoustab.domain.Notice;
import kr.kosta.team2.anonymoustab.service.FriendListService;
import kr.kosta.team2.anonymoustab.service.MemberService;
import kr.kosta.team2.anonymoustab.service.NoticeService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/main/friendlist")

@Controller
public class FriendMenagementController {
	private final static Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	private MemberService memberService;
	@Autowired
	private NoticeService noticeService;
	@Autowired 
	private FriendListService friendListService;
	

	@RequestMapping
	public ModelAndView FriendListForm(
			HttpSession session
			) {
		if (log.isDebugEnabled())
			log.debug("friendlist form");
		long id = (Long)session.getAttribute("loginMemberId");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/friendlist");
		mav.addObject("id", id);
		return mav;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Member> getMyFriendList(
			HttpSession session
			) {
		
		//TODO 세션에서 아이디 받아와야겟지
		long id = (Long)session.getAttribute("loginMemberId");
		
		return memberService.findMemberFriendsById(id);

	}
	
	@RequestMapping("/goto/friend")
	@ResponseBody
	public String searchFriends(@RequestParam("info")String info)
	{
		String arr[] = info.split(",");
		String email =arr[0].trim();
				
		Member member = memberService.findMemberByEmail(email);
		
		return "redirect:/main/friendlist.do?id="+member.getId();
		
	}
	
	@RequestMapping("requestedfriend/list")
	@ResponseBody
	public Map<String,Object> requestedFriendNotice(
			HttpSession session
			){
		
		long id = (Long)session.getAttribute("loginMemberId");
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		List<Member> members = new ArrayList<Member>();
		
		List<Notice> notices = noticeService.findNoticeFriendAddById(id);		
		for (Notice notice : notices) {
			Member member = memberService.findMember(notice.getFriendId());
			members.add(member);
		}
		map.put("notices", notices);
		map.put("members" , members);
		
		return map;
	}
	
	@RequestMapping(value = "requestedfriend/accept" , method = RequestMethod.POST)
	@Transactional
	public String acceptRequest(
			@RequestParam("acceptedRequest") List<Long> acceptedRequest,
			@RequestParam("sort") int sort,
			HttpSession session
			){
		long id = (Long)session.getAttribute("loginMemberId");
		System.out.println("loginMemberId "+id);
		if(sort == 1){ //친구등록 + 알람에서 제거.
			for (Long friendId : acceptedRequest) {
				//나의 목록에 추가
				FriendList friendList = new FriendList();
				friendList.setId(id);
				friendList.setFriendId(friendId);
				this.friendListService.registerFriendList(friendList);
				//상대의 목록에 추가
				friendList.setId(friendId);
				friendList.setFriendId(id);
				this.friendListService.registerFriendList(friendList);
				
				Map<String,Object> map  = new HashMap<String,Object> ();
				map.put("id",id);
				map.put("friendId",friendId);
				this.noticeService.removeNoticeAcceptedFriendAdd(map);
			}
		} else if( sort == 2 ){ //알람에서만 제거 하고 친구등록은 하지 않는다.
			for (Long friendId : acceptedRequest) {
				System.out.println("친구등록 안하고 알람만 제거");
				Map<String,Object> map  = new HashMap<String,Object> ();
				map.put("id",id);
				map.put("friendId",friendId);
				this.noticeService.removeNoticeAcceptedFriendAdd(map);
			}
		}
		
		return "redirect:/main/friendlist.do";
	}
	
	@RequestMapping("unregistermember/list")
	@ResponseBody
	public List<Member> unregisteredMember(
			HttpSession session
			){
		long id = (Long)session.getAttribute("loginMemberId");
		
		return  this.memberService.findUnregisteredMember(id);
	}
	
	@RequestMapping(value = "sendFriendRequest" ,method = RequestMethod.GET)
	@Transactional
	public String sendFriendRequest(
			@RequestParam(value="email",required=false) String email,
			HttpSession session			
			){
		if(email != null&& email!=""){
			
			long id = (Long)session.getAttribute("loginMemberId");
			
//			String strArr[] = str.split(",");
//			Member friendMember = memberService.findMemberByName(strArr[0].trim());
			Member friendMember=memberService.findMemberByEmail(email);
			System.out.println("friend Member Name : " + friendMember.getName());
			
			Notice notice = new Notice();
			notice.setId(id);
			notice.setFriendId(friendMember.getId());
			notice.setCreateDate(new Date());			
			noticeService.registerNoticeFriendRequest(notice);
			
			
		} else {
			System.out.println("Entered email is null");
		}
		
		
		return "redirect:/main/friendlist.do";
	}
	
	@RequestMapping(value = "deletefriendlist" , method = RequestMethod.POST)
	@Transactional
	public String deleteFriendList(
			@RequestParam("deleteFriendList") List<Long> deleteFriendList,
			HttpSession session
			){
		long id = (Long)session.getAttribute("loginMemberId");
		
		for (Long friendId : deleteFriendList) {
			FriendList friendList = new FriendList();
			friendList.setId(id);
			friendList.setFriendId(friendId);
			this.friendListService.removeFriendList(friendList);
			
			friendList.setId(friendId);
			friendList.setFriendId(id);
			this.friendListService.removeFriendList(friendList);
		}
		
		return "redirect:/main/friendlist.do";
	}
	
	

}
