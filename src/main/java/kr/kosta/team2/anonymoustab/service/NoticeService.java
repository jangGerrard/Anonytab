package kr.kosta.team2.anonymoustab.service;

import java.util.List;
import java.util.Map;

import kr.kosta.team2.anonymoustab.domain.Notice;

public interface NoticeService {
	
	public void registerNoticeFriendRequest(Notice notice);
	
	public void registerNoticeArticleRegisterToFriend(Notice notice);
	
	public void registerNoticeLikeToFriend(Notice notice);
	
	public void registerNoticeTagToFriend(Notice notice);
	
	public void registerNoticeShareToFriend(Notice notice);
	
	public void removeNoticeAcceptedFriendAdd(Map<String,Object> map);
	
	public Notice findNotice(long id);

	public List<Notice> findNotice();
	
	public List<Notice> findNoticeFriendAddById(long id);
	
	public List<Notice> findNoticeById(long id);
}
