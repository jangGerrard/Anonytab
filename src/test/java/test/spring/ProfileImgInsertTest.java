package test.spring;

import kr.kosta.team2.anonymoustab.dao.ProfileImgDao;
import kr.kosta.team2.anonymoustab.domain.ProfileImg;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProfileImgInsertTest {
	
	public static void main(String [] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
		
		ProfileImgDao dao = ctx.getBean(ProfileImgDao.class);
		
		ProfileImg profileImg = new ProfileImg();
		profileImg.setImgPath("c:\\test\\test\\test");
		profileImg.setDelYn(false);		
		
		dao.insertProfileImg(profileImg);
		
		System.out.println("test END");
		
	}
	
	
}
