package kr.kosta.team2.anonymoustab.dao.impl.mapper;

import java.util.List;
import java.util.Map;

import kr.kosta.team2.anonymoustab.domain.Notice;

public interface NoticeMapper {
	
	public void insertNoticeFriendRequest(Notice notice);
	
	public void insertNoticeArticleRegisterToFriend(Notice notice);
	
	public void insertNoticeLikeToFriend(Notice notice);
	
	public void insertNoticeTagToFriend(Notice notice);
	
	public void insertNoticeShareToFriend(Notice notice);
	
	public void deleteNoticeAcceptedFriendAdd(Map<String,Object> map);
	
	public Notice selectNotice(long id);
	
	public List<Notice> selectNotices();
	
	public List<Notice> selectNoticeFriendAddById(long id);
	
	public List<Notice> selectNoticeById(long id);
}
