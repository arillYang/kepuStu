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
    <title>科普绿币</title>
    <link href="${pageContext.request.contextPath}/css/mui.min.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/js/mui.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/flexible.js"></script>
    <style>
    	ol, ul{
    		margin: 0;
    		padding: 0;
    	}
    	li{
    		list-style: none;
    	}
    	body{
			background: #f8f8f8;
		}
		.mui-content{
			margin-top: 54px;
		}
		.green_bg{
			width: 100vw;
			height: calc(100vh - 54px);
			background: url(../images/kpLv_bg_03.jpg) no-repeat center/cover;
		}
		.green_btn{
			position: fixed;
			left: 0;
			bottom: 4.013333rem;
			width: 100%;
			text-align: center;
		}
		.green_btn input{
			width: 4rem;
			height: .666667rem;
			padding: 0;
			border: none;
			background: #ffba14;
			color: #ea0850;
			font-size: .373333rem;
			border-radius: .666667rem;
		}
		.green_ewm{
			position: fixed;
			left: 0;
			bottom: 1.053333rem;
			display: flex;
			justify-content: center;
			align-items: center;
			width: 100%;
		}
		.green_ewm img{
			width: 1.893333rem;
			height: 1.893333rem;
			margin-right: .613333rem;
		}
		.green_ewm span{
			color: #fff;
		}
    </style>
</head>

<body>

    <header class="mui-bar mui-bar-nav" style="padding-right: 15px;">
        <h1 class="mui-title">科普绿币</h1>
        <a href="${pageContext.request.contextPath}/duihuan/index?userId=${sessionScope.userId}">
		<h5>
			<img src="${pageContext.request.contextPath}/images/back@2x.png"
				height="20" width="11.4" style="margin-top: 16px;" />
		</h5>
	</a> 
	</header>
	<main class="mui-content">
		<div class="green_bg">
			<div class="green_btn">
				<input type="button" value="去兑换吧" />
			</div>
			<div class="green_ewm">
				<img src="${pageContext.request.contextPath}/images/kp_ewm_03.jpg" alt="ewm" />
				<span>长按左图识别图中二维码，<br />关注“科普我知道”微信公众账号</span>
			</div>
		</div>
	</main>
	

</body>