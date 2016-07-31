package kr.kosta.team2.anonymoustab.dao.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.CommentDao;
import kr.kosta.team2.anonymoustab.dao.impl.mapper.CommentMapper;
import kr.kosta.team2.anonymoustab.domain.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CommentDaoImpl implements CommentDao{
	
	@Autowired
	private CommentMapper commentMapper;

	public Comment selectComment(long id) {
		return this.commentMapper.selectComment(id);
	}

	public List<Comment> selectComments() {
		return this.commentMapper.selectComments();
	}

	public List<Comment> selectCommentByArticleNo(long no) {
		// TODO Auto-generated method stub
		return this.commentMapper.selectCommentByArticleNo(no);
	}

	public void insertComment(Comment comment) {
		this.commentMapper.insertComment(comment);
	}
	
	
	
}
