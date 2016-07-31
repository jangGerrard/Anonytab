package kr.kosta.team2.anonymoustab.service;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Comment;

public interface CommentService {
	
	public Comment findComment(long id);

	public List<Comment> findComments();

	public List<Comment> findCommentByArticleNo(long no);

	public void registerComment(Comment comment);

}
