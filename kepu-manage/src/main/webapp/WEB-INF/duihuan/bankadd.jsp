<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>绑定银行卡</title>
<script src="${pageContext.request.contextPath}/js/mui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/rem.js"></script>

<link href="${pageContext.request.contextPath}/css/mui.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" />
<script type="text/javascript" charset="utf-8">
	mui.init();
</script>
</head>

<body>

	<header class="mui-bar mui-bar-nav" style="padding-right: 15px;">
	<h1 class="mui-title">绑定银行卡</h1>

	<a href="index.html"><h5 id='setting'
			class=" mui-pull-left mui-icon mui-icon-arrowleft"></h5></a> </header>



	<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
		<div class="content-body bankadd-body" style="margin-top: 53px;">

			<div class="xian"></div>
			<div class="bank-title">请绑定持卡人本人的银行卡</div>

			<div class="mui-input-group" id="input_example"
				style="margin-top: 15px;">
				<div class="mui-input-row">
					<label>银行：</label> <select id="bankclass" class=" mui-btn-block">
						<option value="0">请选择银行</option>
						<option value="1">中国农业银行</option>
						<option value="2">中国工商银行</option>
					</select>
				</div>

				<div class="mui-input-row">
					<label>持卡人：</label> <input type="text" class="mui-input-clear"
						placeholder="">
				</div>
				<div class="mui-input-row">
					<label>卡号：</label> <input type="number" class="mui-input-clear"
						placeholder="">
				</div>
			</div>

			<div class="xian"></div>

			<div class="bank-sumit">
				<button type="button" class="mui-btn mui-btn-primary"
					id="input_check">确认提交</button>
			</div>

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

		document.addEventListener('DOMContentLoaded', function() {
			//			if(String.prototype.trim === undefined) { // fix for iOS 3.2
			//				String.prototype.trim = function() {
			//					return this.replace(/^\s+|\s+$/g, '');
			//				};
			//			}

			document.getElementById("header")
			mui("header").on('tap', 'h5', function(event) {
				mui.back()
			});

			document.getElementById("input_check").addEventListener('click',
					function() {
						var check = true;
						mui("#input_example input").each(function() {
							//若当前input为空，则alert提醒 
							if (!this.value || this.value.trim() == "") {
								var label = this.previousElementSibling;
								mui.alert(label.innerText + "不允许为空");
								check = false;
								return false;
							}
						}); //校验通过，继续执行业务逻辑 
						check && mui.alert('验证通过!')
					})

		})
	</script>

</body>

</html>