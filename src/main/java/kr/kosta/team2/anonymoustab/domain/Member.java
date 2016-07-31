package kr.kosta.team2.anonymoustab.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect
public class Member {
   
   private long id;
   private String name;
   private String email;
   private String password;
   private Date birthDate;
   private String phone;
   
   public long getId() {
      return id;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public void setId(long id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   @JsonSerialize(using=JsonDateSerializerForMember.class)
   public Date getBirthDate() {
      return birthDate;
   }
   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }
   public boolean isEqualPassword(String password) {
      if (this.password.equals(password)) {
         return true;
      } else {
         return false;
      }
   }

}