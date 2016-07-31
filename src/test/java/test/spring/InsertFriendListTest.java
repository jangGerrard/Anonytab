package test.spring;

import kr.kosta.team2.anonymoustab.dao.FriendListDao;
import kr.kosta.team2.anonymoustab.domain.FriendList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InsertFriendListTest {
	public static void main(String [] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
		
		FriendListDao dao = ctx.getBean(FriendListDao.class);
		
		FriendList friendList = new FriendList();
		
		friendList.setId(4L);
		friendList.setFriendId(3L);
		
		
		dao.insertFriendList(friendList);
		
		System.out.println("end");
	}
}
