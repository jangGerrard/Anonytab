package test.spring;

import kr.kosta.team2.anonymoustab.dao.ArticleImgDao;
import kr.kosta.team2.anonymoustab.domain.ArticleImg;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArticleImgInsertTest {
		
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
		
		ArticleImgDao dao = ctx.getBean(ArticleImgDao.class);
		
		ArticleImg articleImg = new ArticleImg ();
		
		articleImg.setDelYn(false);
		articleImg.setImgPath("c:\\delete\\this\\record");
		articleImg.setNo(1L);		
		
		dao.insertArticleImg(articleImg);
		
		System.out.println("test 끝");
	}
}
