package kr.kosta.team2.anonymoustab.controller.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.kosta.team2.anonymoustab.domain.Article;
import kr.kosta.team2.anonymoustab.domain.Member;
import kr.kosta.team2.anonymoustab.domain.Notice;
import kr.kosta.team2.anonymoustab.domain.ProfileImg;
import kr.kosta.team2.anonymoustab.service.ArticleService;
import kr.kosta.team2.anonymoustab.service.MemberService;
import kr.kosta.team2.anonymoustab.service.NoticeService;
import kr.kosta.team2.anonymoustab.service.ProfileImgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/main/mypage")
public class MyPageController {
	

		
	@Autowired
	private ArticleService articleService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ProfileImgService profileImgService;
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping
	public ModelAndView myPageForm(@RequestParam("id") Long id, HttpSession session)
	{
		System.out.println("\n\nmyPageId = "+id+"\n\n\n");
		long loginId = (Long)session.getAttribute("loginMemberId");
		Member member = memberService.findMember(id);

		ModelAndView mav= new ModelAndView();
		mav.setViewName("/member/mypage");
		mav.addObject("member",member);
		mav.addObject("loginId",loginId);
		if(id!=null)
		{
		mav.addObject("myPageId",id);
		}

		return mav;
	}
	@RequestMapping("img/download")
	public void profileDownload(
			@RequestParam(value="id",required=false) Long id,
			HttpServletResponse response
			){
		
		if(id==null)
		{//널=였을때 어노니 였을떄
			id=1L;
		}
		
		
		ProfileImg profileImg = profileImgService.findProfileImg(id);
		
		String filePath = profileImg.getImgPath();
		File file = new File(filePath);
		response.setContentLength((int)file.length());
		
		InputStream is = null;
		OutputStream os = null;
		
		try{
			is = new FileInputStream(file);
			os = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			
			while(is.read(buffer) >= 0){
				os.write(buffer);
			}			
			os.flush();
		} catch(Exception e) {
//			e.printStackTrace();
			System.out.println("Profile 경로에 지정된 사진이 없습니다. \"Member ID\" : " +id );
		} finally {
			if(is != null) try {is.close(); } catch (Exception e){ }
			if(os != null) try {os.close(); } catch (Exception e){ }
		}
	}
	
	
	@RequestMapping("/requestfriend")
	public String requestFriend(
			HttpSession session,
			@RequestParam("id") long friendId
			){		
		long myId = (Long)session.getAttribute("loginMemberId");
		
		Notice notice = new Notice();
		notice.setId(myId);
		notice.setFriendId(friendId);
		notice.setCreateDate(new Date());			
		noticeService.registerNoticeFriendRequest(notice);
		
		Long longId = (Long)friendId; String strId = longId.toString();
		
		return "redirect:/main/mypage.do?id="+strId;
	}
	
	@RequestMapping(value = "modifymyinfo" , method = RequestMethod.GET)
	public ModelAndView modifyMyinfoForm(
			HttpSession session
			){
		long id = (Long)session.getAttribute("loginMemberId");
		Member member = memberService.findMember(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/modify_info");
		mav.addObject("member", member);
		
		return mav;
	}
	
	@RequestMapping(value = "modifymyinfo" , method = RequestMethod.POST)
	@Transactional
	public String modifyMyinfo(
			HttpSession session,
			@RequestParam("email") String email,
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("phone") String phone,
			@RequestParam("birthDate") String strBirthDate			
			){
		long id = (Long)session.getAttribute("loginMemberId");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = null;
	      try {
	         birthDate = sdf.parse(strBirthDate);
	      } catch (ParseException e) {
	         e.printStackTrace();
	      }
	      
		Member member = new Member();
		member.setId(id);
		member.setEmail(email);
		member.setName(name);
		member.setPassword(password);
		member.setPhone(phone);
		member.setBirthDate(birthDate);
		
		memberService.modifyMember(member);
		
		Long longId = (Long)id; String strId = longId.toString();
		
		return "redirect:/main/mypage.do?id="+strId;
	}
	
	@RequestMapping("/friends")
	@ResponseBody
	public Map<String,Object> jsonFriendsImg(HttpSession session,@RequestParam("myPageId") Long myPageId)
	{
		long id = (Long)session.getAttribute("loginMemberId");
		List<Member> members =memberService.findMemberFriendsById(myPageId);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("members", members);
	
		
		return map;
	}
	
	@RequestMapping("/modifyImg")
	@Transactional
	public String modifyProfileImg(
			@RequestParam("newProfileImg") MultipartFile multipartFile,
			HttpSession session
			){
			Long id = (Long)session.getAttribute("loginMemberId");
		
		
		
	      if(multipartFile.getSize() > 0){
				String fileRepo = "c:\\temp\\file\\profile\\";
				String fileRealPath = "c:\\temp\\file\\profile\\" + multipartFile.getOriginalFilename();
				
				InputStream is = null;
				OutputStream os = null;
				try {
					//업로드된 파일 스트림
					is = multipartFile.getInputStream();
					//서버에 저장할 파일 스트림
					File file = new File(fileRepo + multipartFile.getOriginalFilename());
					os = new FileOutputStream(file);
					
					//업로드된 인풋스트림에서 byte만큼읽어서 저장시킬 파일 아웃풋스트림에 데이터 전송
					byte[] buffer = new byte[4096];
					while(is.read(buffer) >= 0){
						os.write(buffer);
					}
					os.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally{
					if(is != null) try {is.close(); } catch (Exception e){ }
					if(os != null) try {os.close(); } catch (Exception e){ }
				}
				ProfileImg profileImg = profileImgService.findProfileImg(id);
				profileImg.setImgPath(fileRealPath);
				profileImg.setDelYn(false);
				this.profileImgService.modifyProfileImg(profileImg);
			} else {//default koala이미지
				ProfileImg defaultProfileImg = profileImgService.findProfileImg(id);
				defaultProfileImg.setImgPath("c:\\temp\\file\\profile\\Koala.jpg");
				defaultProfileImg.setDelYn(false);
				this.profileImgService.modifyProfileImg(defaultProfileImg);
			}
	      
		
		return "redirect:/main/mypage.do?id="+ id.toString();
	}

}
