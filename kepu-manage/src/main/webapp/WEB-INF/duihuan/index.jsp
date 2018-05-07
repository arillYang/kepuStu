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
<title>科普积分商城</title>

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



<link href="${pageContext.request.contextPath}/css/mui.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/mui.picker.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/mui.poppicker.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/mui.picker.js"></script>
<script src="${pageContext.request.contextPath}/js/mui.poppicker.js"></script>
<script src="${pageContext.request.contextPath}/js/city.data-3.js"
	type="text/javascript" charset="utf-8"></script>


<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/main.js"></script>

<script type="text/javascript" charset="utf-8">
	mui.init();
</script>
<style>
.mui-popover {
	
}
</style>
</head>
<body>

	<header class="mui-bar mui-bar-nav" style="padding-right: 15px;">
	<h1 class="mui-title">科普绿币</h1>
	<h5 id='setting' class=" mui-pull-right mui-icon mui-icon-search"></h5>
	</header>

	<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
		<div class="content-body">
			<!--banner-->
			<div class="mui-slider banner">
				<div class="mui-slider-group mui-slider-loop">
					<!--支持循环，需要重复图片节点-->
					<div class="mui-slider-item mui-slider-item-duplicate"></div>
					<div class="mui-slider-item">
						<a href="#"><img
							src="${pageContext.request.contextPath}/images/banner1.jpg" /></a>
					</div>
					<div class="mui-slider-item">
						<a href="#"><img
							src="${pageContext.request.contextPath}/images/banner1.jpg" /></a>
					</div>

					<!--支持循环，需要重复图片节点-->
					<div class="mui-slider-item mui-slider-item-duplicate">
						<a href="#"><img
							src="${pageContext.request.contextPath}/images/banner1.jpg" /></a>
					</div>
				</div>

				<div class="mui-slider-indicator">
					<div class="mui-indicator mui-active"></div>
					<div class="mui-indicator"></div>

				</div>
			</div>

			<!--banner-->

			<div class="mui-content index-card-content">
				<div class="mui-row">
					<div class="mui-col-sm-6 mui-col-xs-6" style="padding-right: 0px;">
						<li class="mui-table-view-cell"><a
								class="index-card-list list1" href="${pageContext.request.contextPath}/duihuan/detaillist">
								<div class="icon">
									<img
										src="${pageContext.request.contextPath}/images/icon-index-card1.png" />
								</div>
								<div class="title">绿币200</div>
						</a></li>
					</div>
					<div class="mui-col-sm-6 mui-col-xs-6">
						<li class="mui-table-view-cell"><a
							class="index-card-list list2"
							href="${pageContext.request.contextPath}/duihuan/recordlist">
								<div class="icon">
									<img
										src="${pageContext.request.contextPath}/images/icon-index-card2.png" />
								</div>
								<div class="title">兑换纪录</div>
						</a></li>
					</div>
				</div>
				<div class="mui-row">
					<div class="mui-col-sm-6 mui-col-xs-6" style="padding-right: 0px;">
						<li class="mui-table-view-cell"><a
							class="index-card-list list3"
							href="${pageContext.request.contextPath}/duihuan/rankinglist">
								<div class="icon">
									<img
										src="${pageContext.request.contextPath}/images/icon-index-card3.png" />
								</div>
								<div class="title">绿币排行榜</div>
						</a></li>
					</div>
					<div class="mui-col-sm-6 mui-col-xs-6">
						<li class="mui-table-view-cell"><a
						class="index-card-list list4" href="${pageContext.request.contextPath}/duihuan/earnwaylist">
								<div class="icon">
									<img
										src="${pageContext.request.contextPath}/images/icon-index-card1.png" />
								</div>
								<div class="title">如何赚绿币</div>
						</a></li>
					</div>
				</div>
			</div>

			<div class="xian"></div>

			<!--签到-->

			<div
				class="index-qiandao mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
				<div class="mui-scroll">
					<div class="mui-control-item active">1</div>
					<div class="mui-control-item">2</div>
					<div class="mui-control-item active-day">3</div>
					<div class="mui-control-item">4</div>
					<div class="mui-control-item">5</div>
					<div class="mui-control-item">6</div>
					<div class="mui-control-item">7</div>
					<div class="mui-control-item">8</div>
					<div class="mui-control-item">9</div>

				</div>
			</div>

			<!--签到-->
			<div class="xian"></div>

			<!--兑换-->
			<div class="index-product">
				<h3>绿币兑换</h3>
				<ul class="mui-table-view mui-grid-view"
					style="padding: 0px; background: none;">

					<c:forEach items="${list }" var="st">
						<li class="mui-table-view-cell mui-media mui-col-xs-6"><a
							href="${pageContext.request.contextPath}/duihuan/productdetails?id=${st.id}">
								<img height="100px" width="100px" src="${st.displaythepicture}">
								<input type="hidden" value="${st.id}" name="id" id="stId">
								<div class="mui-media-body mui-media-title">${st.commodityname}</div>
								<div class="mui-media-body mui-media-price">${st.integral}绿币</div>
						</a> <a href="#middlePopover" class="test001" >
						<input type="hidden" value="${st.id}"   id="test0001" >
								<div class="mui-media-body mui-media-circle" data-id="${st.id}"   id="${st.id}">兑换</div>
						</a></li>
					</c:forEach>
				</ul>
			</div>

			<div style="height: 50px;"></div>
		</div>
	</div>

	<div id="middlePopover" class="mui-popover">
		<div class="mui-popover-arrow"></div>
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				<div class="title">请填写配送地址</div>
				<div class="mui-input-group" id="input_example"
					style="margin-top: 15px;">

					<div class="mui-input-row">
						<label>收货人：</label> <input type="text"  name="name"   id="name" class="mui-input-clear"
							placeholder="请输入收货人姓名">
					</div>
					<div class="mui-input-row">
						<label>手机号码：</label> <input type="text"  id="phone"  name="phone" class="mui-input-clear"
							placeholder="收货人的电话，方便联系">
					</div>
					<div class="mui-input-row" id="showCityPicker3">
						<label>地址：</label>
						<div id='cityResult3' class="ui-alert"  >
							<span>请选择地址</span>
						</div>
					</div>
					<div class="mui-input-row">
						<label>详细地址：</label> <input type="text" class="mui-input-clear"
							placeholder="请输入街道，门牌等详细地址"  id="address"  name="address">
					</div>
				</div>

				<div class="btnsubmit">
					<button id="input_check"  onclick="duihuan()">确定</button> 
				</div>
			</div>

		</div>

	</div>
	<div class="qiandao-zhezhao " id="qiandao-zhezhao">
		<div class="qd-content">
			<img src="${pageContext.request.contextPath}/images/qd.png"
				width="100%" />
			<div class="qd-info">
				<div class="qd-title">恭喜您今日签到成功</div>
				<div class="qd-num">+4绿币</div>
			</div>
		</div>
	</div>


 <script type="text/javascript">

		function duihuan() {
			var id = $("#test0001").val();
			var name = $("#name").val();
			var phone = $("#phone").val();
			var dizi = $('#cityResult3').html();
			var address = $("#address").val();
			if (name == "") {
				alert("请填写收货人名字");
			} else if (phone == "") {
				alert("请填写收货人手机号");
			} else if (dizi == "请选择地址") {
				alert("请选择收货人地址");
			} else if (address == "") {
				alert("请填写收货人地址");
			} else {
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/duihuan/insertrankinglist",
					data :{
						"proid":id,
						"name":name,
						"phone":phone,
						"dizi":dizi,
						"address":address
						
					},
					dataType : "json",
					success : function(data) {
						alert("兑换成功")
					},error:function(){
						alert("兑换失败")
					}
				});
			}
			return false;

		}
	</script>

	<script>
		var gallery = mui('.mui-slider');
		gallery.slider({
			interval : 5000
		//自动轮播周期，若为0则不自动播放，默认为0；
		});

		mui('body').on('tap', 'a', function() {
			document.location.href = this.href;
		});
		mui('body').on('tap', '.mui-media-circle', function() {
			var s = this.id;
			console.log(s)
			
			
		});

		mui('body').on('tap', '.active-day', function() {
			var pop = document.getElementById("qiandao-zhezhao");
			pop.classList.remove('qiandao-zhezhao-close');
			pop.classList.add('qiandao-zhezhao-open');
		});

		mui('body').on('tap', '.qiandao-zhezhao', function() {
			var pop = document.getElementById("qiandao-zhezhao");
			pop.classList.remove('qiandao-zhezhao-open');
			pop.classList.add('qiandao-zhezhao-close');

		});

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
				}
			//					,
			//					up: {
			//						contentrefresh: '正在加载...',
			//						callback: setUp
			//					}
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
		document.getElementById("input_check").addEventListener('click',
				function() {
					var check = true;
					mui("#input_example input").each(function() {
						//若当前input为空，则alert提醒
						return;
						if (!this.value || this.value.trim() == "") {
							var label = this.previousElementSibling;
							mui.alert(label.innerText + "不允许为空");
							check = false;
							return false;
						}
					}); //校验通过，继续执行业务逻辑 
					/* check && mui.alert('验证通过!') */
					return false;

				})

		mui("header").on('tap', 'h5', function(event) {
			mui.back()
		});
		mui('body').on('shown', '.mui-popover', function(e) {
		
			//console.log('shown', e.detail.id);//detail为当前popover元素
		});
		mui('body').on('hidden', '.mui-popover', function(e) {
			//console.log('hidden', e.detail.id);//detail为当前popover元素
		});

		(function($, doc) {
			$.init();
			$.ready(function() {
				//普通示例
				//-----------------------------------------
				//					//级联示例
				var cityPicker3 = new $.PopPicker({
					layer : 3
				});
				cityPicker3.setData(cityData3);
				var showCityPickerButton = doc
						.getElementById('showCityPicker3');
				var cityResult3 = doc.getElementById('cityResult3');
				showCityPickerButton.addEventListener('tap', function(event) {
					cityPicker3.show(function(items) {
						cityResult3.innerText = "" + (items[0] || {}).text
								+ " " + (items[1] || {}).text + " "
								+ (items[2] || {}).text;
						//返回 false 可以阻止选择框的关闭
						//return false;
					});
				}, false);
			});
		})(mui, document);
	</script>

</body>

</html>