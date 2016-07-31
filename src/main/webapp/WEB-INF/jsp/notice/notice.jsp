<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/style/css/friendlist/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/style/css/friendlist/style.min.css" rel="stylesheet">
<link href="${ctx }/style/css/friendlist/styles.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/style/js/lib/jquery-1.11.1.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
 <script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
 <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
 <script type="text/javascript"
	src="${ctx}/style/js/lib/jquery-1.11.1.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script type="text/javascript">

function allcheck(){
	var allCheck = document.getElementById('allCheckbox');
	var acceptedRequest = document.getElementsByName('acceptedRequest');
	var arr = new Array();
	arr = acceptedRequest;
	if(allCheck.checked == true){
		for(var i =0 ; i <arr.length ; i ++ ){
			arr[i].checked = true;
		}
	} else {
		for(var i =0 ; i <arr.length ; i ++ ){
			arr[i].checked = false;
		}
	}
}


$(document).ready(function(){
	
	$.get('${ctx}/main/friendlist/requestedfriend/list.do',
			function(data){
				
				var  $requestList = $('table#requestedFriendList tbody');
			
				var arr = new Array();
				arr = data;
				
				$requestList.empty();
				for(var i = 0 ; i < arr.length ; i++){
					var notice = arr[i];
					var el = '<tr>';
					el += '<td><input type = "checkbox" name = "acceptedRequest" value = "'+notice.friendId+'"></td> ';
					el += '<td>친구 아이디 : ' + notice.friendId + '</td>';
					el+=  '<td>요청 시간 : ' + notice.createDate + '</td>';
					el += '</tr>';
					$requestList.append(el);
				}
		
		
	});
	
	
});



</script>
<title>Notice</title>
</head>

