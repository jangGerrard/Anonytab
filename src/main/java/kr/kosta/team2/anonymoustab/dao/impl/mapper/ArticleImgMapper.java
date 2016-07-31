package kr.kosta.team2.anonymoustab.dao.impl.mapper;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.ArticleImg;

public interface ArticleImgMapper {
	public ArticleImg selectArticleImg(long no);
	
	public List<ArticleImg> selectArticleImgs();
	
	public void insertArticleImg(ArticleImg articleImg);
}
