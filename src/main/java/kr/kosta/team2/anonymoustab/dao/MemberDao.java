package kr.kosta.team2.anonymoustab.dao;

import java.util.List;

import kr.kosta.team2.anonymoustab.domain.Member;

public interface MemberDao {
   
   public long insertMembers(Member member);
   
   public void updateMember(Member member);

   public List<Member> selectMembers();
   
   public Member selectMember(long id);

   public Member selectMemberByEmail(String email);
   
   public Member selectMemberByName(String name);
   
   public boolean isEqualsPassword(String email, String password);
   
   public List<Member> selectMemberFriendsById(long id);
   
   public List<Member> selectUnregisteredMember(long id);

}