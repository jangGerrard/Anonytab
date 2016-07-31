package kr.kosta.team2.anonymoustab.controller.android;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.kosta.team2.anonymoustab.domain.Article;
import kr.kosta.team2.anonymoustab.service.ArticleService;
import kr.kosta.team2.anonymoustab.service.CommentService;
import kr.kosta.team2.anonymoustab.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@RequestMapping("/android")
@Controller
public class androidController {

	@Autowired
	ArticleService articleService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	CommentService commentService;
	
	
	
	@RequestMapping("/json")
	@ResponseBody
	public Map<String,Object> androidJson()
	{
		Map<String,Object> map = new HashMap<String,Object>();
		List<Article> articles=articleService.findArticles();
		map.put("articles",articles);
		
		map.put("members",memberService.findMembers());
		
		map.put("comments", commentService.findComments());
		
		
		return map;
	}
	
	
}
