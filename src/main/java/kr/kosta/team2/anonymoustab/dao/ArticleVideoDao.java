package kr.kosta.team2.anonymoustab.dao;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.ArticleVideo;

public interface ArticleVideoDao {
	
	public void insertArticleVideo(ArticleVideo articleVideo);
	
	public ArticleVideo selectArticleVideo(long no);

	public List<ArticleVideo> selectArticleVideos();	
	
}
