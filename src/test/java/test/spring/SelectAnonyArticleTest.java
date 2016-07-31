//package test.spring;
//
//import java.util.List;
//
//import kr.kosta.team2.anonymoustab.dao.ArticleDao;
//import kr.kosta.team2.anonymoustab.domain.Article;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class SelectAnonyArticleTest {
//
//	public static void main(String[] args) {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//		
//		ArticleDao dao = ctx.getBean(ArticleDao.class);
//		
////		List<Article> articles = dao.selectAnonyFeedArticles();
//		
//		for (Article article : articles) {
//			System.out.println(article.getNo());
//		}
//	}
//
//}
