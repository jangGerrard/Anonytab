package kr.kosta.team2.anonymoustab.service.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.CommentDao;
import kr.kosta.team2.anonymoustab.domain.Comment;
import kr.kosta.team2.anonymoustab.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
		
	@Autowired
	private CommentDao commentDao;
	
	public Comment findComment(long id) {
		return this.commentDao.selectComment(id);
	}

	public List<Comment> findComments() {
		return this.commentDao.selectComments();
	}

	public List<Comment> findCommentByArticleNo(long no) {
		return this.commentDao.selectCommentByArticleNo(no);
	}

	public void registerComment(Comment comment) {
		this.commentDao.insertComment(comment);
	}

}
