package kr.kosta.team2.anonymoustab.controller.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import kr.kosta.team2.anonymoustab.domain.Article;
import kr.kosta.team2.anonymoustab.domain.ArticleImg;
import kr.kosta.team2.anonymoustab.domain.ArticleVideo;
import kr.kosta.team2.anonymoustab.domain.Comment;
import kr.kosta.team2.anonymoustab.domain.Member;
import kr.kosta.team2.anonymoustab.domain.Notice;
import kr.kosta.team2.anonymoustab.service.ArticleImgService;
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
@RequestMapping("/main")
public class MainController {

	@Autowired
	MemberService memberService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ArticleImgService articleImgService;
	@Autowired
	CommentService commentService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	ArticleVideoService articleVideoService;

	@RequestMapping
	public ModelAndView mainForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/main/main");
		return mav;
	}

	@RequestMapping("/member/list")
	@ResponseBody
	public List<Member> memberList() {
		return memberService.findMembers();
	}

	@RequestMapping("/goto/friend")
	public String gotoFriend(@RequestParam("email") String email) {
		// System.out.println("email = "+info);
		// String arr[] =info.split(",");
		//
		// Member member=null;
		// if(arr.length==1)
		// {
		// String name=arr[0].trim();
		// System.out.println("사이즈 1 이름 : "+arr[0].trim() );
		// member=memberService.findMemberByName(name);
		// }
		// else
		// {
		// }
		Member member;
		if (email != null) {
			member = memberService.findMemberByEmail(email);
		} else {
			return "redirect:/main.do";
		}

		return "redirect:/main/mypage.do?id=" + member.getId();
	}

	// 뉴스피드 띄우는 부분이다.
	// 여기서 level 값을 받아서 어디꺼인지르 구별해줄생각.
	@RequestMapping("/newsfeed/json")
	@ResponseBody
	public Map<String, Object> newsfeedJson(
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "level", required = false) Integer level,
			@RequestParam(value = "myPageId", required = false) Long myPageId,
			HttpSession session) {

		int rLevel = 0;
		if (level != null) {
			rLevel = level;
		}

		// 여기에서 이제 아티클뿐만아니라 멤버의 객체도 넣어서 가야한다.
		int mLimit = 10;
		if (limit != null) {
			mLimit = limit;
		}

		long id = (Long) session.getAttribute("loginMemberId");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Article> articles = null;
		// 여기 전체 멤버가있는데 여기서, 전체멤버를 가져가는게 아니라,
		if (rLevel == 1) {
			System.out.println("myPageId =" + myPageId);
			articles = articleService.findMyNewsfeedArticles(myPageId, mLimit);
		} else if (rLevel == 2)// 어노니머스 뉴스피드쪽
		{

			articles = articleService.findAnonyFeedTopFiveArticles();
			//리턴할 아티클이며 탑5 담고있다.
			List<Article> nomals = articleService.findAnonyFeedArticles(mLimit);
			//저 리스트에 nomals 를 대입하면 되는데 거기에서 같은건 뺴고 대입
			//중복이 문제가 되는거지
			System.out.println("size = "+articles.size()+"   size = "+nomals.size());

			List<Long> lists = new ArrayList<Long>();
			List<Integer> indexs= new ArrayList<Integer>();
			
			for(Article article : articles)
			{
				lists.add(article.getNo());
			}//여기서 top 5브 아티클의 no를 담앗다 nomals 에서 이걸 제외하고 추가해주면되
			
			for(Article nom :nomals)
			{
				for(Long item : lists)//하지만 여기서 3번돌겟지
				{
					if(nom.getNo()==item)
					{
						indexs.add(nomals.indexOf(nom));
						System.out.println("nomals.indexOf(nom)" +nomals.indexOf(nom));
					}
				}
			}
			
			System.out.println("sizme =  "+nomals.size());
			for(int len = indexs.size()-1; len>=0 ;len--)
			{
				System.out.println("indexs.get(len) = "+indexs.get(len));
				int n=indexs.get(len);
				nomals.remove(n);
			}
			System.out.println("sizme =  "+nomals.size());
			
			for(Article nom :nomals)
			{
				articles.add(nom);
			}
			
			System.out.println("sizel  dd d dd = "+articles.size());

		} else// url에서 level이 안왔다는거
		{
			articles = articleService.findNewsfeedArticles(id, mLimit);// 여기에서
																		// 나온걸
																		// 크리에이트멤버의
																		// id를
		}

		// 가져가야겟당.
		// 이아이티클의 순서대로... 댓글도가져와야지

		List<Member> commentMembers = new ArrayList<Member>();

		List<Member> memberIds = memberService.findMembers();
		for (Article article : articles) {
			List<Comment> commentsList = new ArrayList<Comment>();

			long no = article.getNo();

			if (commentService.findCommentByArticleNo(no) != null) {
				Member createMember = null;
				commentsList = commentService.findCommentByArticleNo(no);

				for (Comment comment : commentsList) {
					createMember = memberService.findMember(comment
							.getCreateMemberId());
					commentMembers.add(createMember);
				}

			}

			article.setComments(commentsList);
		}
		List<Long> videoList = new ArrayList<Long>();

		List<ArticleVideo> videosList = articleVideoService.findArticleVideos();
		for (ArticleVideo articleVideo : videosList) {
			videoList.add(articleVideo.getNo());
		}

		map.put("commentMembers", commentMembers);
		map.put("members", memberIds);// 이걸 전체로 가져가는게아니고
		map.put("articles", articles);
		map.put("videos", videoList);

		return map;
	}

	@Transactional
	@RequestMapping("/comment/insert")
	@ResponseBody
	public Map<String, Object> commentInsert(
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam("contents") String contents,
			@RequestParam("no") long no, HttpSession session,
			@RequestParam("tag") String tagEmail,
			@RequestParam(value = "level", required = false) Integer level,
			@RequestParam(value = "myPageId", required = false) Long myPageId) {
		long id = (Long) session.getAttribute("loginMemberId");
		// TODO 재관이형 여기에서 tagEmail 이게 내가 태그할사람의 EMAIL 이니까 조작해서만들어
		
		// 태그 notice 하는것
		if (tagEmail !=""&&tagEmail !=null) {
			System.out.println("\ntagEMAIL = " + tagEmail);
			Member member = null;
			if (memberService.findMemberByEmail(tagEmail) != null) {
				member = memberService.findMemberByEmail(tagEmail);
				Notice notice = new Notice();
				notice.setId(id);
				notice.setFriendId(member.getId());
				notice.setLevel(2);
				notice.setCreateDate(new Date());
				notice.setNo(no);
				noticeService.registerNoticeTagToFriend(notice);

			}
		}


		Comment comment = new Comment();
		System.out.println("\n댓글 = "+contents);
		comment.setContents(contents);
		comment.setCreateDate(new Date());
		comment.setCreateMemberId(id);
		comment.setArticleNo(no);

		commentService.registerComment(comment);

		// -----------------------------------------------------여기까지가 코맨트이다.

		int mLimit = 10;
		if (limit != null) {
			mLimit = limit;
		}

		int rLevel = 0;
		if (level != null)// 어느 레벨에서 온 콜인지 구별해야한다.
		{
			rLevel = level;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		List<Article> articles = null;
		// 여기 전체 멤버가있는데 여기서, 전체멤버를 가져가는게 아니라,
		if (rLevel == 1) {
			articles = articleService.findMyNewsfeedArticles(myPageId, mLimit);
		} else if (rLevel == 2)// 어노니머스 뉴스피드쪽
		{
			articles = articleService.findAnonyFeedTopFiveArticles();
			//리턴할 아티클이며 탑5 담고있다.
			List<Article> nomals = articleService.findAnonyFeedArticles(mLimit);
			//저 리스트에 nomals 를 대입하면 되는데 거기에서 같은건 뺴고 대입
			//중복이 문제가 되는거지
			System.out.println("size = "+articles.size()+"   size = "+nomals.size());

			List<Long> lists = new ArrayList<Long>();
			List<Integer> indexs= new ArrayList<Integer>();
			
			for(Article article : articles)
			{
				lists.add(article.getNo());
			}//여기서 top 5브 아티클의 no를 담앗다 nomals 에서 이걸 제외하고 추가해주면되
			
			for(Article nom :nomals)
			{
				for(Long item : lists)//하지만 여기서 3번돌겟지
				{
					if(nom.getNo()==item)
					{
						indexs.add(nomals.indexOf(nom));
						System.out.println("nomals.indexOf(nom)" +nomals.indexOf(nom));
					}
				}
			}
			
			System.out.println("sizme =  "+nomals.size());
			for(int len = indexs.size()-1; len>=0 ;len--)
			{
				System.out.println("indexs.get(len) = "+indexs.get(len));
				int n=indexs.get(len);
				nomals.remove(n);
			}
			System.out.println("sizme =  "+nomals.size());
			
			for(Article nom :nomals)
			{
				articles.add(nom);
			}
			
			System.out.println("sizel  dd d dd = "+articles.size());
		} else// url에서 level이 안왔다는거
		{
			articles = articleService.findNewsfeedArticles(id, mLimit);// 여기에서
																		// 나온걸
																		// 크리에이트멤버의
																		// id를
		}

		List<Member> commentMembers = new ArrayList<Member>();

		List<Member> memberIds = memberService.findMembers();
		for (Article article : articles) {
			List<Comment> commentsList = new ArrayList<Comment>();


			long nno = article.getNo();

			if (commentService.findCommentByArticleNo(nno) != null) {
				Member createMember = null;
				commentsList = commentService.findCommentByArticleNo(nno);

				for (Comment ccomment : commentsList) {
					createMember = memberService.findMember(ccomment
							.getCreateMemberId());
					commentMembers.add(createMember);
				}

			}

			article.setComments(commentsList);
			// no에 달린 댓글이 많다..
		}
		List<Long> videoList = new ArrayList<Long>();

		List<ArticleVideo> videosList = articleVideoService.findArticleVideos();
		for (ArticleVideo articleVideo : videosList) {
			videoList.add(articleVideo.getNo());
		}

		map.put("commentMembers", commentMembers);
		map.put("members", memberIds);// 이걸 전체로 가져가는게아니고
		map.put("articles", articles);
		map.put("videos", videoList);

		return map;
	}

	@RequestMapping("/article/detail/list")
	@ResponseBody
	public Map<String, Object> detailArticle(
			@RequestParam(value = "no", required = false) Long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		long cno = 1L;
		if (no == null) {
			no = cno;
		}
		Article article = this.articleService.findArticle(no);
		ArticleImg articleImg = this.articleImgService.findArticleImg(article
				.getNo());
		// mav.setViewName(); +

		String imgPath = articleImg.getImgPath();

		Member member = memberService.findMember(article.getCreateMemberId());

		map.put("article", article);
		map.put("imgPath", imgPath);
		map.put("memberName", member.getName());

		return map;
	}

	@RequestMapping("/article/recommend")
	@Transactional
	@ResponseBody
	public Article recommendUpdate(@RequestParam("check") boolean check,
			@RequestParam("no") Long no) {

		System.out.println("check = " + check + "no = " + no);

		if (check) {
			System.out.println("add");
			articleService.modifyArticleRecommendAdd(no);
		} else {
			System.out.println("minus");
			articleService.modifyArticleRecommendMinus(no);
		}

		return articleService.findArticle(no);

	}

	@RequestMapping("/auto")
	public ModelAndView autoJSP() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/main/auto");
		return mav;
	}

	@RequestMapping("/auto-tag")
	public ModelAndView autoTagJSP() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/main/auto-tag");
		return mav;
	}

	@RequestMapping("/auto/tag")
	@ResponseBody
	public List<Member> autoSuggestTag(HttpSession session) {
		Long id = (Long) session.getAttribute("loginMemberId");

		List<Member> members = memberService.findMemberFriendsById(id);

		return members;
	}

	@RequestMapping("/auto/search")
	@ResponseBody
	public List<Member> autoSuggest() {

		List<Member> members = memberService.findMembers();

		return members;
	}

	@RequestMapping("/share")
	@Transactional
	public String shareRegister(@RequestParam("shareLevel") int shareLevel,
			@RequestParam("shareArticleNo") int intArticleNo,
			@RequestParam("shareContents") String shareContents,
			HttpSession session) {
		System.out.println("article ID : " + intArticleNo);
		System.out.println("shareContents  :  " + shareContents);
		long articleNo = Long.parseLong((Integer.toString(intArticleNo)));
		long id = (Long) session.getAttribute("loginMemberId");
		List<Member> members = new ArrayList<Member>();
		Article article = articleService.findArticle(articleNo);
		Member createMember = memberService.findMember(article
				.getCreateMemberId());
		Member sharedMember = memberService.findMember(id);
		String sContents=article.getContents();//.substring(0, 20);
		if(sContents.length()>20)
		{
			sContents=article.getContents().substring(0, 20);
			sContents+="....";
		}
		String contents = "[ " + createMember.getName() + " ] 님의 글을 인용한 글 입니다.<br/>" 
		+ "원문 > "+ sContents+"<br/><br/>"
		+ ": "+ shareContents;

		
		ArticleImg articleImg = articleImgService.findArticleImg(article
				.getNo());
		ArticleVideo articleVideo = articleVideoService
				.findArticleVideo(article.getNo());

		if (shareLevel == 1) { // 전체 공유

			members = memberService.findMembers();

			article.setComments(null);
			article.setContents(contents);
			article.setCreateDate(new Date());
			article.setCreateMemberId(id);
			article.setDelYn(false);
			article.setLevel(shareLevel);
			article.setRecommend(0);

			long newArticleNo = 0L;
			newArticleNo = this.articleService.registerArticle(article);

			System.out.println("auto Gen Key : " + newArticleNo);
			if (articleImg != null) {
				ArticleImg shareImg = new ArticleImg();
				shareImg.setImgPath(articleImg.getImgPath());
				shareImg.setNo(newArticleNo);
				System.out.println("new Article No On Img : " + newArticleNo);
				System.out.println("articleImgPath : "
						+ articleImg.getImgPath());
				articleImgService.registerArticleImg(shareImg);
				System.out.println("articleImgPath : "
						+ articleImg.getImgPath() + "  new Article No : "
						+ newArticleNo);
			} else if (articleVideo != null) {
				ArticleVideo shareVideo = new ArticleVideo();
				shareVideo.setVideoPath(articleVideo.getVideoPath());
				shareVideo.setNo(newArticleNo);
				articleVideoService.registerArticleVideo(shareVideo);
				System.out.println("new Article No On Video : " + newArticleNo);
				System.out.println("articleVideoPath : "
						+ articleVideo.getVideoPath());

			} else {
				System.out.println("사진 비디오 파일이 없습니다.");
			}

			for (Member member : members) {
				Notice notice = new Notice();
				notice.setId(id);
				notice.setFriendId(member.getId());
				notice.setNo(newArticleNo);
				notice.setLevel(3);
				notice.setContents(shareContents);
				notice.setCreateDate(new Date());
				noticeService.registerNoticeShareToFriend(notice);
			}
		} else if (shareLevel == 2) { // 친구 공유

			members = memberService.findMemberFriendsById(id);

			article.setComments(null);
			article.setContents(contents);
			article.setCreateDate(new Date());
			article.setCreateMemberId(id);
			article.setDelYn(false);
			article.setLevel(shareLevel);
			article.setRecommend(0);

			long newArticleNo = articleService.registerArticle(article);
			if (articleImg != null) {
				ArticleImg shareImg = new ArticleImg();
				shareImg.setImgPath(articleImg.getImgPath());
				shareImg.setNo(newArticleNo);
				System.out.println("articleImgPath : "
						+ articleImg.getImgPath() + "new Article No : "
						+ newArticleNo);
				articleImgService.registerArticleImg(shareImg);
			} else if (articleVideo != null) {
				ArticleVideo shareVideo = new ArticleVideo();
				shareVideo.setVideoPath(articleVideo.getVideoPath());
				shareVideo.setNo(newArticleNo);
				articleVideoService.registerArticleVideo(shareVideo);
			} else {
				System.out.println("사진 비디오 파일이 없습니다.");
			}

			for (Member member : members) {
				Notice notice = new Notice();
				notice.setId(id);
				notice.setFriendId(member.getId());
				notice.setNo(newArticleNo);
				notice.setLevel(3);
				notice.setContents(shareContents);
				notice.setCreateDate(new Date());
				noticeService.registerNoticeShareToFriend(notice);
			}
		} else if (shareLevel == 3) {

			article.setComments(null);
			article.setContents(contents);
			article.setCreateDate(new Date());
			article.setCreateMemberId(id);
			article.setDelYn(false);
			article.setLevel(shareLevel);
			article.setRecommend(0);

			long newArticleNo = articleService.registerArticle(article);

			if (articleImg != null) {
				ArticleImg shareImg = new ArticleImg();
				shareImg.setImgPath(articleImg.getImgPath());
				shareImg.setNo(newArticleNo);
				articleImgService.registerArticleImg(shareImg);
			} else if (articleVideo != null) {
				ArticleVideo shareVideo = new ArticleVideo();
				shareVideo.setVideoPath(articleVideo.getVideoPath());
				shareVideo.setNo(newArticleNo);
				articleVideoService.registerArticleVideo(shareVideo);
			} else {
				System.out.println("사진 비디오 파일이 없습니다.");
			}
		} else if (shareLevel == 4) {

			article.setComments(null);
			article.setContents(contents);
			article.setCreateDate(new Date());
			article.setCreateMemberId(id);
			article.setDelYn(false);
			article.setLevel(shareLevel);
			article.setRecommend(0);

			long newArticleNo = articleService.registerArticle(article);

			if (articleImg != null) {
				ArticleImg shareImg = new ArticleImg();
				shareImg.setImgPath(articleImg.getImgPath());
				shareImg.setNo(newArticleNo);
				System.out.println("articleImgPath : "
						+ articleImg.getImgPath() + "new Article No : "
						+ newArticleNo);
				articleImgService.registerArticleImg(shareImg);
			} else if (articleVideo != null) {
				ArticleVideo shareVideo = new ArticleVideo();
				shareVideo.setVideoPath(articleVideo.getVideoPath());
				shareVideo.setNo(newArticleNo);
				articleVideoService.registerArticleVideo(shareVideo);
			} else {
				System.out.println("사진 비디오 파일이 없습니다.");
			}

			return "redirect:/anonyfeed.do";
		}
		return "redirect:/main.do";
	}

}
