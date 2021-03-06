<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>科普绿币商城</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mui.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/vue.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/mui.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/rem.js"></script>
<link href="${pageContext.request.contextPath}/css/mui.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/mui.picker.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/mui.poppicker.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/mui.picker.js"></script>
<script src="${pageContext.request.contextPath}/js/mui.poppicker.js"></script>
<script src="${pageContext.request.contextPath}/js/city.data-3.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript" charset="utf-8">
	mui.init();
</script>
<style>
.index-qiandao .mui-control-item1 {
	display: inline-flex;
	justify-content:center;
	align-items:center;
	width: 50px;
	height: 50px;
	border-radius: 50px;
	border: 1px solid #ccc;
	position: relative;
	padding: 0px !important;
	margin-right:10px;
}
.index-qiandao .mui-control-item1:last-of-type{
	margin-right:0;
}
.index-qiandao .mui-control-item1 .mui-scroll{
	position: absolute;
	left: 0;
}

.index-qiandao .active-day1.acDay {
	border: 0px solid #5296d4 !important;
	background: #5296d4;
	color: #ffffff;
}
.index-qiandao .active-day1.active{
    border: 1px solid #f95e60 !important;
    color: #f95e60 !important;

}
.index-qiandao .active-day1.n{
	background: #5296d4;
}
.index-qiandao .active-day1.y{
	background: rgba(0,0,0,.3);
}
.mui-table-view-cell{
	padding:10px 5px;
}
</style>

</head>
<body>

	<!-- <header class="mui-bar mui-bar-nav" style="padding-right: 15px;">
	<h1 class="mui-title">科普绿币</h1>
	<h5></h5>
	</header> -->

	<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
		<div class="content-body">
			<!--banner-->
			<div class="mui-slider banner">
				<div class="mui-slider-group mui-slider-loop">
					<!--支持循环，需要重复图片节点-->
					<div class="mui-slider-item mui-slider-item-duplicate"></div>
					<div class="mui-slider-item">
						<a href="${pageContext.request.contextPath}/duihuan/greenback"><img
							src="${pageContext.request.contextPath}/images/banner1.jpg" /></a>
					</div>
					<div class="mui-slider-item">
						<a href="${pageContext.request.contextPath}/duihuan/greenback"><img
							src="${pageContext.request.contextPath}/images/banner1.jpg" /></a>
					</div>

					<!--支持循环，需要重复图片节点-->
					<div class="mui-slider-item mui-slider-item-duplicate">
						<a href="${pageContext.request.contextPath}/duihuan/greenback"><img
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
					<div class="mui-col-sm-4 mui-col-xs-4" style="padding-right: 0px;">
						<li class="mui-table-view-cell"><a
							class="index-card-list list1"
							href="${pageContext.request.contextPath}/duihuan/detaillist">
								<div class="icon">
									<img
										src="${pageContext.request.contextPath}/images/icon-index-card1.png" />
								</div>
								<div class="title">绿币${stu.score }</div>
						</a></li>
					</div>
					<div class="mui-col-sm-4 mui-col-xs-4">
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
					<div class="mui-col-sm-4 mui-col-xs-4">
						<li class="mui-table-view-cell"><a
							class="index-card-list list4"
							href="${pageContext.request.contextPath}/duihuan/earnwaylist">
								<div class="icon">
									<img
										src="${pageContext.request.contextPath}/images/icon-index-card1.png" />
								</div>
								<div class="title">如何赚绿币</div>
						</a></li>
					</div>
				</div>
