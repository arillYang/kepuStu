<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script> --%>
<script type="text/javascript">
	KindEditor.ready(function(K) {
		var editor = K.editor({
			allowFileManager : true,
			allowImageUpload : true,//运行上传图片  
			uploadJson : "${pageContext.request.contextPath}"+'/news/upload' //  服务端上传图片处理URI
		});
		K('#J_selectImage').click(function() {
			editor.loadPlugin('multiimage', function() {
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var div = K('#J_imageView_hot');
						div.html('');
						K.each(urlList, function(i, data) {
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
							$("#launchPage").val(data.url);
							$("#pic_hot").attr("href",data.url); 
						});
						editor.hideDialog();
					}
				});
			});
		});
		K('#guide_pics').click(function() {
			editor.loadPlugin('multiimage', function() {
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var imgArray = [];
						var div = K('#J_imageView_guides');
						div.html('');
						K.each(urlList, function(i, data) {
							imgArray.push(data.url);
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
						});
						$("#guides").val(imgArray.join(","));
						editor.hideDialog();
					}
				});
			});
		});
		K('#about_us_pics').click(function() {
			editor.loadPlugin('multiimage', function() {
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var imgArray = [];
						var div = K('#J_imageView_about_us');
						div.html('');
						K.each(urlList, function(i, data) {
							imgArray.push(data.url);
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
						});
						$("#about_us").val(imgArray.join(","));
						editor.hideDialog();
					}
				});
			});
		});
	});
	
	function checkForm(){
		return true;
	}
	
	
	function resetValue(){
	}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/system/save" onsubmit="return checkForm()">
	  <div class="form-group">
	    <label class="col-sm-2 control-label">开机图片：</label>
	    <div class="col-sm-10">
	       <a id="pic_hot" href="${launchPage}" target="_blank">链接</a>
           <input type="button" id="J_selectImage"  value="上传图片" />
           <input type="hidden" id="launchPage" name="launchPage"/>  
           <div id="J_imageView_hot"></div>
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">引导页：</label>
	    <div class="col-sm-10">
           <input type="button" id="guide_pics"  value="上传图片" />
           <input type="hidden" id="guides" name="guides"/>  
           <div id="J_imageView_guides"></div>
	    </div>
	   </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">引导页链接：</label>
	    <div class="col-sm-10">
	        <c:forEach var="guide" items="${guideList}" varStatus="status">
	        	<%-- <a  href="${guide}" target="_blank">图片链接</a> --%>
	        	<img alt="引导图" src="${guide}" style="width:10%;height:10%">
	        </c:forEach>
	    </div>
	  </div>
	   <div class="form-group">
	  	<label class="col-sm-2 control-label">帮助：</label>
	  	<div class="col-sm-10">
           <input type="button" id="about_us_pics"  value="上传图片" />
           <input type="hidden" id="about_us" name="about_us"/>  
            <c:forEach var="pic" items="${logo}" varStatus="status">
	        	<img alt="帮助图" src="${pic}" style="width:10%;height:10%">
	        </c:forEach>
           <div id="J_imageView_about_us"></div>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">IOS审核开关：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" value="true" name="temp"  ${temp=='true'?'checked':''} checked>是
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="false" name="temp"  ${temp=='false'?'checked':''}>否
	 	 </label>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">发布审核开关：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" value="1" name="need_approve"  ${need_approve==1?'checked':''} checked>是
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="0" name="need_approve"  ${need_approve==0?'checked':''}>否
	 	 </label>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">文章阅读系数：</label>
	     <div class="col-sm-10">
	      <input type="text"  onkeyup="value=value.replace(/[^\d.]/g,'')"  class="form-control" id="coefficient_1" name="coefficient_1" value="${cf.coefficient_1}" style="width: 100px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">点赞系数：</label>
	    <div class="col-sm-10">
	      <input type="text" onkeyup="value=value.replace(/[^\d.]/g,'')"  class="form-control" id="coefficient_2" name="coefficient_2" value="${cf.coefficient_2}" style="width: 100px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">评论系数：</label>
	    <div class="col-sm-10">
	      <input type="text" onkeyup="value=value.replace(/[^\d.]/g,'')"  class="form-control" id="coefficient_3" name="coefficient_3" value="${cf.coefficient_3}" style="width: 100px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">分享系数：</label>
	    <div class="col-sm-10">
	      <input type="text" onkeyup="value=value.replace(/[^\d.]/g,'')" class="form-control" id="coefficient_4" name="coefficient_4" value="${cf.coefficient_4}" style="width: 100px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">发布动态系数：</label>
	    <div class="col-sm-10">
	      <input type="text" onkeyup="value=value.replace(/[^\d.]/g,'')"  class="form-control" id="coefficient_5" name="coefficient_5" value="${cf.coefficient_5}" style="width: 100px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">点击服务系数：</label>
	    <div class="col-sm-10">
	      <input type="text" onkeyup="value=value.replace(/[^\d.]/g,'')"  class="form-control" id="coefficient_6" name="coefficient_6" value="${cf.coefficient_6}" style="width: 100px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">答题系数：</label>
	    <div class="col-sm-10">
	      <input type="text" onkeyup="value=value.replace(/[^\d.]/g,'')"  class="form-control" id="coefficient_7" name="coefficient_7" value="${cf.coefficient_7}" style="width: 100px">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)">返回</button>
	      <font color="red" id="error"></font>
	    </div>
	  </div>
	  </form>
  </div>
</div>

<!-- 设置积分兑换比例,和消费返积分比例 -->

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">设置积分兑换比例,和消费返积分比例</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" method="post"
				action="${pageContext.request.contextPath}/user/system/save"
				onsubmit="return checkForm()">
				<div class="form-group">
					<label class="col-sm-2 control-label">下单返积分比例</label>
					<div class="col-sm-10">
						<input type="text" onkeyup= "if( ! /^d*(?:.d{0,2})?$/.test(this.value)){alert('只能输入数字，小数点后只能保留2位');this.value='';}"
							class="form-control" id="coefficient_2" name="coefficient_2"
							value="0.002" style="width: 100px"></input><font color="red">注意:设置成功后用户下单金额乘比例等于获得积分!&nbsp&nbsp&nbsp&nbspPS:0.002</font>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">积分提现比例</label>
					<div class="col-sm-10">
						<input type="text" onkeyup= "if( ! /^d*(?:.d{0,2})?$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='';}"
							class="form-control" id="coefficient_3" name="coefficient_3"
							value="0.02" style="width: 100px"> <font color="red">注意:设置成功后用户兑换金额乘比例等于获得余额!&nbsp&nbsp&nbsp&nbspPS:0.02</font>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">保存</button>
						&nbsp;&nbsp;
						<button type="button" class="btn btn-primary"
							onclick="javascript:window.history.go(-1)">返回</button>
						<font color="red" id="error"></font>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>