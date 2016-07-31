package kr.kosta.team2.anonymoustab.controller.admin;

import javax.servlet.http.HttpSession;

import kr.kosta.team2.anonymoustab.controller.admin.LoginController;
import kr.kosta.team2.anonymoustab.domain.Member;
import kr.kosta.team2.anonymoustab.service.MemberService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class LoginController {

	private final static Log log = LogFactory.getLog(LoginController.class);
	   
	   @Autowired
	   private MemberService memberService;
	   
	   @RequestMapping(value="login", method=RequestMethod.GET)
	   public ModelAndView loginForm(
			   @RequestParam(value="error_code",required=false) Integer error_code ) {
		   ModelAndView mav = new ModelAndView();
		   Integer err=0;
		   if(error_code!=null)
		   {
		   err=error_code;
		   }
		   if(err==1)
		   {
			   mav.addObject("error_code", error_code);
		   } else if(err == 2){
			   mav.addObject("error_code", error_code);
		   }
	      if (log.isDebugEnabled()) log.debug("login form");
	      
	      
	      
	      mav.setViewName("/login");
	      return mav;
	   }
	   
	   @RequestMapping(value="login", method=RequestMethod.POST)
	   public String login(
	         @RequestParam("email") String email,
	         @RequestParam("password") String password,
	         HttpSession session
	         ){
		   	System.out.println(session);
		   	System.out.println(email);
		   	System.out.println(password);
	         if(memberService.isEqualsPassword(email, password)){
	         
	            Member member = memberService.findMemberByEmail(email);
	            session.setAttribute("loginMemberId", member.getId());
	            session.setAttribute("loginEmail", member.getEmail());
	            session.setAttribute("loginName", member.getName());
	            session.setAttribute("loginPhone", member.getPhone());
	            session.setAttribute("loginDate", member.getBirthDate());
	            
	            System.out.println("로그인성공");
	            return "redirect:/main.do";
	         }
	         else{
	            System.out.println("로그인실패");
	            return "redirect:/login.do?error_code=1";
	         }
	      
	   }
	   
	   @RequestMapping("/logout")
		public String logout(HttpSession session)
		{
			session.invalidate();	
			return "redirect:/login.do";//별거없는데 왜 리다리렉트못하냐..?
		}
	   
	   
	   
}
