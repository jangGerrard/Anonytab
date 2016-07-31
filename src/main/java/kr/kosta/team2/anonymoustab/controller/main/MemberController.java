package kr.kosta.team2.anonymoustab.controller.main;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Member;
import kr.kosta.team2.anonymoustab.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;			
	
	@RequestMapping
	public ModelAndView memberForm(){			
		
		System.out.println("memberForm");
		List<Member> members = memberService.findMembers();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("members", members);
		mav.setViewName("/member/members");				
		return mav;
	}
	
	@RequestMapping("/detail")
	public ModelAndView memberDetailForm(
			@RequestParam("id") long id
			){
		
		System.out.println("memberDetailForm");
		Member member = memberService.findMember(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("member", member);
		mav.setViewName("/member/detail_member");			
		
		return mav;
	}	
	
}
