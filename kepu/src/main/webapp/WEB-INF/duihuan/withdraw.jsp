<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>提现</title>
<link href="${pageContext.request.contextPath}/css/mui.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/mui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/flexible.js"></script>
<style>
body {
	background: #f8f8f8;
}

.mui-content {
	margin-top: 54px;
}

.withdraw .zh {
	display: flex;
	align-items: center;
	height: 1.573333rem;
	padding: 0 .4rem;
	background: #f8f8f8;
}

.withdraw .zh span {
	color: #4d4d4d;
	font-size: .426667rem;
}

.zh_lx {
	display: inline-flex;
	align-items: center;
	margin-left: .733333rem;
}

.zh_lx img {
	width: .56rem;
	height: .56rem;
	margin-right: .266667rem;
}

.zh_lx {
	color: #4d4d4d;
	font-size: .426667rem;
}

.with_money {
	height: .906667rem;
	padding: 0 .4rem;
	background: #efeff4;
	color: #929292;
	line-height: .906667rem;
	font-size: .373333rem;
}

.with_input {
	padding: 0 .4rem;
	background: #f8f8f8;
}

.with_input>div {
	display: flex;
	align-items: center;
	height: 1.333333rem;
	border-bottom: 1px solid #e2e2e2;
}

.with_input>div:last-of-type {
	border-bottom: none;
}

.with_input input {
	padding: 0;
	margin-bottom: 0;
	border: none;
	color: #929292;
	font-size: .373333rem;
}

.with_input>div span {
	margin-right: .453333rem;
	color: #4d4d4d;
	font-size: .373333rem;
}

.with_input .yzm {
	width: 40%;
}

.with_input .countdown {
	width: 2.533333rem;
	height: .8rem;
	border: 1px solid #5296d4;
	color: #5296d4;
	font-size: .373333rem;
	text-align: center;
}

.with_input_ok {
	margin-top: 1.213333rem;
	text-align: center;
}

.with_input_ok input {
	width: 9.2rem;
	height: 1.333333rem;
	border-radius: 1.333333rem;
	background: #5296d4;
	color: #fff;
	font-size: .48rem;
}

.with_input_ok span {
	display: block;
	margin-top: .373333rem;
	color: #929292;
	font-size: .373333rem;
}

.fs_yzm {
	display: none;
	width: 3.453333rem;
	height: 1.066677rem;
	margin: .746667rem auto 0 auto;
	background: #bababa;
	color: #fff;
	font-size: .373333rem;
	line-height: 1.066677rem;
	text-align: center;
}
</style>
</head>

<body>

	<header class="mui-bar mui-bar-nav" style="padding-right: 15px;">
	<h1 class="mui-title">提现</h1>
	<a href="${pageContext.request.contextPath}/duihuan/detaillist">
		<h5>
			<img src="${pageContext.request.contextPath}/images/back@2x.png"
				height="20" width="11.4" style="margin-top: 16px;" />
		</h5>
	</a> 
	</header>
	<main class="mui-content">
	<div class="withdraw">
		<div style="height: .266667rem; background: #efeff4;"></div>
		<div class="zh">
			<span>提现账户</span>
			<div class="zh_lx">
				<img src="${pageContext.request.contextPath}/images/zfb.png" alt="z" />支付宝
			</div>
		</div>
		<div class="with_money">
			<span>可提现金额${sessionScope.amount}元</span>
			<input type="hidden" value="${sessionScope.amount}" id="amount" name="amount">
			<input type="hidden" value="${sessionScope.phone}" id="phone" name="phone">
		</div>
		<div class="with_input">
			<div>
				<input type="text" id="account" name="account"  placeholder="请输入支付宝号" />
			</div>
			<div>
				<input type="text" name="username" id="username" placeholder="请输入真实姓名" />
			</div>
		</div>
		<div style="height: .266667rem; background: #efeff4;"></div>
		<div class="with_input_ok">
			<input type="button" value="立即提现" /> <span>提现2-5个工作日到账</span>
		</div>
		<div class="fs_yzm">验证码已发送</div>
	</div>
	</main>
	<script>		
		$(document).ready(function() {
			$('.with_input_ok input').on('click', function() {
				var account=$("#account").val();
				var username=$("#username").val();
				var amount=$("#amount").val();
				if(account==""){
					alert("支付宝账号错误");
					return false;
					
				}else if(username==""){
					alert("用户名错误");
					return false;
					
				}else if(amount=="" || amount < 0.1){
					alert("金额太小");
					return false;
				}else{
					$.ajax({
					      url: "${pageContext.request.contextPath}/duihuan/withdrawPay",//使用ajax需要添加一个以ashx结尾的一般处理程序，服务端处理，直接返回给js。不用刷新页面，url：""，里面填写一般处理程序的路径
					      data: {
					    	  account:account,
					    	  username:username,
					    	  amount:amount
					    	  
					      },//用来存放要提交到服务端处理的数据，如果没有，就不填
					      type: "post",//向服务器发送请求
					      dataType: "json",//服务端将执行完的数据返回给js，json返回的数据格式{"自定义名称":"值","":""}，如果返回一堆对象数据就是[{"":""},{":""},{}....]
					      success: function (data) {//返回的json数据到自定义na中，因为返回的是一组数据，以键值对的形式，na可以直接点出每一条的键获取到值
							if(data==1){
								location.href="${pageContext.request.contextPath}/duihuan/withOk"
							}else{
								alert("提现失败,请稍后再试...");
								return false;
							}
					     },error:function(){
					    	 alert("请求在验证...请稍后");
					     }
					});
				}
			})
		})
	</script>

</body>

