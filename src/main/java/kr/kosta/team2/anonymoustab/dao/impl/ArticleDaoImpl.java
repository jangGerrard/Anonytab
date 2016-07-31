package kr.kosta.team2.anonymoustab.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.kosta.team2.anonymoustab.dao.ArticleDao;
import kr.kosta.team2.anonymoustab.dao.impl.mapper.ArticleMapper;
import kr.kosta.team2.anonymoustab.domain.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ArticleDaoImpl implements ArticleDao{

	@Autowired
	private ArticleMapper articleMapper;
	
	public long insertArticle(Article article) {
		this.articleMapper.insertArticle(article);
		return article.getNo();
	}
	
	public void updateArticleRecommendAdd(Long no) {
		this.articleMapper.updateArticleRecommendAdd(no);
	}

	public void updateArticleRecommendMinus(Long no) {
		this.articleMapper.updateArticleRecommendMinus(no);
	}
	
	public void updateArticleDelYnTrue(Article article){
		this.articleMapper.updateArticleDelYnTrue(article);
	}
	
	public Article selectArticle(long id) {
		return this.articleMapper.selectArticle(id);
	}

	public List<Article> selectArticles() {
		return this.articleMapper.selectArticles();
	}

	public List<Article> selectNewsfeedArticles(long id,int limit) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("limit", limit);
		
		return this.articleMapper.selectNewsfeedArticles(map);
	}

	public List<Article> selectMyNewsfeedArticles(long id,int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("limit", limit);
		
		
		return this.articleMapper.selectMyNewsfeedArticles(map);
	}
	
	public List<Article> selectAnonyFeedArticles(int limit){
		return this.articleMapper.selectAnonyFeedArticles(limit);
	}
	
	public List<Article> selectAnonyFeedTopFiveArticles(){
		return this.articleMapper.selectAnonyFeedTopFiveArticles();
	}
	
}
