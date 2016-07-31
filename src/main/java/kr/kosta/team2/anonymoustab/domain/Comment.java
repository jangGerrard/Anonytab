package kr.kosta.team2.anonymoustab.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect
public class Comment {
	
	private long id;
	private String contents;
	private long articleNo;
	private Date createDate;
	private Long createMemberId;
	private int recommend;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public long getArticleNo() {
		return articleNo;
	}
	
	public void setArticleNo(long articleNo) {
		this.articleNo = articleNo;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Long getCreateMemberId() {
		return createMemberId;
	}
	
	public void setCreateMemberId(long createMemberId) {
		this.createMemberId = createMemberId;
	}
	
	public int getRecommend() {
		return recommend;
	}
	
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	
}
