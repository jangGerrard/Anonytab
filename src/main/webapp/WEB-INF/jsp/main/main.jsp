<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<c:set var = "loginMemberId" value = "<%=session.getAttribute(\"loginMemberId\")%>" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link href="${ctx}/style/jquery-ui-1.11.1.custom/jquery-ui.css" rel="stylesheet">
<link href="${ctx}/style/css/main/main.css" rel="stylesheet">
<link href="${ctx}/style/css/friendlist/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/style/css/main/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/style/css/main/styles.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx}/style/css/friendlist/autocomplete.css"><!-- zindex -->
<!-- VIDEO VIDEO -->
<script src = "${ctx}/style/video-js/video.js"></script>
<link href="${ctx}/style/video-js/video-js.css" rel="stylesheet" type="text/css">
<!-- VIDEO VIDEO -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main home</title>
<script type="text/javascript"
   src="${ctx}/style/js/lib/jquery-1.11.1.js"></script>
    <script src="${ctx }/style/js/main/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<!-- 공유하기 -->
<script src="${ctx}/style/jquery-ui-1.11.1.custom/external/jquery/jquery.js"></script>
<script src="${ctx}/style/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
<!-- 공유하기 -->
<script src="${ctx}/style/js/lib/jquery.timeago.js" type="text/javascript"></script>
<script type="text/javascript">
function goKosta(){
	location.href = 'http://www.samsung.co.kr';
}
function goKwang(){
	location.href = 'http://www.samsungtechwin.co.kr';
}
function goDong(){
	location.href = 'www.kw.ac.kr';
}



function onContentSubmit(){
	var form = document.getElementById('contentSubmit');
	var content = document.getElementById('contents').value;
	
	if(content == '' || content == '게시글을 입력해 주세요.') {
		alert('게시글을 입력해 주세요.');
		return false;
	} else {
		form.submit();
	}
}

function Change(target, type) {
   if (target.value == target.defaultValue && type == 0)
      target.value = '';
   if (!target.value && type == 1)
      target.value = target.defaultValue;
}

