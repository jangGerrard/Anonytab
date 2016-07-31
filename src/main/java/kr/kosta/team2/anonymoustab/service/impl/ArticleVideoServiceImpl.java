package kr.kosta.team2.anonymoustab.service.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.ArticleVideoDao;
import kr.kosta.team2.anonymoustab.domain.ArticleVideo;
import kr.kosta.team2.anonymoustab.service.ArticleVideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleVideoServiceImpl implements ArticleVideoService{

	@Autowired
	private ArticleVideoDao articleVideoDao;
	
	public void registerArticleVideo(ArticleVideo articleVideo) {
		this.articleVideoDao.insertArticleVideo(articleVideo);
	}

	public ArticleVideo findArticleVideo(long no) {
		return this.articleVideoDao.selectArticleVideo(no);
	}

	public List<ArticleVideo> findArticleVideos() {
		
		return this.articleVideoDao.selectArticleVideos();
	}

}
