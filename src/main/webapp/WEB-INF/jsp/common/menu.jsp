<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/style/css/main/bootstrap.min.css" rel = "stylesheet">
<link href="${ctx}/style/css/main/styles.css" rel = "stylesheet">
<title>Menu</title>
</head>
<body>

<h2>메뉴</h2>

<!-- <div class="box"></div>
        <div class="row row-offcanvas row-offcanvas-left"></div> -->

<div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">
	<ul class="nav hidden-xs" id="lg-menu" id="menu">
		<c:forEach var = "menu" items = "${menus}">
		<li><a href  = "/{menu.url}">${menu.name}</a></li>
		</c:forEach>	
	</ul>
</div>


</body>
</html>