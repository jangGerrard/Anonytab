package kr.kosta.team2.anonymoustab.dao;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.FriendList;

public interface FriendListDao {
	
	public void insertFriendList(FriendList friendList);
	
	public void deleteFriendList(FriendList friendList);
	
	public List<FriendList> selectFriends(long id);
	
}
