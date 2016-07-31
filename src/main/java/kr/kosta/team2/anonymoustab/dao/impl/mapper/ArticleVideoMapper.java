package kr.kosta.team2.anonymoustab.dao.impl.mapper;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.ArticleVideo;

public interface ArticleVideoMapper {
	
	public void insertArticleVideo(ArticleVideo articleVideo);
	
	public ArticleVideo selectArticleVideo(long no);

	public List<ArticleVideo> selectArticleVideos();	
	
}
