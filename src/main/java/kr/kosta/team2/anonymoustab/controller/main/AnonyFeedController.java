package kr.kosta.team2.anonymoustab.controller.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.kosta.team2.anonymoustab.domain.Article;
import kr.kosta.team2.anonymoustab.domain.ArticleVideo;
import kr.kosta.team2.anonymoustab.domain.Comment;
import kr.kosta.team2.anonymoustab.domain.Member;
import kr.kosta.team2.anonymoustab.domain.Notice;
import kr.kosta.team2.anonymoustab.service.ArticleService;
import kr.kosta.team2.anonymoustab.service.ArticleVideoService;
import kr.kosta.team2.anonymoustab.service.CommentService;
import kr.kosta.team2.anonymoustab.service.MemberService;
import kr.kosta.team2.anonymoustab.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/anonyfeed")
public class AnonyFeedController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private  CommentService commentService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private ArticleVideoService articleVideoService;
	
	@RequestMapping
	public ModelAndView anonyForm(
			HttpSession session
			){
		long loginId = (Long)session.getAttribute("loginMemberId");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/anonyfeed/anonyfeed");
		mav.addObject("loginId", loginId);
		
		
		return mav;
	}

	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> anonyfeedList(
			@RequestParam(value="limit",required=false) Integer limit
			){
		int mLimit = 10;
		if (limit != null) {
			mLimit = limit;
		}

		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Article> articles = this.articleService.findAnonyFeedArticles(mLimit);		
		List<Member> commentMembers=new ArrayList<Member>();		
		List<Member> memberIds=new ArrayList<Member>();
		
		for(Article article :articles)
		{	
			List<Comment> commentsList =new ArrayList<Comment>();
			
			long createId=article.getCreateMemberId();
			memberIds.add(memberService.findMember(createId));
			
			long no=article.getNo();
			
			
			if(commentService.findCommentByArticleNo(no)!=null){
				Member createMember=null;
				commentsList=commentService.findCommentByArticleNo(no);
				
				for(Comment comment: commentsList){	
					createMember =memberService.findMember(comment.getCreateMemberId());
					commentMembers.add(createMember);
				}				
			}			
			
			article.setComments(commentsList);
			//no에 달린 댓글이 많다..
		}
		
		map.put("commentMembers",commentMembers);
		map.put("members",memberIds);//이걸 전체로 가져가는게아니고
		map.put("articles",articles);		
		
		return map;
	}
	
	@RequestMapping("topfivelist")
	@ResponseBody
	public Map<String,Object> anonyfeedTopFiveList(
			@RequestParam(value="limit",required=false) Integer limit
			){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Article> articles = this.articleService.findAnonyFeedTopFiveArticles();		
		List<Member> commentMembers=new ArrayList<Member>();		
		List<Member> memberIds=new ArrayList<Member>();
		
		for(Article article :articles)
		{	
			List<Comment> commentsList =new ArrayList<Comment>();
			
			long createId=article.getCreateMemberId();
			memberIds.add(memberService.findMember(createId));
			
			long no=article.getNo();
			
			
			if(commentService.findCommentByArticleNo(no)!=null){
				Member createMember=null;
				commentsList=commentService.findCommentByArticleNo(no);
				
				for(Comment comment: commentsList){	
					createMember =memberService.findMember(comment.getCreateMemberId());
					commentMembers.add(createMember);
				}				
			}			
			
			article.setComments(commentsList);
			//no에 달린 댓글이 많다..
		}
		
		List<Long> videoList = new ArrayList<Long>();

		List<ArticleVideo> videosList = articleVideoService.findArticleVideos();
		for (ArticleVideo articleVideo : videosList) {
			videoList.add(articleVideo.getNo());
		}
		
		map.put("commentMembers",commentMembers);
		map.put("members",memberIds);//이걸 전체로 가져가는게아니고
		map.put("articles",articles);
		map.put("videos", videoList);		
		return map;
	}
	
	@Transactional
	@RequestMapping("comment/insert")
	@ResponseBody
	public Map<String,Object> commentInsert(
			@RequestParam(value = "limit" , required = false) Integer limit,
			@RequestParam("contents") String contents,
			@RequestParam("no") long no,
			@RequestParam("tag") String tagEmail,
			HttpSession session
			){
		
		long id = (Long) session.getAttribute("loginMemberId");
		
		Member member = memberService.findMemberByEmail(tagEmail);
		Notice notice = new Notice();
		notice.setId(id);
		notice.setFriendId(member.getId());
		notice.setLevel(2);
		notice.setCreateDate(new Date());
		notice.setNo(no);
		noticeService.registerNoticeTagToFriend(notice);
		return null;
	}
	
	
}
