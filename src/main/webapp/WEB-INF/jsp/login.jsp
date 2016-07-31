
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${ctx }/style/css/login/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/style/css/login/styles.css" rel="stylesheet">
<link href="${ctx }/style/css/login/login-bootstrap.min.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Login</title>

<%-- <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script src="${ctx }/style/js/login/bootstrap.min.js"></script> --%>

<script type="text/javascript"
	src="${ctx}/style/js/lib/jquery-1.11.1.js"></script>

<script type="text/javascript">


	function Change(target, type) {
		if (target.value == target.defaultValue && type == 0)
			target.value = '';
		if (!target.value && type == 1)
			target.value = target.defaultValue;
	}

	function joinForm() {
		var check = document.getElementById('forJoinForm');
		if (check.value % 2 == 0) {
			$('#registerForm').show(500);
			$('#registerForm input:first').focus();
			check.value++;
		} else {
			$('#registerForm').hide(500);
			check.value++;
		}
	}

	function check() {
		var check = true;
		var pw = document.getElementById('joinPw').value;
		var rePw = document.getElementById('joinRepw').value;

		var email = document.getElementById('joinEmail').value;
		var reEmail = document.getElementById('reMail').value;
		
		if (email.match(/^(\w+)@(\w+)[.](\w+)$/ig)) {
			if(email != reEmail){
				alert('메일이 일치 하지 않습니다. 다시 입력해 주세요.');
				$('#reMail').focus();
				return false;
			}

		} else {
			alert('메일형식과 일치하지 않습니다.');
			check = false;
			$('#joinEmail').focus();
			return check;
		}

		if (pw.length < 6) {
			alert('비밀번호는 6자이상 입력해 주세요.');
			$('#joinPw').focus();
			check = false;
			return check;
		}

		if (pw != rePw) {
			alert('비밀번호가 불일치합니다. \n비밀번호를 다시 작성하세요.');
			$('#joinRepw').focus();
			check = false;
			return check;
		}
		var reg = /(19|20)([0-9][0-9])-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])/;
		var birthDate = $('#birthDate').val().trim();
		if(!reg.test(birthDate)){
			alert('생년월일 형식이 맞지 않습니다.\n ex) 2014-01-01');
			$('#birthDate').focus();
			return false;
		} 	
		if (check) {
			var id = document.getElementById("joincheck");
			id.summit();
		} else {
			return false;

		}

	}

	$(document).ready(function() {
		alert('file not yet supported!');
		// 	var err=$('#err_alert').val();
		var err = document.getElementById('err_alert').value;
		$('#registerForm').hide();
		if (err == 1) {
			alert('ID와 비밀번호를 다시 확인해주세요!');
		} else if(err == 2){
			alert('회원 가입 실패 입니다.\n동일한 이메일이 있습니다.\n다른 이메일을 입력해 주세요!');
		}

	});
</script>
</head>
<body>
	<div class="container-full">
		<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
			<div class="row">
				<div class="col-md-8 col-xs-10 col-md-offset-2">
					<div class="modal-header">
						<h1 class="text-center">L o g i n</h1>
					</div>
					<form action="login.do" method="post">
						<div class="form-group">
							<input type="text" name="email" id="id"	class="form-control input-lg" value="Email" onFocus='Change(this,0)' onBlur='Change(this,1)' />
						</div>
						<div class="form-group">
							<input type="password" class="form-control input-lg" name="password" id="pw" value="password" onFocus='Change(this,0)' onBlur='Change(this,1)' />
						</div>
						<div class="form-group">
							<input type="submit" class="btn btn-primary btn-lg btn-block" value="Sign in" onclick="login();" /><br /> 
							<input type="button" class="btn btn-primary btn-lg btn-block" value="Register" onclick="joinForm()" /> 
							<input type="hidden" id="forJoinForm" value=0 />
								<div class="col-md-9 col-md-offset-7">
									<p>
										<span class="pull-right"> &copy; <strong>Anonymous Group.</strong> </span>
									</p>
								</div>
						</div>
					</form>
				</div>
				<div class="col-md-2"></div>
			</div>
					<form id="joincheck" action="${ctx}/join.do" method="post" enctype="multipart/form-data" onsubmit="check(); return false;">
						<div id="registerForm">
						<div class="col-md-8 col-xs-10 col-md-offset-2" >
							<div class="form-group">
								<input type="text" name="name" id="name" class="form-control input-lg" value="이름" onFocus='Change(this,0)' onBlur='Change(this,1)' />
							</div>
							<div class="form-group">
								<input type="text" name="phoneNum" id="phoneNum" class="form-control input-lg" value="핸드폰번호" onFocus='Change(this,0)' onBlur='Change(this,1)' />
							</div>
							<div class="form-group">
								<input type="text" name="email" id="joinEmail" class="form-control input-lg" value="이메일" onFocus='Change(this,0)' onBlur='Change(this,1)' />
							</div>
							<div class="form-group">
								<input type="text" name="reMail" id="reMail" class="form-control input-lg" value="이메일 재입력" onFocus='Change(this,0)' onBlur='Change(this,1)' />
							</div>
							<div class="form-group">
								<input type="password" name="password" id="joinPw" class="form-control input-lg" value="비밀번호" onFocus='Change(this,0)' onBlur='Change(this,1)' />
							</div>
							<div class="form-group">
								<input type="password" name="rePassword" id="joinRepw" class="form-control input-lg" value="비밀번호 재입력" 	onFocus='Change(this,0)' onBlur='Change(this,1)' /> 
								<span id="spnText"></span>
							</div>
							<div class="form-group">
								<input type="text" name="birthDate" id="birthDate" class="form-control input-lg" value="생년월일 ex)2014-01-01" onFocus='Change(this,0)' onBlur='Change(this,1)' />
							</div>
							<div class="form-group">
								<input type="file" name="file" id="file" class="form-control input-lg" value="사진" />
							</div>
							<input class="btn btn-primary btn-lg btn-block" type="submit" value="join" />
						</div>
</div>
						<input id="err_alert" type="hidden" value="${error_code}" />

					</form>
				</div>
			</div>
		</div>
</body>
</html>