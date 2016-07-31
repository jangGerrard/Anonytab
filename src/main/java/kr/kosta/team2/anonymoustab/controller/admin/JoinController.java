package kr.kosta.team2.anonymoustab.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import kr.kosta.team2.anonymoustab.domain.Member;
import kr.kosta.team2.anonymoustab.domain.ProfileImg;
import kr.kosta.team2.anonymoustab.service.MemberService;
import kr.kosta.team2.anonymoustab.service.ProfileImgService;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JoinController {

   private final static Log log = LogFactory.getLog(JoinController.class);

   @Autowired
   private MemberService memberService;
   @Autowired
   private ProfileImgService profileImgService;


   @RequestMapping(value = "join", method = RequestMethod.GET)
   public ModelAndView JoinForm() {
      if (log.isDebugEnabled())
         log.debug("join form");
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/join");
      return mav;
   }

   @RequestMapping(value = "join", method = RequestMethod.POST)
   public String join(
         @RequestParam("email") String email,
         @RequestParam("password") String password, 
         @RequestParam("name") String name,
         @RequestParam("phoneNum") String phone,
         @RequestParam("birthDate") String strBirthDate, 
         @RequestParam("file") MultipartFile multipartFile,
         HttpSession session
         
	) {
	  
	  if(memberService.findMemberByEmail(email) != null ){
		  return "redirect:/login.do?error_code=2";
	  }

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date birthDate = null;
      try {
         birthDate = sdf.parse(strBirthDate);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      
            
      Member member = new Member();
      member.setEmail(email);
      member.setBirthDate(birthDate); 
      member.setPassword(password);
      member.setName(name);
      member.setPhone(phone);
      
      long id =  memberService.registerMember(member);
      System.out.println("member Id ::::::::   "+member.getId());
      System.out.println("returned ID :::::    " + id);
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
			ProfileImg profileImg = new ProfileImg();
			profileImg.setId(id);
			profileImg.setImgPath(fileRealPath);
			profileImg.setDelYn(false);
			this.profileImgService.registerProfileImg(profileImg);
		} else {//default koala이미지
			ProfileImg defaultProfileImg = new ProfileImg();
			defaultProfileImg.setId(id);
			defaultProfileImg.setImgPath("D:\\temp\\file\\profile\\Koala.jpg");
			defaultProfileImg.setDelYn(false);
			this.profileImgService.registerProfileImg(defaultProfileImg);
		}
      
      
      
      
      return "redirect:/login.do";
      
         
      
   }

 
}