<!-- 				<div class="mui-row"> -->
<!-- 					<div class="mui-col-sm-6 mui-col-xs-6" style="padding-right: 0px;"> -->
<!-- 						<li class="mui-table-view-cell"><a -->
<!-- 							class="index-card-list list3" -->
<%-- 							href="${pageContext.request.contextPath}/duihuan/rankinglist"> --%>
<!-- 								<div class="icon"> -->
<!-- 									<img -->
<%-- 										src="${pageContext.request.contextPath}/images/icon-index-card3.png" /> --%>
<!-- 								</div> -->
<%-- 								<div class="title">绿币排行榜:${town }</div> --%>
<!-- 						</a></li> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>


			<div class="current-date"></div>
			<div class="index-qiandao mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
				<div class="mui-scroll">
					
				</div>
			</div>
			<!--签到-->
			<div class="xian"></div>
			<!--兑换-->
			<div class="index-product">
				<h3>绿币兑换</h3>
				<ul class="mui-table-view mui-grid-view"
					style="padding: 0px; background: none;">

					<c:forEach items="${list}" var="st">
						<li class="mui-table-view-cell mui-media mui-col-xs-6"><a
							href="${pageContext.request.contextPath}/duihuan/productdetails?id=${st.id}">
								<img height="100px" width="100px" src="${st.displaythepicture}">
								<input type="hidden" value="${st.id}" name="id" id="stId">
								<div class="mui-media-body mui-media-title">${st.commodityname}</div>
								<div class="mui-media-body mui-media-price">${st.integral}绿币</div>
						</a> 
						<c:if test="${st.integral <= stu.score  }">
								<a href="javascript:void(0);" class="test001"> <input
									type="hidden" value="${st.id}">
									<div class="mui-media-body mui-media-circle" data-id="${st.id}"
										id="${st.id}">兑换</div>
								</a>
							</c:if> <c:if test="${st.integral > stu.score }">
								<a href="#middlePopover1">
									<div class="mui-media-body mui-media-circle"
										style="background: #e2e2e2; color: #ffffff !important;"border: 0px !important;">
										绿币不足</div>
								</a>
							</c:if></li>
					</c:forEach>
				</ul>
			</div>

			<div style="height: 50px;"></div>
		</div>
	</div>
	
	<div id="middlePopover1">	</div>

	<div id="middlePopover" class="mui-popover">
		<div class="mui-popover-arrow"></div>
		<div class="mui-scroll-wrapper">
		
			<div class="mui-scroll">
				<div class="title">请填写配送地址</div>
				<div class="mui-input-group" id="input_example"
					style="margin-top: 15px;">

					<div class="mui-input-row">
						<label>收货人：</label> <input type="text" name="name" id="name"
							class="mui-input-clear" placeholder="请输入收货人姓名">
					</div>
					<div class="mui-input-row">
						<label>手机号码：</label> <input type="text" id="phone" name="phone"
							class="mui-input-clear" placeholder="收货人的电话，方便联系">
					</div>
					<div class="mui-input-row" id="showCityPicker3">
						<label>地址：</label>
						<div id='cityResult3' class="ui-alert">
							<span>请选择地址</span>
						</div>
					</div>
					<div class="mui-input-row">
						<label>详细地址：</label> <input type="text" class="mui-input-clear"
							placeholder="请输入街道，门牌等详细地址" id="address" name="address">
					</div>
				</div>

				<div class="btnsubmit">
					<button id="input_check" onclick="duihuan()">确定</button>
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
			    <div class="qd-num"></div>
			</div>
		</div>
	</div>



	<script type="text/javascript">

	function duihuan() {
		var id = currentId;
		var name = $("#name").val();
		var phone = $("#phone").val();
		var dizi = $('#cityResult3').html();
		var address = $("#address").val();
		 var pattern = /^1[34578]\d{9}$/; 
		if (name == "") {
			mui.alert('请填写收货人名字','温馨提示');
		} else if ( pattern.test(phone) == false) {
			mui.alert('请填写正确手机号','温馨提示');
		} else if (dizi == "请选择地址") {
			mui.alert('请选择收货人地址','温馨提示');
		} else if (address == "") {
			mui.alert('请选择收货人地址','温馨提示');
		} else {
		
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/duihuan/insertrankinglist",
			data : {
				"proid" : id,
				"name" : name,
				"phone" : phone,
				"dizi" : dizi,
				"address" : address
			},
			dataType : "json",
			success : function(data) {
				if (data == 0) {
					mui.alert('兑换失败','温馨提示');
					location.reload();
				} else if (data == 1) {
					mui.alert('绿币不足','温馨提示');
				} else if (data == 2) {
					mui.alert('余额不足','温馨提示');
				} else if (data == 3) {
					window.location.href = "${pageContext.request.contextPath}/duihuan/productsuc";
				}else if (data == 4) {
				
					mui.alert('数量不足','温馨提示');
				}
			}
		});
			}
			return false;
		}
	</script>
	<script>

		var gallery = mui('.mui-slider');
		var currentId = null;
		gallery.slider({
			interval : 5000
		//自动轮播周期，若为0则不自动播放，默认为0；
		});

		mui('body').on('tap', 'a:not(.mui-media-circle)', function() {
			document.location.href = this.href;
		});
		
		mui('body').on('tap', '.mui-media-circle', function() {
			currentId = this.id;
			mui('#middlePopover').popover('show');
		});	
		
		var date=new Date();
		
		//获取当前月份的总天数
		function getDays(){
			var date=new Date();
		    //将当前月份加1，下移到下一个月
		    date.setMonth(date.getMonth()+1);
		    //将当前的日期置为0，
		    date.setDate(0);
		    //再获取天数即取上个月的最后一天的天数
		    var days=date.getDate();
		    return days;
		}
		
		//为当前标签循环添加子标签		getDays()
		var str="";
		for(var i=1;i<=7;i++)
		{
		  str+="<div class='mui-control-item1 active-day1'>"+i+"</div>";
		}
		$(".mui-scroll").append(str);
		
		mui.ajax(
				'${pageContext.request.contextPath}/duihuan/signCheck', 
			{
				dataType : 'json',//服务器返回json格式数据
				type : 'post',//HTTP请求类型
				success : function(data) {
				
						mui(".mui-scroll .mui-control-item1").each(function(){
							if(data==""){
								if(1==$(this).text())
									{
									$(this).addClass('n').html('签到');
									}
							}
						})
					
					 var it=data.length-1;
					 var t= $(this).text()
					for(var i=0;i<data.length;i++){
						var adata=new Date();
						mui(".mui-scroll .mui-control-item1").each(function(){
								if($(this).text()==data[i].keysta){
									$(this).addClass('active');
								}
							if(data[0].ajxsigndate==data[0].sqlsigndate){ 
								if(data[0].keysta==$(this).text())	{
								$(this).addClass('y').html('已签到');
							   }	
							}else{
								var t=data[0].keysta+1;
								if(t==$(this).text())	{
									$(this).addClass('n').html('签到');
							}
							}
						})
					}
				}	
			});

		mui('body').on('tap', '.active-day1', function() {
			
			var h= $(this).text();
			if(h=="签到")
			{
				var y=$(this).index()+1;
			 	var pop = document.getElementById("qiandao-zhezhao");
				pop.classList.remove('qiandao-zhezhao-close');
			 mui.ajax(
							'${pageContext.request.contextPath}/duihuan/sign', 
							{
							dataType : 'json',//服务器返回json格式数据
							type : 'post',//HTTP请求类型
							data:{
								keysta:$(this).index()+1
							},
							success : function(data) {
		
								if(data>=2)
									{
									$(".qd-num").text("+7绿币");
									}else if(data==9999){
										$(".qd-num").text("已签到");
									}else{
										
										$(".qd-num").text("+"+y+"绿币");
									}
								setTimeout(function(){
									location.reload();
								},3000 );  
								 
							}
						}); 
			
				pop.classList.add('qiandao-zhezhao-open');
				
			}
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