package test.spring;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.CommentDao;
import kr.kosta.team2.anonymoustab.domain.Comment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CommentselectCommentByArticleNoTest {

	public static void main(String []args)
	{
	
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
		
		CommentDao commentDao=ctx.getBean(CommentDao.class);
		
		List<Comment> comments =commentDao.selectCommentByArticleNo(1L);
		
		for(Comment comment :comments)
		{
			System.out.println(comment.getContents());
		}
		

		
	}
	
}
