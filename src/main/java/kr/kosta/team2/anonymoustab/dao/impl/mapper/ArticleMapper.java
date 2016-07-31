package kr.kosta.team2.anonymoustab.dao.impl.mapper;

import java.util.List;
import java.util.Map;

import kr.kosta.team2.anonymoustab.domain.Article;

public interface ArticleMapper {
	
	public void insertArticle(Article article);
	
	public void updateArticleRecommendAdd(Long no);

	public void updateArticleRecommendMinus(Long no);
	
	public void updateArticleDelYnTrue(Article article);
	
	public Article selectArticle(long id);
	
	public List<Article> selectArticles();
	
	public List<Article> selectNewsfeedArticles(Map<String,Object> map);
			
	public List<Article> selectMyNewsfeedArticles(Map<String,Object> map);
	
	public List<Article> selectAnonyFeedArticles(int limit);
	
	public List<Article> selectAnonyFeedTopFiveArticles();
}
