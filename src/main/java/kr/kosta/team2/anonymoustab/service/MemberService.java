package kr.kosta.team2.anonymoustab.service;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Member;

public interface MemberService {
	
	public long registerMember(Member member);
	
	public void modifyMember(Member member);
	
	public Member findMember(long id);
	
	public List<Member> findMembers();
	
	public Member findMemberByEmail(String email);
	
	public Member findMemberByName(String name);
	
	public boolean isEqualsPassword(String email, String password);
	
	public List<Member> findMemberFriendsById(long id);
	
	public List<Member> findUnregisteredMember(long id);
}
