<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<c:set var="loginId"
	value="<%=session.getAttribute(\"loginMemberId\")%>" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link href="${ctx}/style/css/main/main.css" rel="stylesheet">
<link href="${ctx}/style/css/friendlist/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/style/css/main/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/style/css/main/styles.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx}/style/css/friendlist/autocomplete.css"><!-- zindex -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,
 maximum-scale=1, user-scalable=no">
<title>Management</title>

<script type="text/javascript"
	src="${ctx}/style/js/lib/jquery-1.11.1.js"></script>
<script src="${ctx }/style/js/main/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>

<title>Friend</title>

<script type="text/javascript">
function friendMypage(id) {
	location.href = '${ctx}/main/mypage.do?id=' + id;
}



function noticeFormSubmit(el){
	var formVar = document.getElementById('noticeForm');
	formVar.action = '${ctx}/main/friendlist/requestedfriend/accept.do?sort=' + el;
	formVar.submit();
}

function autoSuggest()
{
	$.get('${ctx}/main/auto/search.do',
			function(data) {
	
	var arr=new Array();
	arr= data;
	
 		var projects=new Array();
	for(var i = 0 ; i<arr.length;i++)
	{
	projects[i]={
	        value: arr[i].email,
	        label: arr[i].name,
	        desc: arr[i].email,
	        icon: arr[i].id
	      };
	}
 
    $( "#project" ).autocomplete({
      minLength: 0,
      source: projects,
      focus: function( event, ui ) {
        $( "#project" ).val( ui.item.label );
        return false;
      },
      select: function( event, ui ) {
        $( "#project" ).val( ui.item.label );
        $( "#project-id" ).val( ui.item.value );
        $( "#project-description" ).html( ui.item.desc );
        $( "#project-icon" ).attr( "src", "images/" + ui.item.icon );
 
        return false;
      }
    })
    .autocomplete( "instance" )._renderItem = function( ul, item ) {
      return $( "<li>" )
        .append( '<a><img id="viewImg" src="${ctx }/main/mypage/img/download.do?id='+item.icon+'"/> '+ item.label +' <br>' + item.desc + '</a>' )
        .appendTo( ul );
    };
    //이부분에 아이디를 줘서해야한다.
    
    
	});
}

	function searchFriend() {
		var email =$('#project-id').val();

		location.href = '${ctx}/main/goto/friend.do?email='
				+ encodeURIComponent(email);
	}

	
	function autoSuggestUn()
	{
		$.get('${ctx}/main/friendlist/unregistermember/list.do',
				function(data) {
		
		var arr=new Array();
		arr= data;
		
	 		var projects=new Array();
		for(var i = 0 ; i<arr.length;i++)
		{
		projects[i]={
		        value: arr[i].email,
		        label: arr[i].name,
		        desc: arr[i].email,
		        icon: arr[i].id
		      };
		}
	 
	    $( "#unproject" ).autocomplete({
	      minLength: 0,
	      source: projects,
	      focus: function( event, ui ) {
	        $( "#unproject" ).val( ui.item.label );
	        return false;
	      },
	      select: function( event, ui ) {
	        $( "#unproject" ).val( ui.item.label );
	        $( "#unproject-id" ).val( ui.item.value );
	        $( "#unproject-description" ).html( ui.item.desc );
	        $( "#unproject-icon" ).attr( "src", "images/" + ui.item.icon );
	 
	        return false;
	      }
	    })
	    .autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append( '<a><img id="viewImg" src="${ctx }/main/mypage/img/download.do?id='+item.icon+'"/> '+ item.label +' <br>' + item.desc + '</a>' )
	        .appendTo( ul );
	    };
	    //이부분에 아이디를 줘서해야한다.
	    
	    
		});
	}
	
	function searchFriendUn()
	{
		var email =$('#unproject-id').val();

		location.href = '${ctx}/main/friendlist/sendFriendRequest.do?email='
				+ encodeURIComponent(email);
		
	}
	
	
	
	
	function Change(target, type) {
		if (target.value == target.defaultValue && type == 0)
			target.value = '';
		if (!target.value && type == 1)
			target.value = target.defaultValue;
	}

	function allcheck() {
		var allCheck = document.getElementById('allCheckbox');
		var acceptedRequest = document.getElementsByName('acceptedRequest');
		var arr = new Array();
		arr = acceptedRequest;
		if (allCheck.checked == true) {
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = true;
			}
		} else {
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = false;
			}
		}
	}

	function deleteAllcheck() {
		var allCheck = document.getElementById('deleteAllcheckbox');
		var acceptedRequest = document.getElementsByName('deleteFriendList');
		var arr = new Array();
		arr = acceptedRequest;
		if (allCheck.checked == true) {
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = true;
			}
		} else {
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = false;
			}
		}
	}
	
	function deleteArticleAllcheck() {
		var allCheck = document.getElementById('deleteArticleAllcheckbox');
		var acceptedRequest = document.getElementsByName('deleteArticleList');
		var arr = new Array();
		arr = acceptedRequest;
		if (allCheck.checked == true) {
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = true;
			}
		} else {
			for (var i = 0; i < arr.length; i++) {
				arr[i].checked = false;
			}
		}
	}
	
