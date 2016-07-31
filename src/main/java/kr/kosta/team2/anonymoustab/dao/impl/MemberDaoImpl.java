package kr.kosta.team2.anonymoustab.dao.impl;

import java.util.List;

import kr.kosta.team2.anonymoustab.dao.MemberDao;
import kr.kosta.team2.anonymoustab.dao.impl.mapper.MemberMapper;
import kr.kosta.team2.anonymoustab.domain.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {

   @Autowired
   private MemberMapper memberMapper;
   
   public void updateMember(Member member){
	   this.memberMapper.updateMember(member);
   }

   public Member selectMember(long id) {
      return this.memberMapper.selectMember(id);
   }

   public List<Member> selectMembers() {
      return this.memberMapper.selectMembers();
   }

   public Member selectMemberByEmail(String email) {

      return this.memberMapper.selectMemberByEmail(email);
   }

   public boolean isEqualsPassword(String email, String password) {
      Member member = selectMemberByEmail(email);
      
      if(member==null)
      {
    	  return false;
      }
      if (member.isEqualPassword(password)) {
         return true;
      } else {
         return false;
      }

   }

   public long insertMembers(Member member) {
      this.memberMapper.insertMembers(member);            
      return member.getId();
      
   }

	public Member selectMemberByName(String name) {
		return this.memberMapper.selectMemberByName(name);		
	}
	
	public List<Member> selectMemberFriendsById(long id) {
		return this.memberMapper.selectMemberFriendsById(id);
	}
	
	public List<Member> selectUnregisteredMember(long id){
		return this.memberMapper.selectUnregisteredMember(id);
	}
}