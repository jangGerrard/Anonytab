package kr.kosta.team2.anonymoustab.service.impl;

import kr.kosta.team2.anonymoustab.dao.ProfileImgDao;
import kr.kosta.team2.anonymoustab.domain.ProfileImg;
import kr.kosta.team2.anonymoustab.service.ProfileImgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileImgServiceImpl implements ProfileImgService{

	@Autowired
	private ProfileImgDao profileImgDao;

	public void registerProfileImg(ProfileImg profileImg){
		this.profileImgDao.insertProfileImg(profileImg);
	}	

	public void modifyProfileImg(ProfileImg profileImg){
		this.profileImgDao.updateProfileImg(profileImg);
	}
	
	public ProfileImg findProfileImg(long id) {
		return this.profileImgDao.selectProfileImg(id);
	}	
}
