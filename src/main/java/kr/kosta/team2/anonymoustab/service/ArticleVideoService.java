package kr.kosta.team2.anonymoustab.service;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.ArticleVideo;

public interface ArticleVideoService {
	
	public void registerArticleVideo(ArticleVideo articleVideo);
	
	public ArticleVideo findArticleVideo(long no);

	public List<ArticleVideo> findArticleVideos();
}
