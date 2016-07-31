package kr.kosta.team2.anonymoustab.dao;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Comment;

public interface CommentDao {
	public Comment selectComment(long id);
	
	public List<Comment> selectComments();

	public List<Comment> selectCommentByArticleNo(long no);

	public void insertComment(Comment comment);
}
