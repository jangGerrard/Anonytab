package kr.kosta.team2.anonymoustab.dao.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.ArticleVideoDao;
import kr.kosta.team2.anonymoustab.dao.impl.mapper.ArticleVideoMapper;
import kr.kosta.team2.anonymoustab.domain.ArticleVideo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleVideoDaoImpl implements ArticleVideoDao {

	@Autowired
	private ArticleVideoMapper articleVideoMapper;
	
	public void insertArticleVideo(ArticleVideo articleVideo) {
		this.articleVideoMapper.insertArticleVideo(articleVideo);
	}
	
	public ArticleVideo selectArticleVideo(long no) {
		return this.articleVideoMapper.selectArticleVideo(no);
	}

	public List<ArticleVideo> selectArticleVideos() {
		// TODO Auto-generated method stub
		return this.articleVideoMapper.selectArticleVideos();
	}

}