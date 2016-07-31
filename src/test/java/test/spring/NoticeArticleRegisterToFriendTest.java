package test.spring;

import java.util.Date;

import kr.kosta.team2.anonymoustab.dao.NoticeDao;
import kr.kosta.team2.anonymoustab.domain.Notice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NoticeArticleRegisterToFriendTest {

	public static void main(String [] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
		
		NoticeDao dao= ctx.getBean(NoticeDao.class);
		
		Notice notice = new Notice();
		
		notice.setId(1L);
		notice.setFriendId(3L);
		notice.setNo(12L);
		notice.setCreateDate(new Date());
		notice.setLevel(5);
		
		
		dao.insertNoticeArticleRegisterToFriend(notice);
		
		System.out.println("test End");
	}

}
