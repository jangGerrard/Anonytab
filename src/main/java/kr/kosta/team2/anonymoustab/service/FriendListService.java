package kr.kosta.team2.anonymoustab.service;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.FriendList;

public interface FriendListService {
	
	public void registerFriendList(FriendList friendList);
	
	public void removeFriendList(FriendList friendList);
	
	public List<FriendList> findFriends(long id);
}
