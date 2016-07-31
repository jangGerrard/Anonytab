package kr.kosta.team2.anonymoustab.dao.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.FriendListDao;
import kr.kosta.team2.anonymoustab.dao.impl.mapper.FriendListMapper;
import kr.kosta.team2.anonymoustab.domain.FriendList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendListDaoImpl implements FriendListDao{

	@Autowired
	private FriendListMapper friendListMapper;
	
	public void insertFriendList(FriendList friendList){
		this.friendListMapper.insertFriendList(friendList);
	}
	
	public void deleteFriendList(FriendList friendList){
		this.friendListMapper.deleteFriendList(friendList);
	}
	
	public List<FriendList> selectFriends(long id){
		return this.friendListMapper.selectFriends(id);
	}
}
