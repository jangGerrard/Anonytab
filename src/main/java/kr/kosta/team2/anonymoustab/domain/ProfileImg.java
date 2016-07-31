package kr.kosta.team2.anonymoustab.domain;

public class ProfileImg {
	
	private long id;
	private String imgPath;
	private boolean delYn;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getImgPath() {
		return imgPath;
	}
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public boolean isDelYn() {
		return delYn;
	}
	
	public void setDelYn(boolean delYn) {
		this.delYn = delYn;
	}
	
}
