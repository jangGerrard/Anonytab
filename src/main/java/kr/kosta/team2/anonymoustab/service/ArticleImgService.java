package kr.kosta.team2.anonymoustab.service;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.ArticleImg;

public interface ArticleImgService {
	
	public ArticleImg findArticleImg(long no);

	public List<ArticleImg> findArticleImgs();
	
	public void registerArticleImg(ArticleImg articleImg);
}
