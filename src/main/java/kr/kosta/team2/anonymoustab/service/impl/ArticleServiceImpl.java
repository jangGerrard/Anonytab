package kr.kosta.team2.anonymoustab.service.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.ArticleDao;
import kr.kosta.team2.anonymoustab.domain.Article;
import kr.kosta.team2.anonymoustab.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;
	
	public long registerArticle(Article article) {
		return this.articleDao.insertArticle(article);		
	}
	
	public void modifyArticleRecommendAdd(Long no) {
		this.articleDao.updateArticleRecommendAdd(no);
	}

	public void modifyArticleRecommendMinus(Long no) {
		this.articleDao.updateArticleRecommendMinus(no);
	}
	
	public void modifyArticleDelYnTrue(Article article){
		this.articleDao.updateArticleDelYnTrue(article);
	}

	public Article findArticle(long id) {
		return this.articleDao.selectArticle(id);
	}

	public List<Article> findArticles() {
		return this.articleDao.selectArticles();
	}

	public List<Article> findNewsfeedArticles(long id,int limit) {
		return this.articleDao.selectNewsfeedArticles(id,limit);
	}

	public List<Article> findMyNewsfeedArticles(long id,int limit) {
		return this.articleDao.selectMyNewsfeedArticles(id,limit);
	}
	
	public List<Article> findAnonyFeedArticles(int limit){
		return this.articleDao.selectAnonyFeedArticles(limit);
	}
	
	public List<Article> findAnonyFeedTopFiveArticles(){
		return this.articleDao.selectAnonyFeedTopFiveArticles();
	}

}
