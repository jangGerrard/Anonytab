package kr.kosta.team2.anonymoustab.dao.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.ArticleImgDao;
import kr.kosta.team2.anonymoustab.dao.impl.mapper.ArticleImgMapper;
import kr.kosta.team2.anonymoustab.domain.ArticleImg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleImgDaoImpl implements ArticleImgDao{

	@Autowired
	private ArticleImgMapper articleImgMapper;
	
	public ArticleImg selectArticleImg(long no) {
		return this.articleImgMapper.selectArticleImg(no);
	}

	public List<ArticleImg> selectArticleImgs() {
		return this.articleImgMapper.selectArticleImgs();
	}
	
	public void insertArticleImg(ArticleImg articleImg){
		this.articleImgMapper.insertArticleImg(articleImg);
	}

}
