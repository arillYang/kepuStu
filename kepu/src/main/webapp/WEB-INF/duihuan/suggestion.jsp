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
<title>意见反馈</title>
<link href="${pageContext.request.contextPath}/css/mui.min.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/mui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/flexible.js"></script>
<style>
ol, ul {
	margin: 0;
	padding: 0;
}

li {
	list-style: none;
}

body {
	background: #f8f8f8;
}

.mui-content {
	margin-top: 54px;
}

.sug_lx {
	height: 2.586667rem;
	padding: 0 .4rem;
}

.sug_lx span {
	display: block;
	padding-top: .4rem;
	padding-bottom: .223333rem;
	color: #666;
	font-size: .373333rem;
}

.mui-checkbox2 {
	margin-left: 1.546667rem;
}

.mui-input-row.mui-checkbox {
	display: inline-block;
}

.mui-checkbox label {
	font-size: .426667rem;
	color: #333;
}

.mui-checkbox.mui-left input[type=checkbox] {
	right: 0;
	left: 0;
}

.mui-checkbox.mui-left label {
	padding-left: .866667rem;
}

.mui-checkbox input[type=checkbox]:checked:before, .mui-radio input[type=radio]:checked:before
	{
	color: #5296d4;
}

.sug_cont {
	padding: 0 .4rem .4rem .4rem;
}

.sug_cont span {
	display: block;
	margin: .4rem 0;
	color: #999;
	font-size: .4rem;
}

.sug_cont textarea {
	height: 2.666667rem;
	margin-bottom: 0;
}

.sug_cont input {
	margin-top: .15rem;
	margin-bottom: 0;
	color: #999;
	font-size: .346667rem;
}

.with_input_ok {
	margin-top: .693333rem;
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
</style>
</head>

<body>

	<header class="mui-bar mui-bar-nav" style="padding-right: 15px;">
	<h1 class="mui-title">意见反馈</h1>
	<a href="index.html">
		<h5>
			<img src="${pageContext.request.contextPath}/images/back@2x.png"
				height="20" width="11.4" style="margin-top: 16px;" />
		</h5>
	</a> </header>
	<main class="mui-content">
	<div class="sug_lx">
		<span>选择反馈类型</span>
		<div class="mui-input-row mui-checkbox mui-left mui-checkbox1">
			<label>产品建议</label> <input name="checkbox" type="checkbox">
		</div>
		<div class="mui-input-row mui-checkbox mui-left mui-checkbox2">
			<label>功能建议</label> <input name="checkbox" type="checkbox">
		</div>
	</div>
	<div style="height: .266667rem; background: #efeff4;"></div>
	<div class="sug_cont">
		<span>请输入您的反馈，我们将不断为您改进</span>
		<textarea placeholder="希望产品..."></textarea>
		<input type="text" placeholder="填写您的手机或邮箱" />
	</div>
	<div style="height: .266667rem; background: #efeff4;"></div>
	<div class="with_input_ok">
		<input type="button" value="提交意见反馈" />
	</div>
	</main>
	<script>
		$(document).ready(function() {
			$('.mui-checkbox1').on('click', function() {
				if ($('.mui-checkbox2 input').prop("checked")) {
					$('.mui-checkbox2 input').prop("checked", false);
				}
			})
			$('.mui-checkbox2').on('click', function() {
				if ($('.mui-checkbox1 input').prop("checked")) {
					$('.mui-checkbox1 input').prop("checked", false);
				}
			})
		})
	</script>

</body>

</html>