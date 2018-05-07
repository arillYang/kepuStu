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
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/selectpicker/bootstrap-select.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/selectpicker/bootstrap-select.css">
<script type="text/javascript">
$(window).on('load', function () {
	$('#village').selectpicker();  
	var v=${villageIds==null?-1:villageIds};
	if(v!=-1){
		var array=v;
		$("#village").selectpicker('val', array);
		$('#village').selectpicker('refresh');
	}
	$("#village").change(function(){
		var temp=$("#village").val();
		if($.inArray("-100", temp)==0)
			$("#village").selectpicker('val', '-100');
		});
});
$(document).ready(function(){
	$("#town").change( function () {
		$("#village").html("");
	})
});
	KindEditor.ready(function(K) {
		var editor = K.editor({
			allowFileManager : true,
			allowImageUpload : true,//运行上传图片  
			uploadJson : "${pageContext.request.contextPath}"+'/news/upload' //  服务端上传图片处理URI
		});
		K('#J_selectImage').click(function() {
			editor.loadPlugin('multiimage', function() {
				editor.uploadJson="${pageContext.request.contextPath}"+'/news/upload?head=1';
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var imgArray = [];
						var div = K('#J_imageView_hot');
						div.html('');
						K.each(urlList, function(i, data) {
							imgArray.push(data.url);
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
							$("#pic_hot").attr("href",data.url); 
						});
						  $("#newsimages").val(imgArray.join(","));
						editor.hideDialog();
					}
				});
			});
		});
		K('#J_selectImage_latterPic').click(function() {
			editor.loadPlugin('multiimage', function() {
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var imgArray = [];
						var div = K('#J_imageView_latterPic');
						div.html('');
						K.each(urlList, function(i, data) {
							imgArray.push(data.url);
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
							$("#pic_hot").attr("href",data.url); 
						});
					    $("#latterpic").val(imgArray.join(","));
						editor.hideDialog();
					}
				});
			});
		});
		K('#J_selectImage_firstPic').click(function() {
			editor.loadPlugin('multiimage', function() {
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var div = K('#J_imageView_firstPic');
						div.html('');
						K.each(urlList, function(i, data) {
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
							$("#firstpic").val(data.url);
							$("#pic_firstPic").attr("href",data.url); 
						});
						editor.hideDialog();
					}
				});
			});
		});
	});
	
	var editor2;
	KindEditor.ready(function(K) {
		editor2 = K.create('textarea[name="realcontent"]', {
			resizeType : 1,
			allowPreviewEmoticons : true,
			allowImageUpload : true,
			allowFileManager : true,
			items : [
				'image', 'preview','undo','redo','fullscreen','media'],
		    uploadJson : "${pageContext.request.contextPath}"+'/news/upload',
		    afterBlur: function () { 
		    	//alert(editor2.text());
		    	$("#myContent").val(editor2.text()); //this.sync();   获取文本值
		    	},//数据同步
		});
	});
	function checkForm(){
		var t=$("#town").val()
		if(t!=null&&t=="-100"){
			$("#error").html("请选择乡镇");
			return false;
		} 
		return true;
	}
	$(function () {
	    //默认绑定乡镇
	    // TownBind();
	    //绑定事件
	    var town = $("#town").val();
	    if (town != ""||town!=null) {
	    	villageBind();
	    }
	    $("#town").change( function () {
	    	villageBind();
	    })
	})
	function villageBind() {
		var town = $("#town").val();
	    //判断省份这个下拉框选中的值是否为空
	    if (town == ""||town==null) {
	        return;
	    }
	    	$("#village").html("");
	    var str = "<option value='-100'>全部</option>";
		$.ajax({
	        type: "POST",
	        url:"${pageContext.request.contextPath}"+'/user/getAddress',
	        data: { "parentId": town},
	        dataType: "JSON",
	        async: false,
	        success: function (data) {
	            $.each(data, function (i, item) {
	               str+="<option value='" + item.id + "'>" + item.name + "</option>";
	            })
	    		$("#village").append(str);
	            $('#village').selectpicker('refresh');
	        },
	        error: function () { alert("Error"); }
	    });
	}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/notice/save" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">公告标题：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="title" name="title" value="${news.title}" style="width: 600px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">样式分类：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" name="newsstyle" value="1" ${news.newsstyle==1?'checked':''} checked>单图(小)
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" name="newsstyle" value="2" ${news.newsstyle==2?'checked':''}>单图(大)
	 	 </label>
	 	 <label class="checkbox-inline">
	    	<input type="radio" name="newsstyle" value="3" ${news.newsstyle==3?'checked':''}>三图
	 	 </label>
	 	  <label class="checkbox-inline">
	    	<input type="radio" name="newsstyle" value="4" ${news.newsstyle==4?'checked':''}>无图
	 	 </label>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">公告logo：</label>
	    <div class="col-sm-10">
           <input type="button" id="J_selectImage"  value="上传图片" />
           <span style="color:red">温馨提示：大单图比例，15:6；三图比例：4:3；小单图比例：4:3,图片控制在1M之内</span>
           <input type="hidden" id="newsimages" name="newsimages"/>  
           <div id="J_imageView_hot">
           		<c:forEach var="pic" items="${logo}" varStatus="status">
	           		<img src="${pic}" style="width:20%;height:20%;" >
	        	</c:forEach>
           	</div>
	    </div>
	  </div>
	 <c:if test="${right!=2}">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">乡镇：</label>
		     <c:if test="${right==0}">
			     <div class="col-sm-10">
				   	 <select class="form-control" style="width:120px;float:left;margin-right:20px" id="town" name="town">
				    		<option value="-100">请选择...</option>
			    		 	<c:forEach var="t" items="${town}" varStatus="status">
				    			<option value="${t.id}" ${t.id==townId?'selected':''}>${t.name}</option>
			        		</c:forEach>
				    </select>
				    <select  class="selectpicker bla bla bli"  multiple data-live-search="true" style="width:120px;float:left;margin-left:10px" id="village" name="village">
				    		
				    </select>
			     </div>
		     </c:if>
		     <c:if test="${right==1}">
		      <div class="col-sm-10">
		     		<input type="hidden" id="town"  value="${townId}"/>
		     	    <select  class="selectpicker bla bla bli"  multiple data-live-search="true" style="width:500px;float:left;margin-left:10px" id="village" name="village">
				    		<option value="-100">全部</option>
				    </select>
			</div>  
		     </c:if>
		  </div>
	  </c:if>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">关键词：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="keywords" name="keywords" value="${news.keywords}" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">公告作者：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="newsauthor" name="newsauthor" value="${news.newsauthor}" style="width: 300px">
	    </div>
	  </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">公告内容：</label>
	    <div class="col-sm-10">
	      <textarea  name="realcontent" style="width:650px;height:500px;visibility:hidden;">${realcontent.content}</textarea>
	      <input type="hidden" id="myContent" name="myContent"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">是否置顶：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" name="stick" value="1" ${news.stick==1?'checked':''} checked>是
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" name="stick" value="0" ${news.stick==0?'checked':''}>否
	 	 </label>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">是否轮播：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" value="1" name="carousel"  ${news.carousel==1?'checked':''} checked>是
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="0" name="carousel"  ${news.carousel==0?'checked':''}>否
	 	 </label>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">是否草稿：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" value="1" name="draft"  ${news.draft==1?'checked':''} checked>是
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="0" name="draft"  ${news.draft==0?'checked':''}>否
	 	 </label>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="uid" name="uid" value="${news.uid }"/>
	      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)">返回</button>
	       <font color="red" id="error"></font>
	    </div>
	  </div>
	  </form>
  </div>
</div>
</body>
</html>