function notice(){
	
	$.get(
		'${ctx}/main/notice/article/list.do',
		function(data) {

			var $noticeList = $('#noticeList li');

			var members = new Array();
			members = data.members;
			var notices = new Array();
			notices = data.notices;
			var articles = new Array();
			articles = data.articles;

			$noticeList.empty();
			for (var i = 0; i < notices.length; i++) {
				var notice = notices[i];

				var friendName;
				for (var j = 0; j < members.length; j++) {
					if (notice.friendId == members[j].id) {
						var friendName = members[j].name;
					}
				}
				var shortContents;
				for (var k = 0; k < articles.length; k++) {
					var article = articles[k];
						if (notice.no != null) {
							if(article != null){
							if (notice.no == article.no) {
								shortContents = article.contents.substring(0, 6)+ '...';
							}
						}
					}
				}

				if (notice.level == 1) { //level 1,,,친구요청
					var el = '<a href = "${ctx}/main/friendlist.do" >';
					el += ' '
							+ friendName
							+ '님이 회원님에게 친구 요청을 하였습니다.';
					el += '</a>';
				} else if (notice.level == 2) {
					var el = '<a href = "">';
					el += ' ' + friendName
							+ '님이 회원님에게 "'
							+ shortContents
							+ '"에서 태그를 하였습니다.';
					el += '</a>';
				} else if (notice.level == 3) {
					var el = '<a href = "">';
					el += ' ' + friendName
							+ '님이 회원님에게  "'
							+ shortContents
							+ '"을 공유 하였습니다.';
					el += '</a>';
				} else if (notice.level == 4) {
					var el = '<a href = "">';
					el += ' ' + friendName
							+ '님이 회원님에게 "'
							+ shortContents
							+ '" 글을 추천 하였습니다.';
					el += '</a>';
				} else if (notice.level == 5) {
					var el = '<a href = "">';
					el += ' '
							+ friendName
							+ '님이 "'
							+ shortContents
							+ '"  게시글을 등록 하였습니다.';
					el += '</a>';
				}

				$noticeList.append(el);
			}
		});
}

function friendList(){						
	$.get(
			'${ctx}/main/friendlist/list.do',

			function(data) {
				var $tbody = $('table#mytable tbody');

				var arr = new Array();
				arr = data;
				$tbody.empty();//계속추가되니까 , 지워추고해야함
				for (var i = 0; i < arr.length; i++) {
					var member = arr[i];
					var el = '<tr>';

					el += '<td><input type="checkbox" name = "deleteFriendList" value = "'+member.id +'" /></td>';
					el += '<td><img id= "profileImg" src="${ctx }/main/mypage/img/download.do?id='+member.id+'" class= "img-rounded" onclick="friendMypage('+member.id+');"></td>';
					el += '<td><a href="${ctx}/main/mypage.do?id='+member.id+'">'+ member.email
							+ '</td>';
					el += '<td><a href="${ctx}/main/mypage.do?id='+member.id+'">' + member.name
							+ '</td>';
					el += '<td>' + member.phone
							+ '</td>';
					el += '<td>' + member.birthDate
							+ '</td>';
					el += '</tr>';

					$tbody.append(el); //실제로 추가된다.

				}

		});
}

function requestFriend(){
	$.get(
			'${ctx}/main/friendlist/requestedfriend/list.do',
			function(data) {

				var $requestList = $('table#requestedFriendList tbody');

				var notices = new Array();
				notices = data.notices;
				var members = new Array();
				members = data.members;
				$requestList.empty();
				for (var i = 0; i < notices.length; i++) {
					var notice = notices[i];

					var friendName;
					for (var j = 0; j < members.length; j++) {
						if (notice.friendId == members[j].id) {
							var friendName = members[j].name;
						}
					}

					var el = '<tr>';
					el += '<td><input type = "checkbox" name = "acceptedRequest" value = "'+notice.friendId+'"></td> ';
					el += '<td><img id= "profileImg" src="${ctx }/main/mypage/img/download.do?id='+notice.friendId+'" class= "img-rounded" onclick="friendMypage('+notice.friendId+');"></td>';
					el += '<td>' + friendName
							+ '</td>';
					el += '<td>'
							+ notice.createDate
							+ '</td>';
					el += '</tr>';
					$requestList.append(el);
				}

			});
}


