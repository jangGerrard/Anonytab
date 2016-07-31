package kr.kosta.team2.anonymoustab.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.kosta.team2.anonymoustab.dao.NoticeDao;
import kr.kosta.team2.anonymoustab.dao.impl.mapper.NoticeMapper;
import kr.kosta.team2.anonymoustab.domain.Notice;

@Repository
public class NoticleDaoImpl implements NoticeDao {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	public void insertNoticeFriendRequest(Notice notice){
		this.noticeMapper.insertNoticeFriendRequest(notice);
	}
	
	public void insertNoticeArticleRegisterToFriend(Notice notice){
		this.noticeMapper.insertNoticeArticleRegisterToFriend(notice);
	}
	
	public void insertNoticeLikeToFriend(Notice notice){
		this.noticeMapper.insertNoticeLikeToFriend(notice);
	}
	
	public void insertNoticeTagToFriend(Notice notice){
		this.noticeMapper.insertNoticeTagToFriend(notice);
	}
	
	public void insertNoticeShareToFriend(Notice notice){
		this.noticeMapper.insertNoticeShareToFriend(notice);
	}
	
	public void deleteNoticeAcceptedFriendAdd(Map<String,Object> map){
		this.noticeMapper.deleteNoticeAcceptedFriendAdd(map);
	}
	
	public Notice selectNotice(long id) {
		return this.noticeMapper.selectNotice(id);
	}

	public List<Notice> selectNotices() {
		return this.noticeMapper.selectNotices();
	}
	
	public List<Notice> selectNoticeFriendAddById(long id){
		return this.noticeMapper.selectNoticeFriendAddById(id);
	}
	
	public List<Notice> selectNoticeById(long id){
		return this.noticeMapper.selectNoticeById(id);
	}

}
