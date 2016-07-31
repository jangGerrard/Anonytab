import kr.kosta.team2.anonymoustab.dao.ArticleVideoDao;
import kr.kosta.team2.anonymoustab.domain.ArticleVideo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ArticleVideoDaoTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
		
		ArticleVideoDao dao = ctx.getBean(ArticleVideoDao.class);
		
		ArticleVideo articleVideo = new ArticleVideo();
//		articleVideo.setNo(96L);
//		articleVideo.setVideoPath("c:\\temp\\file\\video\\oceans-clip.mp4");		
//		
//		dao.insertArticleVideo(articleVideo);
//		
//		System.out.println("end..");
		
		articleVideo = dao.selectArticleVideo(96L);
		
		System.out.println("articleVideo no : " + articleVideo.getNo());
		System.out.println("articleVideo path : " + articleVideo.getVideoPath());
	}

}
