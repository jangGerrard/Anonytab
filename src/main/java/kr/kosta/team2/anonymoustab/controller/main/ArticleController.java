package kr.kosta.team2.anonymoustab.controller.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.kosta.team2.anonymoustab.domain.Article;
import kr.kosta.team2.anonymoustab.domain.ArticleImg;
import kr.kosta.team2.anonymoustab.domain.ArticleVideo;
import kr.kosta.team2.anonymoustab.domain.FriendList;
import kr.kosta.team2.anonymoustab.domain.Notice;
import kr.kosta.team2.anonymoustab.service.ArticleImgService;
import kr.kosta.team2.anonymoustab.service.ArticleService;
import kr.kosta.team2.anonymoustab.service.ArticleVideoService;
import kr.kosta.team2.anonymoustab.service.FriendListService;
import kr.kosta.team2.anonymoustab.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/main/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleImgService articleImgService;
	@Autowired
	private FriendListService friendListservice;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private ArticleVideoService articleVideoService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@Transactional
	public String articleRegister(@RequestParam("contents") String contents,
			@RequestParam("articleLevel") int articleLevel,
			@RequestParam("file") MultipartFile multipartFile,//파일 컨트롤러에서 넘어온 사진 파일
			@RequestParam("videofile") MultipartFile videoMultipartFile,//비디오
			HttpSession session) {
		long id = (Long) session.getAttribute("loginMemberId");

		
		
		Article article = new Article();

		article.setContents(contents);
		article.setCreateDate(new Date());
		article.setDelYn(false);
		article.setCreateMemberId(id);
		article.setLevel(articleLevel);

		System.out.println("Registered Article No : "+this.articleService.registerArticle(article));
		if (multipartFile.getSize() > 0) {  //사진 파일 등록
			String fileRepo = "c:\\temp\\file\\article\\";
			String fileRealPath = "c:\\temp\\file\\article\\"
						+ multipartFile.getOriginalFilename();

			InputStream is = null;
			OutputStream os = null;
			try {
				// 업로드된 파일 스트림
				is = multipartFile.getInputStream();
				// 서버에 저장할 파일 스트림
				File file = new File(fileRepo
						+ multipartFile.getOriginalFilename());//파일이있는경로로 File 객체 생성해서
				os = new FileOutputStream(file);//그 파일 객체로 아웃풋 스트림을 생성하고

				// 업로드된 인풋스트림에서 byte만큼읽어서 저장시킬 파일 아웃풋스트림에 데이터 전송
				byte[] buffer = new byte[4096];
				while (is.read(buffer) >= 0) {//인풋 스트림꺼 읽어와서 buffer에적어
					os.write(buffer);
				}
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null)
					try {
						is.close();
					} catch (Exception e) {
					}
				if (os != null)
					try {
						os.close();
					} catch (Exception e) {
					}
			}
			ArticleImg articleImg = new ArticleImg();
			articleImg.setNo(article.getNo());
			articleImg.setImgPath(fileRealPath);
			this.articleImgService.registerArticleImg(articleImg);
		}
		
		if (videoMultipartFile.getSize() > 0) {  // 비디오 파일 등록
			String fileRepo = "c:\\temp\\file\\video\\";
			String fileRealPath = "c:\\temp\\file\\video\\"
					+ videoMultipartFile.getOriginalFilename();
			
			InputStream is = null;
			OutputStream os = null;
			try {
				// 업로드된 파일 스트림
				is = videoMultipartFile.getInputStream();
				// 서버에 저장할 파일 스트림
				File file = new File(fileRepo
						+ videoMultipartFile.getOriginalFilename());
				os = new FileOutputStream(file);

				// 업로드된 인풋스트림에서 byte만큼읽어서 저장시킬 파일 아웃풋스트림에 데이터 전송
				byte[] buffer = new byte[4096];
				while (is.read(buffer) >= 0) {
					os.write(buffer);
				}
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null)
					try {
						is.close();
					} catch (Exception e) {
					}
				if (os != null)
					try {
						os.close();
					} catch (Exception e) {
					}
			}
			ArticleVideo articleVideo = new ArticleVideo();
			articleVideo.setNo(article.getNo());
			articleVideo.setVideoPath(fileRealPath);
			this.articleVideoService.registerArticleVideo(articleVideo);
		}

		if (articleLevel == 1 || articleLevel == 2) {
			List<FriendList> friendLists = friendListservice.findFriends(id);
			for (FriendList friendList : friendLists) {
				long friendId = friendList.getFriendId();

				Notice notice = new Notice();
				notice.setId(id);
				notice.setFriendId(friendId);
				notice.setNo(article.getNo());
				notice.setCreateDate(new Date());
				notice.setLevel(5);
				noticeService.registerNoticeArticleRegisterToFriend(notice);
			}
		}
		if(articleLevel==3)
		{
			return "redirect:/main/mypage.do?id="+id;
		}
		else if(articleLevel==4)
		{
			return "redirect:/anonyfeed.do";
		}
		else
		{
			return "redirect:/main.do";
		}
		
	}

	@RequestMapping("/detail/download")
	public void articleDownload(@RequestParam("no") long no,
			HttpServletResponse response) {
		ArticleImg articleImg = null;
		String filePath = null;

		if (articleImgService.findArticleImg(no) != null)
		{
			articleImg = articleImgService.findArticleImg(no);
			filePath = articleImg.getImgPath();
			System.out.println("filePath = " + filePath);
			
			File file = new File(filePath);//db의 저장된 filepath를 가지고 파일객체 생성해서
			response.setContentLength((int) file.length());//?.?
			
			InputStream is = null;
			OutputStream os = null;
			
			try {
				is = new FileInputStream(file);//인풋스트림열어서
				os = response.getOutputStream();//서버에서의 업로드된 파일을 가져오는거다.
				byte[] buffer = new byte[4096];
				while (is.read(buffer) >= 0) {
					os.write(buffer);
				}
				os.flush();
			} 
			catch (Exception e) {
				System.out.println("Article 경로에 지정된 사진이 없습니다. \"Article No\" : "+no);
			}finally {if (is != null)try {is.close();} catch (Exception e) {}
			if (os != null)try {os.close();} catch (Exception e) {}}
		}
	
	}
	
	@RequestMapping("/detail/download/video")
	public void articleDownloadVideo(@RequestParam("no") long no, HttpServletResponse response) {
		
		ArticleVideo articleVideo = null;
		String filePath = null;

		if (articleVideoService.findArticleVideo(no) != null)
		{
			articleVideo = articleVideoService.findArticleVideo(no);
			filePath = articleVideo.getVideoPath();
			System.out.println("filePath = " + filePath);
			
			File file = new File(filePath);
			response.setContentLength((int) file.length());
			
			InputStream is = null;
			OutputStream os = null;
			
			try {
				is = new FileInputStream(file);//서버에 있는 파일의 경로를 통해서 파일객체를생성해서 
				os = response.getOutputStream();
				byte[] buffer = new byte[4096];
				while (is.read(buffer) >= 0) {//인풋스트림으로 읽에서 
					os.write(buffer);//os에 쓴다.
				}
				os.flush();
			} 
			catch (Exception e) {
				System.out.println("Article Video 경로에 지정된 파일이 없습니다. \"Article No\" : " + no);
			} finally {if (is != null)try {is.close();} catch (Exception e) {}
			if (os != null)try {os.close();} catch (Exception e) {}}
		}
	//여기서 다운 로드된걸 바탕으로 우리는 뷰를 해줫던거지.
	}
	
	@RequestMapping("/myarticle/list")
	@ResponseBody
	public Map<String,Object> myArticleList(
			HttpSession session
			){
		long id = (Long) session.getAttribute("loginMemberId");
		Map<String,Object> map = new HashMap<String,Object>();
		if(id != 0){ 
			List<Article> articles = articleService.findMyNewsfeedArticles(id , 10000);
			map.put("articles",articles);
		}
		return map;
	}
	
	@RequestMapping("/myarticle/delete")
	@Transactional
	public String deleteMyArticle(
			@RequestParam("deleteArticleList") List<Long> deleteArticleList
			){
		for (Long articleNo : deleteArticleList) {
			Article article = this.articleService.findArticle(articleNo);
			article.setDelYn(true);
			
			this.articleService.modifyArticleDelYnTrue(article);
		}
		return "redirect:/main/friendlist.do";
	}

}
