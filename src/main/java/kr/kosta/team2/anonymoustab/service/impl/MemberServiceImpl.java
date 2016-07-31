package kr.kosta.team2.anonymoustab.service.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.MemberDao;
import kr.kosta.team2.anonymoustab.domain.Member;
import kr.kosta.team2.anonymoustab.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	public long registerMember(Member member	){
		return this.memberDao.insertMembers(member);
	}

	public void modifyMember(Member member){
		this.memberDao.updateMember(member);
	}
	
	public Member findMember(long id) {
		return this.memberDao.selectMember(id);
	}

	public List<Member> findMembers() {
		return this.memberDao.selectMembers();
	}

	public Member findMemberByEmail(String email) {
		
		return this.memberDao.selectMemberByEmail(email);
	}

	public Member findMemberByName(String name) {
		return this.memberDao.selectMemberByName(name);
	}

	public boolean isEqualsPassword(String email, String password) {
		
		return this.memberDao.isEqualsPassword(email, password);
	}

	public List<Member> findMemberFriendsById(long id) {
		return this.memberDao.selectMemberFriendsById(id);
	}
	
	public List<Member> findUnregisteredMember(long id){
		return this.memberDao.selectUnregisteredMember(id);
	}


}
