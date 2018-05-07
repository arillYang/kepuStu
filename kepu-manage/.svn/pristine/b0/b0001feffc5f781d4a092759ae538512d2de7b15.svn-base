<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function checkForm(){
		
		return true;
	}
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
					  $("#sitepic").val(data.url);
					});
					editor.hideDialog();
				}
			});
		});
	});
	});
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/product/linkSave" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">标题：</label>
	    <div class="col-sm-10">
	      <input type="text"  class="form-control" id="sitetitle" name="sitetitle" value="${outchain.sitetitle}" style="width: 300px">
	    </div>
	  </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">图片：</label>
	    <div class="col-sm-10">
           <input type="button" id="J_selectImage"  value="上传图片" />
           <input type="hidden" id="sitepic" name="sitepic"/>  
           <div id="J_imageView_hot">
               <c:if test="${outchain.sitepic!=null}">
	           		<img src="${outchain.sitepic}" style="width:20%;height:20%;" >
	           	</c:if>
           	</div>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">类型：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" name="sitetype" value="1" ${outchain.sitetype==1?'checked':''} checked>第三方链接
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" name="sitetype" value="2" ${outchain.sitetype==2?'checked':''}>app跳转链接
	 	 </label>
	 	 <label class="checkbox-inline">
	    	<input type="radio" name="sitetype" value="3" ${outchain.sitetype==3?'checked':''}>下载链接图片
	 	 </label>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">安卓外链：</label>
		    <div class="col-sm-10">
		       <input type="text" class="form-control" id="androidsite" name="androidsite" value="${outchain.androidsite}" style="width: 300px">
		    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">IOS外链：</label>
	    <div class="col-sm-10">
	       <input type="text" class="form-control" id="iossite" name="iossite" value="${outchain.iossite}" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="uid" name="uid" value="${outchain.uid }"/>
	      <input type="hidden" id="outchainId" name="outchainId" value="${outchainId}"/>
	      <input type="hidden" id="outchainid" name="outchainid" value="${outchain.outchainid}"/>
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