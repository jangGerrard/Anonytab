package kr.kosta.team2.anonymoustab.dao.impl.mapper;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Member;

public interface MemberMapper {
	
	public void updateMember(Member member);
   
   public List<Member> selectMembers();
   
   public Member selectMember(long id);
   
   public Member selectMemberByEmail(String email);

   public long insertMembers(Member member);

   public Member selectMemberByName(String name);

   public List<Member> selectMemberFriendsById(long id);
   
   public List<Member> selectUnregisteredMember(long id);
   
}