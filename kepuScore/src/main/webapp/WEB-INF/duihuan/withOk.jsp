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
<title>提现成功</title>
<link href="${pageContext.request.contextPath}/css/mui.min.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/mui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/flexible.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/co.js"></script>
<style>
body {
	background: #f8f8f8;
}

.mui-content {
	margin-top: 54px;
}

.with_input_msg {
	padding: 2.32rem 0;
	text-align: center;
}

.with_input_msg img {
	width: 2.426667rem;
	height: 2.426667rem;
}

.with_input_msg span {
	display: block;
}

.with_input_msg span:nth-of-type(1) {
	margin: .48rem 0;
	color: #4d4d4d;
	font-size: .426667rem;
}

.with_input_msg span:nth-of-type(2) {
	color: #929292;
	font-size: .373333rem;
}

.with_input_ok {
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

.back_top {
	overflow: hiddle;
	width: 12px;
	height: 20px;
	margin-top: 18px;
}

.back_top img {
	width: 100%;
	height: 100%;
}
</style>
</head>

<body>

	<header class="mui-bar mui-bar-nav" style="padding-right: 15px;">
	<h1 class="mui-title">提现成功</h1>
	<a
		href="${pageContext.request.contextPath}/duihuan/index?userId=${sessionScope.userId}">
		<h5>
			<div class="back_top">
				<img src="${pageContext.request.contextPath}/images/back@2x.png"
					height="20" width="11.4" />
			</div>
		</h5>
	</a> </header>
	<main class="mui-content">
	<div class="with_input_msg">
		<img src="${pageContext.request.contextPath}/images/with_ok.png"
			alt="ok" /> <span>提现成功</span> <span>我们将在2-5个工作日内处理您的申请</span>
	</div>
	<div class="with_input_ok">
		<a
			href="${pageContext.request.contextPath}/duihuan/index?userId=${sessionScope.userId}"
			style="text-decoration: none;"><input type="button" value="完成" /></a>
	</div>
	</main>
	<script>
		
	</script>

</body>

</html>