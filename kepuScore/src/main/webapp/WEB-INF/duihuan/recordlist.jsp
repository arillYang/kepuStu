<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>科普绿币商城</title>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mui.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/js/vue.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/js/mui.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/js/rem.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/co.js"></script>
<script type="text/javascript" charset="utf-8">
	mui.init();
</script>

<style type="text/css">

	.back_top{
		overflow:hiddle;
		width:12px;
		height:20px;
		margin-top: 18px;
	}
	.back_top img{
		width:100%;
		height:100%;
	}

</style>
</head>

<body>
	<header class="mui-bar mui-bar-nav" style="padding-right: 15px;">
	<h1 class="mui-title">兑换记录</h1>
	<a href="${pageContext.request.contextPath}/duihuan/index?userId=${sessionScope.userId}">
		<h5>
			<div class="back_top">
					<img src="${pageContext.request.contextPath}/images/back@2x.png"
				height="20" width="11.4" />
				</div>
		</h5>
	</a> </header>

	<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
		<div class="content-body record_body" style="margin-top: 53px;">

			<!--没有数据时显示-->
			<c:forEach items="${list }" var="st">
				<div class="record_list">
					<div class="record_divide">

						<p>
							<fmt:formatDate value="${st.recordtime }" pattern="yyyy-MM" />
						</p>

					</div>
					<ul class="mui-table-view record_l_ul">
						<li class="mui-table-view-cell mui-media record_l_li"><a
							href="#"> <img class="mui-media-object mui-pull-left"
								src="${st.recordimg }">
								<div class="mui-media-body">${st.recordname }
									<p class='mui-ellipsis'>
										<fmt:formatDate value="${st.recordtime }"
											pattern="yyyy-MM-dd hh:mm:ss" />
									</p>
								</div>
						</a> <span
							class="mui-badge mui-badge-primary ranklist_num record_l_num">-${st.recordintegral }</span>
						</li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</div>

	<script>
		mui.init({
			pullRefresh : {
				container : '#pullrefresh',
				down : {
					//						height: 50, //可选,默认50.触发下拉刷新拖动距离,
					//						auto: true, //可选,默认false.首次加载自动下拉刷新一次
					//						contentdown: "下拉可以刷新", //可选，在下拉可刷新状态时，下拉刷新控件上显示的标题内容
					//						contentover: "释放立即刷新", //可选，在释放可刷新状态时，下拉刷新控件上显示的标题内容
					//						contentrefresh: "正在努力刷新...", //可选，正在刷新状态时，下拉刷新控件上显示的标题内容
					callback : setDown
				//必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
				},
			// up: {
			//						contentrefresh: '正在加载...',
			// callback: setUp
			// }
			},
			gestureConfig : {
				tap : true, //默认为true
				doubletap : true, //默认为false
				longtap : true, //默认为false
				swipe : true, //默认为true
				drag : true, //默认为true
				hold : false, //默认为false，不监听
				release : false
			//默认为false，不监听
			},
			swipeBack : true
		//启用右滑关闭功能
		});

		//			mui(".mui-scroll-wrapper").scroll({
		//				bounce: false, //滚动条是否有弹力默认是true
		//				indicators: false, //是否显示滚动条,默认是true
		//				deceleration: mui.os.ios ? 0.0003 : 0.0009 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
		//			});

		function setDown() {
			mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
		}

		function setUp() {
			mui('#pullrefresh').pullRefresh().pullupLoading();
		}
		//			mui('.index-qiandao').addEventListener("swipeleft",function(){
		//			     console.log("你正在向左滑动");
		//			});
	</script>

</body>


