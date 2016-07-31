package test.spring;

import java.util.Date;

import kr.kosta.team2.anonymoustab.dao.ArticleDao;
import kr.kosta.team2.anonymoustab.dao.CommentDao;
import kr.kosta.team2.anonymoustab.domain.Article;
import kr.kosta.team2.anonymoustab.domain.Comment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CopyOfArticleDaoUpdateTest {
	public static void main(String[] args){
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
		
		ArticleDao dao = ctx.getBean(ArticleDao.class);

		dao.updateArticleRecommendMinus(4L);
		
	}
}
