package kr.kosta.team2.anonymoustab.dao.impl.mapper;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.FriendList;

public interface FriendListMapper {
	
	public void insertFriendList(FriendList friendList);
	
	public void deleteFriendList(FriendList friendList);
	
	public List<FriendList> selectFriends(long id);
}
