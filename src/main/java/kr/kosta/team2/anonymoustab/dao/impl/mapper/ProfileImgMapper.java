package kr.kosta.team2.anonymoustab.dao.impl.mapper;

import kr.kosta.team2.anonymoustab.domain.ProfileImg;

public interface ProfileImgMapper {
	public void insertProfileImg(ProfileImg profileImg);

	public void updateProfileImg(ProfileImg profileImg);
	
	public ProfileImg selectProfileImg(long id);
	
}
