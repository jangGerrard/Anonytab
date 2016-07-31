<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!doctype html>
<html lang="us">
<head>
	<meta charset="utf-8">
	<title>jQuery UI Example Page</title>
	<link href="${ctx}/style/jquery-ui-1.11.1.custom/jquery-ui.css" rel="stylesheet">
	<script src="${ctx}/style/jquery-ui-1.11.1.custom/external/jquery/jquery.js"></script>
	<script src="${ctx}/style/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
	<script>
$(document).ready(function() {
	
/* 	var currentPosition = parseInt($("#right_section").css("top"));   */
 
/*    $(window).scroll(function() {  
            var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.  
            alert('position'+position)
           $("#right_section").stop().animate({ "top" : (position-500) + "px" } , 200 );  
            
            
    });  */ 
   
    var currentPosition = parseInt($("#right_section").css("top"));  //이건 항상 100이야.
       $(window).scroll(function() {  
               var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다. 
			    // alert('currentPosition : '+currentPosition + '\nposition : ' +position);
               $("#right_section").stop().animate({"top":position+currentPosition+"px"},1000);  
               position =0;
       }); 
       
   	/* $(window).scroll(function()
   			{
   				
   		  		$('#right_section').animate({top: $(window).scrollTop() +"px" },{queue: false, duration: 1000});  
   			}); */



		$( "#dialog" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				},
				{
					text: "Cancel",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});
		
		// Link to open the dialog
		$( "#dialog-link" ).click(function( event ) {
			alert('testtesttest');
			$( "#dialog" ).dialog( "open" );
			event.preventDefault();
		});
});
</script>
</head>
<body>

<h1>Welcome to jQuery UI!</h1>


<!-- Dialog NOTE: Dialog is not generated by UI in this demo so it can be visually styled in themeroller-->

<p><input type = "button" id="dialog-link" value = "공유"></p>
<!-- content -->

					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>
					<h1>aaaaaaaaaaaaaaa</h1>



<div id="dialog" title="Share">
	<div class="col-sm-7 col-sm-offset-2">
		<div class="panel panel-default">
			<form class="form-horizontal"
				action="${ctx }/main/article/register.do" method="post"
				enctype="multipart/form-data">
				<div class="panel-heading">
					<p class="lead">공유하기</p>
				</div>
				
				<div class="panel-body">
				
					<select class="btn btn-default dropdown-toggle" id="articleLevel" name="articleLevel">
						<option value="1">전체공개</option>
						<option value="2">친구공개</option>
						<option value="3">비공개</option>
						<option value="4">익명</option>
					</select>
				</div>
				<textarea class="form-control" name="contents" id="contents" rows="4" cols="50" onFocus='Change(this,0)' onBlur='Change(this,1)'>Comment</textarea>
					<br/>

					
			</form>
		</div>
	</div>
</div>


<div style=" float:right; width:80px;">  
       <div id="right_section" style="position:absolute;top : 100px;right: 100px;">  
           <div><h1>따라갑시다.</h1></div>  
        </div>  
</div>  







</body>
</html>