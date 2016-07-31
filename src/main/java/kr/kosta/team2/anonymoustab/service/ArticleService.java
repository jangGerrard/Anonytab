package kr.kosta.team2.anonymoustab.service;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Article;

public interface ArticleService {
	
	public long registerArticle(Article article);
	
	public void modifyArticleRecommendAdd(Long no);

	public void modifyArticleRecommendMinus(Long no);
	
	public void modifyArticleDelYnTrue(Article article);
	
	public Article findArticle(long id);
	
	public List<Article> findArticles();
	
	public List<Article> findNewsfeedArticles(long id,int limit);
	
	public List<Article> findMyNewsfeedArticles(long id,int limit);
	
	public List<Article> findAnonyFeedArticles(int limit);
	
	public List<Article> findAnonyFeedTopFiveArticles();

}