<body>
<div class="wrapper">
    <div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
  
            <!-- 메뉴 -->
            <div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">
              
              	<ul class="nav">
          			<li><a class="visible-xs text-center" href="#" data-toggle="offcanvas"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
            	</ul>
               
                <ul class="nav hidden-xs" id="lg-menu">
                 
                </ul>
                <ul class="list-unstyled hidden-xs" id="sidebar-footer">
                    <li>
                      <a href="http://localhost:8080/Anonymoustab/main.do"><h3>&copy;Anoymous Group</h3> <i class="glyphicon glyphicon-heart-empty"></i> anoymous</a>
                    </li>
                </ul>
              
              	<!-- tiny only nav-->
              <ul class="nav visible-xs" id="xs-menu">
                  	<li><a class="text-center" href="#featured"><i class="glyphicon glyphicon-list-alt"></i></a></li>
                    <li><a class="text-center" href="#stories"><i class="glyphicon glyphicon-list"></i></a></li>
                  	<li><a class="text-center" href="#"><i class="glyphicon glyphicon-paperclip"></i></a></li>
                    <li><a class="text-center" href="#"><i class="glyphicon glyphicon-refresh"></i></a></li>
                </ul>
              
            </div>
            <!-- /메뉴 -->
            
            
            
             <!-- 오른쪽 메인 -->
            <div class="column col-sm-10 col-xs-11" id="main">
                
                <!-- top search -->
              	<div class="navbar navbar-blue navbar-static-top">  
                    <div class="navbar-header">
                    
                      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle</span>
                        <span class="icon-bar"></span>
          				<span class="icon-bar"></span>
          				<span class="icon-bar"></span>
                      </button>
                      
                      <a class="navbar-brand logo" href="/">A</a>
                  	</div>
                  	<nav class="collapse navbar-collapse" role="navigation">
                  	
                  <!-- top 자동검색  -->
                    <form class="navbar-form navbar-left" >
                        <div class="input-group input-group-sm" style="max-width: 360px;">
                          <input name="srch-term" class="form-control" id="srch-term" type="text" >
                          <div class="input-group-btn">
                            <input class="btn btn-default" type="button" value = "Search" onclick="searchFriend();"><i class="glyphicon glyphicon-search" value="Toggle"></i>
                          </div>
                        </div>
                    </form>
                   <!-- end of  top 자동검색 -->
                    <ul class="nav navbar-nav">
                      <li>
                        <a href="http://localhost:8080/Anonymoustab/main.do"><i class="glyphicon glyphicon-home"></i> Home</a>
                      </li>
                      <li>
                        <a href="http://localhost:8080/Anonymoustab/main.do"><i class="glyphicon glyphicon-home"></i> Notice</a>
                      </li>
                      <li>
                        <a href="http://localhost:8080/Anonymoustab/main.do"><i class="glyphicon glyphicon-home"></i> Friend</a>
                      </li>
                       <li>
                        <a href="#"><span class="badge">My page</span></a>
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
                         <div class="col-sm-5">
                           
                              <div class="panel panel-default">
                                <div class="panel-thumbnail"><img class="img-responsive" src="{ctx}/style/css/friendlist/anonymous.jpg"></div>
                                <div class="panel-body">
                                  <p class="lead">Friend Name</p>
                                  <p>Friend Information</p>
                                  <form action="${ctx }/main/friendlist/requestedfriend/accept.do" method = "post">
                                  <table id="requestedFriendList">
                                  	<thead>
                                  		<tr>
											<th><input type = "checkbox" id ="allCheckbox"  onclick="allcheck();"></th>
											<th>친구 아이디</th>
											<th>요청 시간</th>
                                  		</tr>
                                  	</thead>
                                  	
                                  	<tbody>
                                  	
                                  	</tbody>
                                  </table>
                                  
                                  
                                  
                                  <p>
                                    <img width="28" height="28" src="https://lh3.googleusercontent.com/uFp_tsTJboUY7kue5XAsGA=s28">
                                  </p>
                                  <input class="btn btn-primary pull-right"  type = "submit"  value ="Accept"  >
                                  </form>
                                </div>
                              </div>
                                                                                
                              
                           
                              <div class="panel panel-default">
                                 <div class="panel-heading"><h4>Alarm On Article</h4></div>
                                  <div class="panel-body">
                                   	<form action="" method="post">
		            					<h1>list</h1>
							                      <table id = "mytable">
							                      	<thead>
							                      		<tr>
							                      			<th>Article No</th>
							                      			<th>Contents</th>
							                      			<th>Create Date</th>					                      			
							                      		</tr>
							                      	</thead>
							                      	<tbody>
							                      		
							                      	</tbody>
							                      </table>
							                      
							                       <input class="btn btn-primary pull-right" type="submit"  value ="Delete">
	                              	</form>
                                  </div>
                           
                           		
                 <!-- main col right -->
                          <div class="col-sm-7">
                               
                               
                       </div><!--/row-->
                                         
                        <div class="row" id="footer">    
                          <div class="col-sm-6">
                            
                          </div>
                          <div class="col-sm-6">
                        </div>
                        </div>
                      
                      <hr>
                      
                      <h4 class="text-center">
                      <a href="http://bootply.com/96266" target="ext">&copy;Anoymous Group</a>
                      </h4>
                        
                      <hr>
                        
                      
                    </div><!-- /col-9 -->
                </div><!-- /padding -->
            </div>
            <!-- /main -->
          
        </div>
    </div>
</div>


<!--post modal-->
<div tabindex="-1" class="modal fade" id="postModal" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>
			Update Status
      </div>
      <div class="modal-body">
          <form class="form center-block">
            <div class="form-group">
              <textarea class="form-control input-lg" autofocus="" placeholder="What do you want to share?">What do you want to share?</textarea>
            </div>
          </form>
      </div>
      <div class="modal-footer">
          <div>
          <button class="btn btn-primary btn-sm" aria-hidden="true" data-dismiss="modal">Post</button>
            <ul class="pull-left list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>
		  </div>	
      </div>
  </div>
  </div>
</div>

</div>
</body>
</html>