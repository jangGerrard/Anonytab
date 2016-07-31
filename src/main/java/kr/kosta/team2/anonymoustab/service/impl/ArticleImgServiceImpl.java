package kr.kosta.team2.anonymoustab.service.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.ArticleImgDao;
import kr.kosta.team2.anonymoustab.domain.ArticleImg;
import kr.kosta.team2.anonymoustab.service.ArticleImgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleImgServiceImpl implements ArticleImgService{

	@Autowired
	private ArticleImgDao articleImgDao;
	
	public ArticleImg findArticleImg(long no) {
		return this.articleImgDao.selectArticleImg(no);
	}

	public List<ArticleImg> findArticleImgs() {
		return this.articleImgDao.selectArticleImgs();
	}

	public void registerArticleImg(ArticleImg articleImg) {
		this.articleImgDao.insertArticleImg(articleImg);
		
	}

}
