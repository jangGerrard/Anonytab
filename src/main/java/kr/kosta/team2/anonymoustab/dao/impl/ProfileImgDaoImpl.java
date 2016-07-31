package kr.kosta.team2.anonymoustab.dao.impl;

import kr.kosta.team2.anonymoustab.dao.ProfileImgDao;
import kr.kosta.team2.anonymoustab.dao.impl.mapper.ProfileImgMapper;
import kr.kosta.team2.anonymoustab.domain.ProfileImg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileImgDaoImpl implements ProfileImgDao{

	@Autowired
	private ProfileImgMapper profileImgMapper;
	
	public void insertProfileImg(ProfileImg profileImg){
		this.profileImgMapper.insertProfileImg(profileImg);
	}
	
	public void updateProfileImg(ProfileImg profileImg){
		this.profileImgMapper.updateProfileImg(profileImg);
	}

	public ProfileImg selectProfileImg(long id) {
		return profileImgMapper.selectProfileImg(id);
	}

}
