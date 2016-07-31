

import java.util.Date;

import kr.kosta.team2.anonymoustab.dao.ArticleDao;
import kr.kosta.team2.anonymoustab.dao.CommentDao;
import kr.kosta.team2.anonymoustab.domain.Article;
import kr.kosta.team2.anonymoustab.domain.Comment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArticleDaoInsertTest {
	public static void main(String[] args){
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
		
		CommentDao dao = ctx.getBean(CommentDao.class);
		
		Comment comment = new Comment();
		comment.setArticleNo(3L);
		comment.setContents("insert test ");
		comment.setCreateDate(new Date());
		comment.setCreateMemberId(5L);
		
		dao.insertComment(comment);

	}
}
