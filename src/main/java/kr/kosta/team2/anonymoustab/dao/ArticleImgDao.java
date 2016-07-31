package kr.kosta.team2.anonymoustab.dao;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.ArticleImg;

public interface ArticleImgDao {
	
	public ArticleImg selectArticleImg(long no);
	
	public List<ArticleImg> selectArticleImgs();
	
	public void insertArticleImg(ArticleImg articleImg);
}
