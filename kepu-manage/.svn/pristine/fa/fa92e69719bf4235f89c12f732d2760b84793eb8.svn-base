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
<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

Latest compiled and minified JavaScript
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

(Optional) Latest compiled and minified JavaScript translation files
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script> -->
<script type="text/javascript">
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
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var div = K('#J_imageView_hot');
						div.html('');
						K.each(urlList, function(i, data) {
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
							$("#pic_hot").attr("href",data.url); 
							$("#pic").val(data.url);
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
		/* var t=$("#town").val()
		if(t==null||t==""){
			$("#error").html("请选择乡镇");
			return false;
		} */
		return true;
	}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/village/town/save" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">名称：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control"  id="name" name="name" value="${village.name}" style="width: 200px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">logo：</label>
	    <div class="col-sm-10">
           <input type="button" id="J_selectImage"  value="上传图片" />
           <input type="hidden" id="pic" name="pic"/>  
           <div id="J_imageView_hot">
	           	<img src="${village.pic}" style="width:20%;height:20%;" >
           	</div>
	    </div>
	  </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">乡镇简介：</label>
	    <div class="col-sm-10">
	      <textarea  name="realcontent" style="width:650px;height:500px;visibility:hidden;">${realcontent.htmlcontent}</textarea>
	      <input type="hidden" id="myContent" name="myContent"/>
	    </div>
	  </div>
	 
	  
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="id" name="id" value="${village.id }"/>
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