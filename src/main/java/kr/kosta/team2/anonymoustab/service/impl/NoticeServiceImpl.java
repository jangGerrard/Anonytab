package kr.kosta.team2.anonymoustab.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kosta.team2.anonymoustab.dao.NoticeDao;
import kr.kosta.team2.anonymoustab.domain.Notice;
import kr.kosta.team2.anonymoustab.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;
	
	public void registerNoticeFriendRequest(Notice notice){
		this.noticeDao.insertNoticeFriendRequest(notice);
	}
	
	public void registerNoticeArticleRegisterToFriend(Notice notice){
		this.noticeDao.insertNoticeArticleRegisterToFriend(notice);
	}
	
	public void registerNoticeLikeToFriend(Notice notice){
		this.noticeDao.insertNoticeLikeToFriend(notice);
	}
	
	public void registerNoticeTagToFriend(Notice notice){
		this.noticeDao.insertNoticeTagToFriend(notice);
	}
	
	public void registerNoticeShareToFriend(Notice notice){
		this.noticeDao.insertNoticeShareToFriend(notice);
	}
	
	public void removeNoticeAcceptedFriendAdd(Map<String,Object> map){
		this.noticeDao.deleteNoticeAcceptedFriendAdd(map);
	}
	
	public Notice findNotice(long id) {
		return this.noticeDao.selectNotice(id);
	}

	public List<Notice> findNotice() {
		return this.noticeDao.selectNotices();
	}
	
	public List<Notice> findNoticeFriendAddById(long id){
		return this.noticeDao.selectNoticeFriendAddById(id);
	}
	
	public List<Notice> findNoticeById(long id){
		return this.noticeDao.selectNoticeById(id);
	}

}