function myArticles(){						
	$.get(
		'${ctx}/main/article/myarticle/list.do',

		function(data) {
			var $tbody = $('table#myarticle tbody');

			var arr = new Array();
			arr = data.articles;
			
			$tbody.empty();//계속추가되니까 , 지워추고해야함
			for (var i = 0; i < arr.length; i++) {
				var article = arr[i];
				var el = '<tr>';

				el += '<td><input type="checkbox" name = "deleteArticleList" value = "'+article.no +'" /></td>';
				el += '<td>'	+ article.no + '</td>';
				var shortContent;
				if(article.contents.length > 15 ){
					shortContent = article.contents.substring(0,17) + '.....';
				} else {
					shortContent = article.contents;
				}
				el += '<td>' + shortContent + '</td>';
				el += '<td>' + article.createDate +  '</td>';
				var level = checkLevel(article.level);
				el += '<td>' + level + '</td>';
				el += '<td>' + article.recommend + '</td>';
				el += '</tr>';

				$tbody.append(el); //실제로 추가된다.

			}

		});
}

function checkLevel(el){
	var level = el;
	if(level == 1){
		return '전체 공개';
	} else if (level ==2 ){
		return '친구 공개';
	} else if (level == 3) {
		return '비공개';
	} else if(level == 4){
		return '익명';
	}
}

	$(document)
			.ready(
					function() {

						autoSuggest();
						autoSuggestUn();
						notice();
						friendList();
						requestFriend();
						myArticles();


	});
