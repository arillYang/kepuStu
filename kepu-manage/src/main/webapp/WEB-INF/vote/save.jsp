<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/laydateV509/laydate.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	$(function() {
		
	});
	KindEditor.ready(function(K) {
		var editor = K.editor({
			allowFileManager : true,
			allowImageUpload : true,
			uploadJson : "${pageContext.request.contextPath}"+'/news/upload' 
		});
		K('#J_selectImage').click(function() {
			editor.loadPlugin('multiimage', function() {
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
						  $("#voteimages").val(imgArray.join(","));
						editor.hideDialog();
					}
				});
			});
		});
	});
	function checkForm(){
		var joinstart=$('#joinstart').val();
		var joinend=$('#joinend').val();
		var votestart=$('#votestart').val();
		var voteend=$('#voteend').val();
		if(joinstart>joinend){
			$("#error").html("报名结束时间不能早于报名开始时间");
			return false;
		}
		if(votestart>voteend){
			$("#error").html("投票结束时间不能早于投票开始时间");
			return false;
		}	
		return true;
	}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName}</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" id="myForm" method="post" action="${pageContext.request.contextPath}/vote/save" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">标题：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="title" name="title" value="${vote.title}" style="width: 600px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">样式分类：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" name="type" value="1" ${vote.type==1?'checked':''} >大图
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" name="type" value="2" ${vote.type==2?'checked':''}>并列图
	 	 </label>
	 	 <label class="checkbox-inline">
	    	<input type="radio" name="type" value="3" ${vote.type==3?'checked':''}>瀑布图
	 	 </label>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">投票封面图片：</label>
	    <div class="col-sm-10">
           <input type="button" id="J_selectImage"  value="上传图片" />
           <input type="hidden" id="voteimages" name="voteimages"/>  
           <div id="J_imageView_hot">
		         <img src="${vote.displaypic}" style="width:20%;height:20%;" >
           	</div>
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">报名开始时间：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="joinstart" name="t1" value="<fmt:formatDate value='${vote.joinstart}' pattern='yyyy-MM-dd  HH:mm:ss'/>" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">报名结束时间：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="joinend" name="t2" value="<fmt:formatDate value='${vote.joinend}' pattern='yyyy-MM-dd  HH:mm:ss'/>" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">投票开始时间：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="votestart" name="t3" value="<fmt:formatDate value='${vote.votestart}' pattern='yyyy-MM-dd  HH:mm:ss'/>" style="width: 300px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">投票结束时间：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="voteend" name="t4" value="<fmt:formatDate value='${vote.voteend}' pattern='yyyy-MM-dd  HH:mm:ss'/>" style="width: 300px">
	    </div>
	  </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">活动时间：</label>
	    <div class="col-sm-10">
	      <textarea  name="activityTime" style="width:350px;height:100px;">${activityTime}</textarea>
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">活动介绍：</label>
	    <div class="col-sm-10">
	      <textarea  name="activityIntr" style="width:350px;height:100px;">${activityIntr}</textarea>
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">参与对象：</label>
	    <div class="col-sm-10">
	      <textarea  name="activityObj" style="width:350px;height:100px;">${activityObj}</textarea>
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">单IP可投：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="iplimit" name="iplimit" value="${vote.iplimit}" style="width: 50px">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="uid" name="uid" value="${vote.uid }"/>
	      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)">返回</button>
	      <br><font color="red" id="error"></font>
	    </div>
	  </div>
	  </form>
  </div>
</div>
<script>
	 //时间选择器
	 laydate.render({
	  elem: '#joinstart',
	  type: 'datetime'
	  });
	 laydate.render({
	  elem: '#joinend',
	  type: 'datetime'
	  });
	 laydate.render({
	  elem: '#votestart',
	  type: 'datetime'
	  });
	 laydate.render({
	  elem: '#voteend',
	  type: 'datetime'
	  });
</script>
</body>
</html>