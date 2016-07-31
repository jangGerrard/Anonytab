<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AUTO SUGGEST</title>

<script type="text/javascript" src="${ctx}/style/js/lib/jquery-1.11.1.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<script type="text/javascript">

function check()
{
// 	var cherker=$('#chex');
	var cherker = document.getElementById('chex');
	if(cherker.checked==true)
	{
	alert('check');	
		var $div=$('#divtest');
		$div.html("zzzzzzzzzz");
	
	}
	else
	{
	alert('uncheck');	
		
		
	}
	
	
	
}



function searchFriend()
{
	var email =$('#project-id').val();
	alert('str = '+email);
	
	
}




$(document).ready(function() {
	
	$.get('${ctx}/main/auto/tag.do',
			function(data) {
	alert('ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ');
	
	var arr=new Array();
	arr= data;
	
 		var projects=new Array();
	for(var i = 0 ; i<arr.length;i++)
	{
	projects[i]={
	        value: arr[i].email,
	        label: arr[i].name,
	        desc: arr[i].email,
	        icon: "jquery_32x32.png"
	      };
	}
	

	
//     var projects = [
//       {
//         value: "jquery",
//         label: "jQuery",
//         desc: "the write less, do more, JavaScript library",
//         icon: "jquery_32x32.png"
//       },
//       {
//         value: "jquery-ui",
//         label: "jQuery UI",
//         desc: "the official user interface library for jQuery",
//         icon: "jqueryui_32x32.png"
//       },
//       {
//         value: "sizzlejs",
//         label: "Sizzle JS",
//         desc: "a pure-JavaScript CSS selector engine",
//         icon: "sizzlejs_32x32.png"
//       }
//     ];
 
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
        .append( "<a>" + item.label + "<br>" + item.desc + "</a>" )
        .appendTo( ul );
    };
    
    
    
	});
    
    
  });
  
  
  
</script>


</head>
<body>
<h1>AUTO SUGGEST</h1>

 <div id="project-label">Select a project (type "j" for a start):</div>
<!-- <img id="project-icon" src="images/transparent_1x1.png" class="ui-state-default" alt=""> -->
<input id="project" type="text"/>
<input type="button" value="검색" onclick="searchFriend();" />
<p id="project-description">이거</p>
 
<input type="hidden" id="project-id"/>
<br/>
<br/>
<br/>
<br/>

<input id="chex" type="checkbox" onclick="check();"/> 체크!
<iframe width="720" height="438" src="http://serviceapi.nmv.naver.com/flash/convertIframeTag.nhn?vid=6ABC167BEC0780E6413521C155DA0D3838A5&outKey=V1241480b8221cdafe97cb21fa7062efa1c0296e882e13a491697b21fa7062efa1c02" frameborder="no" scrolling="no"></iframe>
<iframe width="720" height="438" src="//www.youtube.com/embed/TsuwPott5lk" frameborder="no" scrolling="no"></iframe>
<div id="divtest">여기에값을</div>
<nobr></nobr>
</body>
</html>