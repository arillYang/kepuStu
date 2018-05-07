<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
 <title>商品详情</title>
 
 
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
		
    <script type="text/javascript" charset="utf-8">
        mui.init();
    </script>
    <style>
    	.mui-popover {
    			
				
			}
    	
    </style>
    
    
    
<style type="text/css">
.img-box {
	width: 100%;
	position: relative;
	z-index: 1;
}

.img-box img {
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	width: 100%;
	margin: auto;
	z-index: -1;
	*zoom: 1;
}

.img-box:before {
	content: "";
	display: inline-block;
	padding-bottom: 100%;
	width: 0.1px; /*必须要有数值，否则无法把高度撑起来*/
	vertical-align: middle;
}
</style>
    
</head>

<body style="background: #ffffff !important;">

<header class="mui-bar mui-bar-nav" style="padding-right: 15px;">
    <h1 class="mui-title">商品详情</h1>
 	<a href="index.html"><h5 id='setting'
			class=" mui-pull-left mui-icon mui-icon-arrowleft"></h5></a> </header>


	<div id="pullrefresh" class="mui-content mui-scroll-wrapper"
		style="background: #ffffff !important;">
		<div class="content-body product-details" style="margin-top: 53px;">
			<!--banner-->
			<div class="mui-slider banner">
				<div class="mui-slider-group mui-slider-loop">
					<!--支持循环，需要重复图片节点-->
					<c:forEach var="u" items="${p}">
						<div class="mui-slider-item">
							<div class="img-box">
								<img src="${u}" />
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="mui-slider-indicator">
					<div class="mui-indicator mui-active"></div>
					<div class="mui-indicator"></div>
				</div>
			</div>
				<!--banner-->
				
				
        <div class="xian"></div>
      
		<div class="details-info">
			
			<div class="details-title">${st.commodityname }</div>
			
			<div class="details-price">
				
				<div class="details-price-left">
					<div class="left1">	${st.integral }绿币</div>
					<div class="left2">${st.money }</div>
				</div>
				<div class="details-price-right"> 运费：${st.freight }</div>
			</div>
		</div>
		
		
      	<div class="xian"></div>
      	<div class="details-content">
      		<div class="details-title">商品详情</div>
      		
     ${st.details }
      			
 
      	</div>
      	<div style="height: 100px;"></div>
      	
      
      	
    </div>
</div>

<div id="middlePopover" class="mui-popover">
			<div class="mui-popover-arrow"></div>
			<div class="mui-scroll-wrapper">
				<div class="mui-scroll">
					<div class="title">请填写配送地址</div>

					<div class="mui-input-group" id="input_example" style="margin-top: 15px;">
												
						<div class="mui-input-row">
							<label>收货人：</label>
							<input type="text" class="mui-input-clear" placeholder="请输入收货人姓名">
						</div>
						<div class="mui-input-row">
							<label>手机号码：</label>
							<input type="number" class="mui-input-clear" placeholder="收货人的电话，方便联系">
						</div>
						<div class="mui-input-row" id="showCityPicker3">
							<label>地址：</label>
							<div id='cityResult3' class="ui-alert"><span>请选择地址</span></div>
						</div>
						<div class="mui-input-row">
							<label>详细地址：</label>
							<input type="text" class="mui-input-clear" placeholder="请输入街道，门牌等详细地址">
						</div>
						
					</div>
					
					<div class="btnsubmit">
						<button id="input_check">确定</button>
					</div>
				</div>
				
			</div>

		</div>

<footer class="detaillist_footer">
        <div class="detaillist_f_inner" style="text-align: center;">
        	<!--绿币够时显示-->
            <div class="detaillist_d_btn" style="width: 100%;">
            	
                <a href="#middlePopover"><button>兑 换</button></a>
            </div>
            <!--绿币不够时显示-->
            
            <!--<div class="detaillist_d_btn" style="width: 100%;">
                <a href="#"><button style="background: #e2e2e2; border: 0px !important; color: #ffffff !important;">绿币不足</button></a>
            </div>-->
            
        </div>
</footer>

<script>
	var gallery = mui('.mui-slider');
			gallery.slider({
				interval: 5000 //自动轮播周期，若为0则不自动播放，默认为0；
			});
			
	mui('body').on('tap', 'a', function () { document.location.href = this.href; });
    mui.init({
        pullRefresh: {
            container: '#pullrefresh',
            down: {
                //						height: 50, //可选,默认50.触发下拉刷新拖动距离,
                //						auto: true, //可选,默认false.首次加载自动下拉刷新一次
                //						contentdown: "下拉可以刷新", //可选，在下拉可刷新状态时，下拉刷新控件上显示的标题内容
                //						contentover: "释放立即刷新", //可选，在释放可刷新状态时，下拉刷新控件上显示的标题内容
                //						contentrefresh: "正在努力刷新...", //可选，正在刷新状态时，下拉刷新控件上显示的标题内容
                callback: setDown //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
            },
            // up: {
            //						contentrefresh: '正在加载...',
            // callback: setUp
            // }
        },
        gestureConfig: {
            tap: true, //默认为true
            doubletap: true, //默认为false
            longtap: true, //默认为false
            swipe: true, //默认为true
            drag: true, //默认为true
            hold: false, //默认为false，不监听
            release: false //默认为false，不监听
        },
        swipeBack: true //启用右滑关闭功能
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
    document.getElementById("input_check").addEventListener('click', function() {
					var check = true;
					mui("#input_example input").each(function() {
						//若当前input为空，则alert提醒 
						if(!this.value || this.value.trim() == "") {

							var label = this.previousElementSibling;
							mui.alert(label.innerText + "不允许为空");
							check = false;
							return false;
						}
					}); //校验通过，继续执行业务逻辑 
					check && mui.alert('验证通过!')
					document.location.href = "product-success.html";
					
	})
   mui("header").on('tap', 'h5', function(event){
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
						layer: 3
					});
					cityPicker3.setData(cityData3);
					var showCityPickerButton = doc.getElementById('showCityPicker3');
					var cityResult3 = doc.getElementById('cityResult3');
					showCityPickerButton.addEventListener('tap', function(event) {
						cityPicker3.show(function(items) {
							cityResult3.innerText = "" + (items[0] || {}).text + " " + (items[1] || {}).text + " " + (items[2] || {}).text;
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
					}, false);
				});
			})(mui, document);
</script>

</body>

</html>