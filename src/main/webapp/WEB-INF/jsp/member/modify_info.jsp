<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix= "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function Change(target, type) {
	if (target.value == target.defaultValue && type == 0)
		target.value = '';
	if (!target.value && type == 1)
		target.value = target.defaultValue;
}


function reset(){
	location.href = '${ctx}/main/mypage.do?${member.id}';
}

function check() {
	var check = true;
	var pw = document.getElementByName('password').value;
	var rePw = document.getElementByName('rePassword').value;

	var email = document.getElementByName('email').value;

	if (email.match(/^(\w+)@(\w+)[.](\w+)$/ig)) {

	} else {
		alert('메일형식과 일치하지 않습니다.');
		check = false;
		return check;
	}

	if (pw.length < 6) {
		alert('비밀번호는 6자이상 입력하세요');
		check = false;
		return check;
	}

	if (pw != rePw) {
		alert('비밀번호가 불일치 합니다.');
		check = false;
		return check;
	}
	
	if (check) {
		var formCheck = document.getElementById("check");
		formCheck.summit();
	} else {
		return false;
	}
}



</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사용자 정보 수정</title>
</head>
<body>

<form action="${ctx}/main/mypage/modifymyinfo.do?"  id = "check" method="post" onsubmit="check(); return false;">
	<table>
		<tbody>
			<tr>
				<td>이메일</td>
				<td><input type="text" name = "email" value ="${member.email}" onFocus='Change(this,0)' onBlur='Change(this,1)'></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name = "name" value="${member.name }" onFocus='Change(this,0)' onBlur='Change(this,1)'></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name = "password" value="Password" onFocus='Change(this,0)' onBlur='Change(this,1)'></td>
			</tr>
			<tr>
				<td>비밀번호 재확인</td>
				<td><input type="password" name = "rePassword" value = "Password" onFocus='Change(this,0)' onBlur='Change(this,1)'></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name = "phone" value="${member.phone }" onFocus='Change(this,0)' onBlur='Change(this,1)'></td>
			</tr>
			<tr>
				<td>생일</td>
				<td><input type="text" name = "birthDate" value="<fmt:formatDate value="${member.birthDate }" pattern="yyyy-MM-dd"/>" onFocus='Change(this,0)' onBlur='Change(this,1)'></td>
			</tr>
		</tbody>
	</table>
	
<input type = "submit" value = "수정" >
<input type = "button" value = "취소"  onclick = "reset();">
</form>

</body>
</html>