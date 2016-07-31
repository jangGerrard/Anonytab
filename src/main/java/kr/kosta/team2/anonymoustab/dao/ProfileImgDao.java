package kr.kosta.team2.anonymoustab.dao;

import kr.kosta.team2.anonymoustab.domain.ProfileImg;

public interface ProfileImgDao {
	
	public void insertProfileImg(ProfileImg profileImg);
	
	public void updateProfileImg(ProfileImg profileImg);
	
	public ProfileImg selectProfileImg(long id);	
}
