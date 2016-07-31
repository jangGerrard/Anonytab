package kr.kosta.team2.anonymoustab.service;

import kr.kosta.team2.anonymoustab.domain.ProfileImg;

public interface ProfileImgService {
	
	public void registerProfileImg(ProfileImg profileImg);

	public void modifyProfileImg(ProfileImg profileImg);
	
	public ProfileImg findProfileImg(long id);
	

}
