<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>360大数据登陆</title>
	<link rel="stylesheet" type="text/css" href="{{URL::asset('static/css/login.css') }}">
	<link rel="stylesheet" type="text/css" media="screen and (min-device-width: 1600px),screen and （max-width： 1920px）, screen and （max-device-width： 1920px)" href="{{URL::asset('static/css/login.css') }}" >
	<link rel=“stylesheet” type="text/css" media="screen and (min-device-width:1280px),screen and （max-width： 1440px）, screen and （max-device-width： 1440px)" href=“{{URL::asset('static/css/login.css') }}”/>
</head>

<body>
	<div class="wrapper">
		<div class="header">
			<a href='login'><img class="logo" src="{{URL::asset('static/image/small_logo.png') }}" alt=""></a>
			<ul class="nav">
				<li><a href="#">登录</a></li>
			<!--	<li><a href="#">注册</a></li>-->
				<li><a href="#">帮助</a></li>
			</ul>
		</div>
		<div class="content" id='myform'>
			<p>登录</p>
			<div class="op">
				<form class="op_form" action="a/login" method='post'>
					<input class="input" type="text" name="email" id="email" placeholder="账户邮箱">
					<input class="input" type="password" name="password" id="password" placeholder="密码">
					{!! Geetest::render() !!}
					<input class="button" type="submit" name="button" id="button" value="登录">
				</form>
			</div>
			<div class="footer">
			<a class="wj" href="#">忘记密码?</a> 
			</div>
		</div>
	</div>
<script>
  $('#button').click(function(){
  	 var em=document.getElementById("email").value;
  	 var emailPat=/^(.+)@(.+)$/;
	 var matchArray=em.match(emailPat);
	 if(em==''){
	 	alert("email为空！！")
	 	return false;
	 }
  	 if(matchArray==null){
  	 	alert("电子邮件地址必须包括 ( @ 和 . )！！");
  	 	return false;
  	 }
  	 
     var pd=document.getElementById("password").value;
     if(pd.length<6){
     	alert("密码长度必须大于等于6！！")
	 	return false;
     }
     //document.getElementById("myform").submit();
  });
     /*
     var captchaObj=document.getElementById("geetest-captcha").value;
     var validate = captchaObj.getValidate();
     if (!validate) {
		alert('请正确完成验证码操作     by服务端');
		return false;
	 }
	 */
	/*
     $.ajax({
		 url:'http://123.206.184.214/a/login',
		 type:'POST',
		 dataType:'json',
		 data:{
		     	'email':$('#email').attr("value"),
		 		'password':$('#password').attr("value")
		 		},
		 success:function(data){
            if(data.code==1){
				alert("code = 1");
			}else if (data.code=='-1'){
                //window.history.back(-1)		
				alert("code = -1");	
            }
		 }
	 })
  });
  */
</script>
</body>
</html>


