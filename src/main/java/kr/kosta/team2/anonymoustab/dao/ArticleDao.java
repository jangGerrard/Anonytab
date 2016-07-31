package kr.kosta.team2.anonymoustab.dao;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Article;

public interface ArticleDao {
	
	public long insertArticle(Article article);
	
	public void updateArticleRecommendAdd(Long no);

	public void updateArticleRecommendMinus(Long no);
	
	public void updateArticleDelYnTrue(Article article);
	
	public Article selectArticle(long id);

	public List<Article> selectArticles();	
	
	public List<Article> selectNewsfeedArticles(long id,int limit);

	public List<Article> selectMyNewsfeedArticles(long id,int limit);
	
	public List<Article> selectAnonyFeedArticles(int limit);
	
	public List<Article> selectAnonyFeedTopFiveArticles();
	
}
		