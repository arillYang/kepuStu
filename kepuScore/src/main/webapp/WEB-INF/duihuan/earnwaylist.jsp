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
<title>获取绿币记录</title>
<script src="${pageContext.request.contextPath}/js/mui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/rem.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/co.js"></script>
<link href="${pageContext.request.contextPath}/css/mui.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" />
<script type="text/javascript" charset="utf-8">
	mui.init();
</script>
		<style>
			body{
				background: #f8f8f8;
			}
			.privacy-title h2{
				margin-top: 1.333333rem;
				font-size: 18px;
				text-align: center;
			}
			.privacy-cont{
				padding: 0 .666667rem;
				margin-top: 1.2rem;
				background: #fff;
				text-align: justify;
			}
			.privacy-cont p, 
			.privacy-cont h3{
				color: #333;
			}
			.privacy-cont h3{
				margin: 0;
				padding: .5rem 0;
				font-size: 15px;
			}
			.privacy-cont p{
				margin-bottom: 0;
				text-indent: 28px;
			}
			.privacy-time{
				margin-top: 1.333333rem;
			    padding: 0 .666667rem;
			}
			.privacy-time p{
				margin-bottom: 0 .666667rem;
				text-align: right;
				color: #333;
			}
			
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
	<h1 class="mui-title">科普绿币管理规定</h1>
	<a href="${pageContext.request.contextPath}/duihuan/index?userId=${sessionScope.userId}">
		<h5>
			<div class="back_top">
					<img src="${pageContext.request.contextPath}/images/back@2x.png"
				height="20" width="11.4" />
			</div>
		</h5>
	</a> </header>
		<main>
			<div class="mui-scroll-wrapper">
				<div class="mui-scroll">
					<div class="privacy-cont">
						<h3>一、什么是科普绿币</h3>
						<p>科普绿币是“科普中国.我知道”APP平台推出的虚拟货币，每天通过完成各种任务、看科普文章赚取科普绿币。所赚取的科普绿币可以折现并累计到现金账户。目前800个科普绿币=1元，最低达到30元可以提现。</p>
						<h3>二、如何赚取科普绿币</h3>
						<p>1、每日签到。连续签到1天，当天可获得1个科普绿币，连续签到2天，当天可获得2个科普绿币，连续签到3天，当天可获得3个科普绿币，以此类推，连续签到7天，当天可获得7个科普绿币，每7天一个轮回。但若连续签到出现某一天断签，则重新从第一天开始签到。</p>
						<p>2、阅读文章。每阅读一篇文章可获得1个科普绿币。</p>
						<p>3、分享文章。科普文章每分享一次可获得1个科普绿币。</p>
						<p>4、发布评论。在科普文章底部发表评论，可获得1个科普绿币。</p>
						<p>5、购物。在本APP购物一次可获得1个科普绿币。</p>
						<p>6、互动答题。参与本APP互动答题，每次可获得1个科普绿币。</p>
						<p>7、发布信息。在APP上发布信息，每次可获得1个科普绿币。</p>
						<p>8、点赞。在文章底部每点赞一次可获得1个科普绿币。</p>
						<h3>三、科普绿币的使用</h3>
						<p>1、直接提现。所赚取的科普绿币可以折现，并累计到现金账户，最低达到30元就可以提现。</p>
						<p>2、可以充值话费。</p>
						<p>3、可以在APP购买商品时直接抵现，或兑换商家优惠券、现金抵扣券等。</p>
						<p>4、可以在平台上进行抽奖，赢取礼品。</p>
						<h3>四、科普绿币使用相关规定</h3>
						<p>1、科普绿币不可以转让给其他人</p>
						<p>2、科普绿币使用有效期为一年，一年一清，逾期清零。</p>
						<p></p>
					</div>
					<div class="privacy-time">
						<p>2018年4月19日</p>
					</div>
				</div>
			</div>
		</main>
		
		<script>
			$(document).ready(function(){
				mui('.mui-scroll-wrapper').scroll({
					deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
				});
			})
		</script>
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
</html>