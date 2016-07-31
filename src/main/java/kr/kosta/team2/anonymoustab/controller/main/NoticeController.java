package kr.kosta.team2.anonymoustab.controller.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.kosta.team2.anonymoustab.domain.Article;
import kr.kosta.team2.anonymoustab.domain.Member;
import kr.kosta.team2.anonymoustab.domain.Notice;
import kr.kosta.team2.anonymoustab.service.ArticleService;
import kr.kosta.team2.anonymoustab.service.MemberService;
import kr.kosta.team2.anonymoustab.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/main/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ArticleService articleService;
	
	
	@RequestMapping
	public ModelAndView noticeForm(){
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/notice/notice");
		
		return mav;
	}
	
	@RequestMapping("/article/list")
	@ResponseBody
	public Map<String,Object> articleNoticeList(
			HttpSession session
			){
		long id = (Long)session.getAttribute("loginMemberId");
		Map<String,Object> map = new HashMap<String,Object>();		
		
		 List<Notice> notices = this.noticeService.findNoticeById(id);		
		 List<Member> members =new ArrayList<Member>();
		 List<Article> articles = new ArrayList<Article>();
		 for(Notice notice : notices)
		 {			 
			Member member = new Member();
			member = memberService.findMember(notice.getFriendId());			
			members.add(member);
			if(notice.getLevel() != 1){ //친구요청이 아닐때. 친구요청일때는 관련된 게시물 no가 없다.
				Article article = articleService.findArticle(notice.getNo());
				articles.add(article);
			}
		 }
		 map.put("notices", notices);
		 map.put("members", members);
		 map.put("articles", articles);

		return map;
	}
}
