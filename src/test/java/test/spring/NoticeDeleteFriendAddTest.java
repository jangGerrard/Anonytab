package test.spring;

import java.util.HashMap;
import java.util.Map;

import kr.kosta.team2.anonymoustab.dao.NoticeDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NoticeDeleteFriendAddTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
		
		NoticeDao dao = ctx.getBean(NoticeDao.class);
		
		Map<String,Object> map = new HashMap<String,Object> ();
		
		map.put("id",1L );
		map.put("friendId", 2L);
		
		dao.deleteNoticeAcceptedFriendAdd(map);
		
		System.out.println("end");
	}

}