function dialLogClose(){
   $( "#dialog" ).dialog({
      autoOpen: false,
      width: 400,
      buttons: [
         {
            text: "Ok",
            click: function() {
               $( this ).dialog( "close" );
               $( "#shareform" ).submit();
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
}
function dialClick(no){
   // Link to open the dialog
      $( "#dialog" ).dialog( "open" );
      $("#shareArticleNo").val(no);
      event.preventDefault();
}


   var page = 10;

   function doSomething(no)//이미지 src 가 없는 것들은 표시 안한다
   {
      $('#newsfeedImg'+no).hide();   
   }

   function videoError()
   {
      var myvid = document.getElementById('example_video_1');

       if (!myvid.error) {
      $('#example_video_1').hide();

      }
      
   }
   
   function checkRecommend(no)
   {
      var cherker = document.getElementById('check'+no);
      if(cherker.checked==true)
      {
         $.get('${ctx}/main/article/recommend.do?check='+true+'&no='+no, function(data) {
            $('#recommendCount'+no).html(data.recommend);
         
         });
      }
      else
      {
         $.get('${ctx}/main/article/recommend.do?check='+false+'&no='+no, function(data) {
            $('#recommendCount'+no).html(data.recommend);

         });
      }      
      

   }
   
   
   function commentInsert(no) {
      var loc=$('#main').scrollTop();
      var email =$('#commentInput-id'+no).val();
      
      var contents = $('#commentInput' + no).val();

      $.get('${ctx}/main/comment/insert.do?no=' + no + '&contents='
              + contents + '&limit=' + page+'&tag='+email,
              function(data) {

            show(data);
            commentBox[no]=true;
            $('div#' + no).show();
            $('#main').scrollTop(loc);
         });

   }

   var commentBox=new Array();
   function comment(el) {
      var no = el;

      
      if(commentBox[no])
      {
         $('div#' + no).hide(500);
         commentBox[no]=false;
      }
      else
      {
         $('div#' + no).show(500);
         commentBox[no]=true;
      }
      
   }

   function scrollShow() {
      $.get('${ctx}/main/newsfeed/json.do?limit=' + page, function(data) {
         page += 10;
         show(data);

      });

   }


   function friendMypage(id) {
      location.href = '${ctx}/main/mypage.do?id=' + id;
   }

   function showPop() {
      var popDiv = document.getElementById('article');
      popDiv.style.display = '';
   }

   function hidePop() {
      var popDiv = document.getElementById('article');
      popDiv.style.display = 'none';

   }

   function showDetail(no) {

      showPop();

      $.get(
                  '${ctx}/main/article/detail/list.do?no=' + no,
                  function(data) {

                     var $popUpDiv = $('div#popup');

                     $popUpDiv.empty();
                     if (data.imgPath != null) {
                        var el = '<input type="button" class="close" value = "x" onclick="hidePop();">'
                        el += '<ul>';
                        el += '<li>' + data.article.no + '</li>';
                        el += '<li>' + data.article.contents + '</li>';
                        el += '<li>' + data.memberName + '</li>';
                        el += '<li> ' + data.article.createDate
                              + '</li>';
                        el += '<li>' + data.article.level + '</li>';
                        el += '</ul>';
                        el += '<img src = "${ctx}/main/article/detail/download.do?no='
                              + data.article.no + '"/>';
                        el += '</div>';
                     } else {
                        var el = '<input type="button" class="close" value = "x" onclick="hidePop();">'
                        el += '<ul>';
                        el += '<li>' + data.article.no + '</li>';
                        el += '<li>' + data.article.contents + '</li>';
                        el += '<li>' + data.memberName + '</li>';
                        el += '<li> ' + data.article.createDate
                              + '</li>';
                        el += '<li>' + data.article.level + '</li>';
                        el += '</ul>';
                        el += '</div>';
                     }

                     $popUpDiv.append(el);
                  });

      $('articleDetail').show();
   }

   //auto로 해서 친구 
   function searchFriend() {
      //location.href="친구의 상세페이지";
      var email =$('#project-id').val();

      location.href = '${ctx}/main/goto/friend.do?email='
            + encodeURIComponent(email);

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
   
   function autoKeyPress(no)
   {
      if(window.event.keyCode==64)
      {
         autoTagSuggest(no);
      }
   }
   
   function autoTagSuggest(no)
   {
      $.get('${ctx}/main/auto/tag.do',
            function(data) {   
      var arr=new Array();
      arr= data;
      
          var projects=new Array();
      for(var i = 0 ; i<arr.length;i++)
      {
      projects[i]={
              value: arr[i].email,
              label: '@'+arr[i].name+'@',
              desc: arr[i].email,
              icon: arr[i].id
            };
      }

       $( "#commentInput"+no ).autocomplete({
         minLength: 0,
         source: projects,
         focus: function( event, ui ) {
           $( "#commentInput"+no ).val( ui.item.label );
           return false;
         },
         select: function( event, ui ) {
           $( "#commentInput"+no ).val( ui.item.label );
           $( "#commentInput-id"+no ).val( ui.item.value );
           $( "#commentInput-description"+no ).html( ui.item.desc );
           $( "#commentInput-icon"+no ).attr( "src", "images/" + ui.item.icon );
    
           return false;
         }
       })
       .autocomplete( "instance" )._renderItem = function( ul, item ) {
         return $( "<li>" )
           .append( '<a><img id="viewImg" src="${ctx }/main/mypage/img/download.do?id='+item.icon+'"/> '+ item.label +' <br>' + item.desc + '</a>' )
           .appendTo( ul );
       };
       
       
       
      });
       
       

   }
   
   

   function clear() {
      var contents = document.getElementById('contents');
      contents.value = '';
   }

   function rowExtend() {
      var contents = document.getElementById('contents')
      if (contents.rows < 6) {
         contents.rows++;
      }
   }

   function divCatch() {

   }

   function show(data) {


      var $table = $('div#cssNewsfeed ');
      $table.empty();
      $.each(data.articles,function(i, article) {
    	  			var member;
    	  			for(var l = 0; l < data.members.length ; l++){
    	  				if(data.members[l].id == data.articles[i].createMemberId){
    	  					member = data.members[l];
    	  				}
    	  			}

                     var el = '<div class="panel panel-default">';
                     
                     el+='<div class="btn-group">';
                     
                     
                     
//                      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!헤더처럼 연한그부분
                     el+='<div class=row>';
                      el+='<div class="panel-heading">';
                       el+='<div class="btn-group btn-group-justified">';
                     
                         el+='<div class="col-md-7">';
                     
                           el+='<img id= "profileImg" src="${ctx }/main/mypage/img/download.do?id='+member.id+'" class= "img-rounded" onclick="friendMypage('+member.id+');">';
                  
                         el+='</div>';
                     
                     el+='<div class="col-md-5">';
                       
                         var myDate=jQuery.timeago(article.createDate);//article.createDate
                          el +='<class="well well-sm" onclick="friendMypage('+member.id+');"/>'+'<nobr><p>&nbsp;'+member.name+'</p></nobr>';
                         el += '<class="well well-sm"/>'+'<nobr><p>&nbsp;'+myDate+'</p></nobr>';
                         
                         
                       el+='</div>';
                     el+='</div>';
                     
                       el+='</div>';
                      el+='</div>';
                     el+='</div>';
//                      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!헤더처럼 연한그부분 끝                  
                     
                     
                     
                     
//                       !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!뉴스피트이미지!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                     el+='<div class="row">';
                     el+='<div class="col-xs-1 col-md-1">';
                     el+='</div>';
                        el+='<div class="col-xs-10 col-md-11">';
                           el+='<div class="panel-body">';
                           el+='<p onclick=showDetail('+ article.no + ');>' + article.contents+ '</p>';
                           
                           
                        
                           var imgPath = '${ctx}/main/article/detail/download.do?no='+article.no;
                           
                           el+='<div><img id="newsfeedImg'+article.no+'" src="'+imgPath+'" class= "img-responsive" onError="doSomething('+article.no+');" ></div>';
                           
                           el += '<div><video id="newsfeedVideo'+article.no+'" class = "video-js vjs-default-skin" controls preload="none" width="400" height="264" data-setup="{}">';
                           el += '<source  src = "${ctx}/main/article/detail/download/video.do?no='+article.no+'" type = "video/mp4" onError="doVideo('+article.no+');" >';
                           el +='</video>';
                           el +='</div>';
                                    
                           
                           el+='<div></div>';
                           el+='<div></div>';
//                             !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!뉴스피트이미지!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!                           
                              
                        el+='</div>';
                     el+='</div>';
                     
                     
                     
                     
//                      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!댓글부분이다
                     el += '<div id="'+article.no+'">';
                     el += '<ul>';
                     
                     var commentCount=0;
                     $.each(article.comments, function(j, comment) {
                        //el += '<li> <img src="" /></li>'
                        //       ?id=commentMembers[i+j].createMemberId
                        
                              var commentName=null;
                              $.each(data.members,function(k,member)
                              {
                                 if(comment.createMemberId==member.id)
                                 {
                                    commentName=member.name;
                                 }
                                 
                              });
                              
                                                         
                        el += '<img id= "minProfileImg" src="${ctx }/main/mypage/img/download.do?id='+comment.createMemberId+'" class= "img-rounded"><p class="commentP"> ['
                              + '<a href="${ctx}/main/mypage.do?id='+comment.createMemberId+'">'+commentName+'</a>'
                              + '] : ' + comment.contents + '<p>';
                        commentCount=(j+1);
                     })
                     el+='<p></p>';
                     el+='<div class="col-xs-1 col-md-1">';
                     el+='</div>';
                  el+='<div class="col-xs-10 col-md-11">';
                  
                     el+='<div><span class="glyphicon glyphicon-pushpin"></span></div>'
                     el+='<div class="col-lg-8">';
                      el+='<div class="input-group">';
                            el +='<input id ="commentInput'+article.no+'" type="text" class="form-control" onkeypress="autoKeyPress('+article.no+');"/>';
                            el+='<span class="input-group-btn">';
                            
                               el+=' <button class="btn btn-default" onclick="commentInsert('+article.no+');"><span class="glyphicon glyphicon-paperclip"></span> Post</button>';
                            
                           el+='<input type="hidden" id="commentInput-id'+article.no+'"/>';//여기에서 히든에 값이 안들어가는게 문제이다.
                        el+='</span>';   
                      el+='</div>';
                     el+='</div>';   
                       el += '</ul>';//일단 빈상태로 둔다 그렇게 두다가
                     el += '</div>';
//                      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!댓글부분이다 여기까지                     
                     el+='</br>';
                     
                     el+='</div>';
                     
                     
                        el +='<div class="panel-heading">'; 
                     
                        el += '<button id="inputFocus'+article.no+'" class="btn btn-primary pull-right" onclick="comment('
                        + article.no + ');"><span class="glyphicon glyphicon-paperclip"></span> 댓글</button>';
                        
                           el += '<button class="btn btn-primary pull-right" id = "dialog-link'+article.no+'" onclick="dialClick('+article.no+');"><span class="glyphicon glyphicon-share"></span> 공유</button>';
                           
                           el += '<input id="check'+article.no+'"type="checkbox" onclick="checkRecommend('
                                 + article.no + ');">추천</input>';
                        el+='</div>';
                     
                     el +='<div class="panel-heading">'; 
                     
                        el+='<div><nobr>['+commentCount+'] 개의 댓글 ['+'<span id="recommendCount'+article.no+'">'+article.recommend+'</span>'+'] 의 추천을 받았습니다.'+'</nobr></div>';
                        el+='</div>';
                  
                        
                        
                        
                     el+= '</div><br/><br/>'
                     $table.append(el); //실제로 추가된다.
                     $('div#' + article.no).hide();//이렇게 시작할때는 댓글 창을 닫아놓는다
                     
                     var videoArr=new Array();
                     videoArr=data.videos;
                     
                     $('#newsfeedVideo'+article.no).hide();
                     for(var i=0;i<videoArr.length;i++)
                     {
                     $('#newsfeedVideo'+videoArr[i]).show();
                     }
                  });

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
   
   

   $(document).ready(function() {
      $('articleDetail').hide();
      autoSuggest();
      
      $('#contents').focus(clear);
      $('#contents').focus(rowExtend);
      
      
      var currentPosition = parseInt($("#right_section").css("top"));  //이건 항상 100이야.
      $('#main').scroll(function() {  
              var position = $('#main').scrollTop(); // 현재 스크롤바의 위치값을 반환합니다. 
			    // alert('currentPosition : '+currentPosition + '\nposition : ' +position);
              $("#right_section").stop().animate({"top":position+currentPosition+"px"},500);  
              position =0;
      }); 

         $('#main').scroll(
                function() {
                   if ($('#scrollDiv').height() - 200 <= ($(document).height()+$('#main').scrollTop()) ) {
                      
                      scrollShow();
                   }

         });

      notice();

      $.ajax({
         url : '${ctx}/main/newsfeed/json.do',
         dataType : 'json',
         success : function(data) {

            show(data);
            

            
            
         }
      });
      
      dialLogClose();
      

      
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
                  <li><a class="visible-xs text-center" href="#" data-toggle="offcanvas">
                  <i class="glyphicon glyphicon-chevron-right"></i>
                  </a></li>
               </ul>

               <ul class="nav hidden-xs" id="lg-menu">

               </ul>
               <ul class="list-unstyled hidden-xs" id="sidebar-footer">
                  <li><a href="http://localhost:8080/Anonymoustab/main.do">
                  <h3>&copy;AnoymousGroup</h3>
                  <i class="glyphicon glyphicon-eye-open"></i> anoymous</a>
                  </li>
               </ul>

            </div>
            <!-- /메뉴 -->


            <!-- 전체 오른쪽메뉴 -->

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

               <!-- top search -->

               <div  class="padding">
                  <div id="scrollDiv" class="full col-sm-9">

                     <!-- content -->
                     <div class="row">

                        <!-- main col left -->
                        <div class="col-sm-7 col-sm-offset-2">



                           <div class="panel panel-default">
                              <form class="form-horizontal" id = "contentSubmit" onsubmit="onContentSubmit(); return false;"
                                 action="${ctx }/main/article/register.do" method="post"
                                 enctype="multipart/form-data" >
                                 <div class="panel-heading">
                                    <p class="lead">Update</p>
                                 </div>
                                 
                                 <div class="panel-body">
                                 
                                    <select class="btn btn-default dropdown-toggle" id="articleLevel" name="articleLevel">
                                       <option value="1">전체공개</option>
                                       <option value="2">친구공개</option>
                                       <option value="3">비공개</option>
                                       <option value="4">익명</option>
                                    </select>
                                 </div>
                              <div id="duplexMargin">
                                 <textarea class="form-control" name="contents" id="contents" 
                                    rows="4" cols="48">게시글을 입력해 주세요.</textarea>
                                    </div>
                               <div id="rightMargin" class="row">
                                 
                                       <button class="btn btn-primary pull-right"
                                       type="submit"><span class="glyphicon glyphicon-paperclip"></span> Post</button>
                                 
                              </div>   
                                 <div class="row">
                              
                                    <div id="leftMargin" class="col-md-2">
                                       <div class="form-group">
                                                <label for="exampleInputFile">Photo File</label>
                                          <span class="glyphicon glyphicon-picture"></span> <input class="c_frm_file" type="file" id="file" name="file">
                                          <br/>
                                             <label for="exampleInputFile">Video File</label>
                                          <span class="glyphicon glyphicon-facetime-video"></span> <input class="c_frm_file" type="file" id="videofile" name="videofile">
                                       </div>
                                    </div>   
                                 </div>
                              </form>
                           </div>

                        </div>
<br/><br/>
      <!--뉴스피드!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->

                        <div id="cssNewsfeed" class="col-sm-7 col-sm-offset-2">

<!--                            <div class="panel panel-default"> -->
<!--                                     <div class="panel-heading"> <h5>NewsFeed</h3> </div> -->
<!--                               <div class="panel-body"> -->

<!--                                     <div id="newsfeedtable"></div> -->


<!--                                     <div id="article"></div> -->
<!--                               </div> -->

<!--                            </div> -->
                        </div>
                      
      <!--뉴스피드!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!끝-->
    <div class="row">
    <div class="col-md-2"></div>
								<div style=" float:right; width:80px; ">  
								       <div id="right_section" style="position:absolute;top : 72px;left: 75%;">  
													
													<div class="panel panel-default" style="width: 200px;">
						                                 <div class="panel-heading" style="height: 45px;">
						                                    <p class="lead">Banner</p>
						                                 </div>						                                 
						                                 <div class="panel-body">
						                                   <img src="${ctx}/style/img/oneBanner.jpg" style="width: 160px;" border="0" onclick="goKosta();">
						                                 	<a href="http://www.eclipse.org/">http://www.eclipse.org/</a>
						                               		
						                                 </div>
						                           </div>
						                           <div class="panel panel-default" style="width: 200px;">
						                                 <div class="panel-heading"  style="height: 45px;">
						                                    <p class="lead">Banner</p>
						                                 </div>						                                 
						                                 <div class="panel-body">
						                                 	<img id="viewImg" src="${ctx}/style/img/twoBanner.jpg"  style="width: 160px;" border="0" onclick="goKwang();"/>
						                                 	<a href="http://www.mysql.com/">http://www.mysql.com/</a>
						                                 </div>
						                           </div>
						                           	<div class="panel panel-default" style="width: 200px;">
						                                 <div class="panel-heading"  style="height: 45px;">
						                                    <p class="lead">Banner</p>
						                                 </div>						                                 
						                                 <div class="panel-body">
						                                 	<img id="viewImg" src="${ctx}/style/img/threeBanner.jpg" style="width: 160px;" border="0" onclick="goDong();"/>
						                                 	<a href="http://tomcat.apache.org/">http://tomcat.apache.org/</a>
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
      </div>


<!-- content -->

<div id="dialog" title="공유하기">
   
         <form class="form-horizontal" id = "shareform" action="${ctx }/main/share.do" method="post" >
            
            
               <select class="btn btn-default dropdown-toggle" id="shareLevel" name="shareLevel">
                  <option value="1">전체공개</option>
                  <option value="2">친구공개</option>
                  <option value="3">비공개</option>
               </select>
                 <input type = "hidden" id = "shareArticleNo" name="shareArticleNo" value = "0" >
         
            <textarea class="form-control" name="shareContents" id="shareContents" rows="8" cols="195" onFocus='Change(this,0)' onBlur='Change(this,1)'>Comment</textarea>
               <br/>
         </form>
      
</div>
      
      
   </div>
</body>
</html>