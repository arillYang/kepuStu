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
    <link href="${pageContext.request.contextPath}/css/mui.min.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/mui.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/flexible.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/co.js"></script>
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

.mask_share {
	padding-top: 3.466667rem;
}

.mask_share_list {
	display: flex;
	flex-wrap: wrap;
	width: 8.24rem;
	margin: 0 auto;
}

.mask_share_list li {
	width: 2.746667rem;
	margin-bottom: .826667rem;
	text-align: center;
}

.mask_share_list li img {
	width: 1.2rem;
	height: 1.2rem;
}

.mask_share_list li span {
	color: #6b6b6b;
	font-size: .346667rem;
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
        <h1 class="mui-title">邀请收徒</h1>
        <a href="index.html">
		<h5>
			<div class="back_top">
					<img src="${pageContext.request.contextPath}/images/back@2x.png"
				height="20" width="11.4" />
				</div>
		</h5>
	</a> 
	</header>
	<main class="mui-content">
		<div class="mask_share">
			<ul class="mask_share_list">
				<li><img src="${pageContext.request.contextPath}/images/share-wx.png" alt="wx" /><br /><span>微信好友</span></li>
				<li><img src="${pageContext.request.contextPath}/images/share-py.png" alt="py" /><br /><span>朋友圈</span></li>
				<li><img src="${pageContext.request.contextPath}/images/share-hy.png" alt="hy" /><br /><span>QQ好友</span></li>
				<li><img src="${pageContext.request.contextPath}/images/share-wb.png" alt="wb" /><br /><span>微博</span></li>
				<li><img src="${pageContext.request.contextPath}/images/share-kj.png" alt="kj" /><br /><span>QQ空间</span></li>
			</ul>
		</div>
	</main>
	<script>
	
	</script>

</body>

</html>