</script>
</head>
<body>
	<div class="wrapper">
		<div class="box">
			<div class="row row-offcanvas row-offcanvas-left">

				<!-- 메뉴 -->
				<div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">

					<ul class="nav">
						<li><a class="visible-xs text-center" href="#"
							data-toggle="offcanvas"><i
								class="glyphicon glyphicon-chevron-right"></i></a></li>
					</ul>

					<ul class="nav hidden-xs" id="lg-menu">

					</ul>
					<ul class="list-unstyled hidden-xs" id="sidebar-footer">
						<li><a href="http://localhost:8080/Anonymoustab/main.do"><h3>&copy;Anoymous
									Group</h3> <i class="glyphicon glyphicon-eye-open"></i> anoymous</a>
						</li>
					</ul>

				</div>
				<!-- /메뉴 -->



				<!-- 오른쪽 메인 -->
				<div class="column col-sm-10 col-xs-11" id="main">

					<!-- top search -->
					<div class="navbar navbar-blue navbar-static-top">
						<div class="navbar-header">

							<button class="navbar-toggle" type="button"
								data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle</span> <span class="icon-bar"></span>
								<span class="icon-bar"></span> <span class="icon-bar"></span>
							</button>

							<a class="navbar-brand logo" href="${ctx}/main.do">A</a>
						</div>
						<nav class="collapse navbar-collapse" role="navigation">
						<form class="navbar-form navbar-left">
							<div class="input-group input-group-sm" style="max-width: 360px;">

								<input id="project" name="srch-term" class="form-control" id="srch-term"
									type="text" placeholder="Search">
								<div class="input-group-btn">
									<input class="btn btn-default" type="button" value="검색"
										onclick="searchFriend();">
										<input type="hidden" id="project-id"/>
								</div>
							</div>

						</form>

						<ul class="nav navbar-nav">
							<li><a href="${ctx}/main.do">
							<i class="glyphicon glyphicon-home"></i> Home</a>
							</li>
							
		                    <li><a href="${ctx}/main/mypage.do?id=${loginMemberId}">
		                    	<i class="glyphicon glyphicon-tower"></i> MyPage</a>
		                    </li>

							<li><a href="${ctx}/anonyfeed.do">
								<i class="glyphicon glyphicon-eye-open"></i> Anonymous</a>
							</li>
									
							
							
							<li class="dropdown">
		                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		                        <i class="glyphicon glyphicon-globe">Notice</i></a>
				                       <ul class="dropdown-menu" id = "noticeList">
				                          <li></li>
				                       </ul>
		                    </li>
		                    
							<li><a href="${ctx}/main/friendlist.do">
								<i class="glyphicon glyphicon-cog"></i> Management</a>
							</li>
		                    
		                    <li>
		                    	<a href="${ctx}/logout.do"><i class="glyphicon glyphicon-export"></i> Logout</a>
							</li>
						</ul>
						</nav>
					</div>

					<!-- /top nav -->

					<div class="padding">
						<div class="full col-sm-9">

							<!-- content -->
							<div class="row">

								<!-- main col left -->
								<div class="col-sm-7 col-sm-offset-2">

									<div class="panel panel-default">
										<div class="panel-thumbnail">
										
										</div>
										<div class="panel-body">
											<p class="lead">Friend Name</p>
											<p>Friend Information</p>
											<form id = "noticeForm" action="${ctx}/main/friendlist/requestedfriend/accept.do" method="post">
												<table id="requestedFriendList" class="table table-striped">
													<thead>
														<tr>
															<th><input type="checkbox" id="allCheckbox" onclick="allcheck();"></th>
															<th>사진</th>
															<th>친구 이름</th>
															<th>요청 시간</th>
														</tr>
													</thead>

													<tbody>

													</tbody>
												</table>



												<button class="btn btn-primary pull-right" 
												 onclick = "noticeFormSubmit(2)"><span class="glyphicon glyphicon-export"></span> Deny</button>
												<button id="rightMargin" class="btn btn-primary pull-right" 
													 onclick="noticeFormSubmit(1)"><span class="glyphicon glyphicon-import"></span> Accept</button>
												
											</form>
										</div>
									</div>

									<div class="well">
											<form class="form-horizontal" id="requestFriend" method="post">
												<h4>Make a friend</h4>
											<div class="form-group" style="padding: 14px;">
												<input id="unproject" class="form-control" type="text" id="tags"
													name="tags" value="Search your friend"
													onFocus='Change(this,0)' onBlur='Change(this,1)' >
											<button id="BigTopMargin" class="btn btn-primary pull-right" type="button"
												value="Request" onClick="searchFriendUn();">
												<span class="glyphicon glyphicon-search"></span> RequestToFriend</button>
												<input type="hidden" id="unproject-id"/>
												
								
									<div class="row">
										<div id="BigRightMargin" class="col-md-3">			
											<ul id="BigTopMargin" class="list-inline">
												<li><a href=""><i
														class="glyphicon glyphicon-upload"></i></a></li>
												<li><a href=""><i
														class="glyphicon glyphicon-camera"></i></a></li>
												<li><a  href=""><i
														class="glyphicon glyphicon-map-marker"></i></a></li>
											</ul>
											</div>
										</div>
									</div>			
											</form>
									</div>

									<div class="panel panel-default">
										<div class="panel-heading">
											<h4>Friend List</h4>
										</div>
										<div class="panel-body" style = "height:250px; overflow:scroll; padding:5px;">
											<form action="${ctx}/main/friendlist/deletefriendlist.do"
												method="post">
												<table id="mytable" class="table table-striped">
													<thead>
														<tr>
															<th><input type="checkbox" id="deleteAllcheckbox"
																onclick="deleteAllcheck();"></th>
															<th>Img</th>
															<th>Email</th>
															<th>Name</th>
															<th>Phone</th>
															<th>Birth Date</th>
														</tr>
													</thead>
													<tbody>

													</tbody>
												</table>

											
												<button class="btn btn-primary pull-right" type="submit"
													><span class="glyphicon glyphicon-remove"></span> Delete</button>
											</form>
										</div>


									</div>
									
									
									<div class="panel panel-default" >
										<div class="panel-heading">
											<h4>My Article</h4>
										</div>
										<div class="panel-body" style = "height:250px; overflow:scroll; padding:5px;">
											<form action="${ctx}/main/article/myarticle/delete.do" method="post">
												<table id="myarticle" class="table table-striped" >
													<thead>
														<tr>
															<th><input type="checkbox" id="deleteArticleAllcheckbox"
																onclick="deleteArticleAllcheck();"></th>
															<th>No</th>
															<th>Contents</th>
															<th>Create Date</th>
															<th>Level</th>
															<th>Recommend</th>
														</tr>
													</thead >
													
													<tbody >

													</tbody>
													
												</table>

												<button class="btn btn-primary pull-right" type="submit"
													><span class="glyphicon glyphicon-remove"></span> Delete</button>
											</form>
										</div>


									</div>
									
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>