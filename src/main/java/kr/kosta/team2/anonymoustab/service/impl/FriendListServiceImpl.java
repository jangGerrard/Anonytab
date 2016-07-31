package kr.kosta.team2.anonymoustab.service.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.FriendListDao;
import kr.kosta.team2.anonymoustab.domain.FriendList;
import kr.kosta.team2.anonymoustab.service.FriendListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendListServiceImpl implements FriendListService{
	
	@Autowired
	private FriendListDao friendListDao;
	
	public void registerFriendList(FriendList friendList){
		this.friendListDao.insertFriendList(friendList);
	}
	
	public void removeFriendList(FriendList friendList){
		this.friendListDao.deleteFriendList(friendList);
	}
	
	public List<FriendList> findFriends(long id){
		return this.friendListDao.selectFriends(id);
	}

}
