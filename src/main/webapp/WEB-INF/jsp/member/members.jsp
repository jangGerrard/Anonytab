<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set  var = "ctx"  value="${pageContext.request.contextPath }"  />
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Members</title>
</head>
<body>
Members

<table>
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>비밀번호</th>			
			<th>생일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="member" items="${members}">
			<tr>
				<td>${member.id }</td>
				<td><a href ="${ctx }/admin/member/detail.do?id=${member.id}">${member.name }</a></td>
				<td>${member.email }</td>
				<td>${member.password }</td>
				<td><fmt:formatDate value="${member.birthDate }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			</tr>
		</c:forEach>
	</tbody>
	
</table>







</body>
</html>