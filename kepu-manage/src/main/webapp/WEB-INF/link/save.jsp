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
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<script type="text/javascript">
function qyincang(){  
	 document.getElementById("q1").style.display="none";  
	 
}
function qshow(){  
	 document.getElementById("q1").style.display="block";  
}
	KindEditor.ready(function(K) {
		var editor = K.editor({
			allowFileManager : true,
			allowImageUpload : true,//运行上传图片  
			uploadJson : "${pageContext.request.contextPath}"+'/news/upload' //  服务端上传图片处理URI
		});
		K('#J_selectImage_lunbo').click(function() {
			editor.loadPlugin('multiimage', function() {
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var div = K('#J_imageView_luobo'); 
						div.html('');
						K.each(urlList, function(i, data) {
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
							$("#lunbo").val(data.url);
							$("#lunbo_pic").attr("href",data.url); 
						});
						editor.hideDialog();
					}
				});
			});
		});
	});
	
	function checkForm(){
		return true;
	}
	$(function() {
		 $('input[name="classifyid"]').change(function(){
		        $('#classifyids').val($('input[name="classifyid"]:checked').map(function(){return this.value}).get().join(','))
		      })
	    var boxObj = $("input:checkbox[name='classifyid']");   
	    var expresslist ='${typeList}';
   		var express = expresslist.split(','); 
   		var q='${link.type}';
   		if(q==1)
   			qshow();
	    $.each(express, function(index, expressId){  
	       boxObj.each(function () {  
	            if($(this).val() == expressId) {  
	               $(this).attr("checked",true);  
	            }  
	        });  
	    });
	});
	function CheckAll(val) { 
		 $("input[name='classifyid']").each(function() { 
		 	this.checked = val; 
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
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/news/link/save" onsubmit="return checkForm()">
	  <div class="form-group">
	    <label class="col-sm-2 control-label">外链：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="link" name="link" value="${link.link}" style="width: 500px">
	    </div>
	  </div>
	   <div class="form-group">
	      <label class="col-sm-2 control-label">轮播图片：</label>
	       <a  id="lunbo_pic" href="${link.lunbo}" target="_blank">图片链接</a>
           <input type="button" id="J_selectImage_lunbo" class="picFileUpload" value="上传图片" />
           <input type="hidden" id="lunbo" name="lunbo"/>
           <div id="J_imageView_luobo"></div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">类型：</label>
	    <div class="col-sm-10">
	     <label class="checkbox-inline">
	    	<input type="radio" value="1" name="type" onclick="qshow()" ${link.type==1?'checked':''}>新闻
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="2" name="type"  onclick="qyincang()" ${link.type==2?'checked':''} >乡镇新闻
	 	 </label>
	 	   <label class="checkbox-inline">
	    	<input type="radio" value="3" name="type" onclick="qyincang()" ${link.type==3?'checked':''}>乡镇公告
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="4" name="type"  onclick="qyincang()" ${link.type==4?'checked':''} >服务
	 	 </label>
	 	   <label class="checkbox-inline">
	    	<input type="radio" value="5" name="type" onclick="qyincang()" ${link.type==5?'checked':''}>商品
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="6" name="type"  onclick="qyincang()" ${link.type==6?'checked':''} >技能
	 	 </label>
	 	   <label class="checkbox-inline">
	    	<input type="radio" value="7" name="type" onclick="qyincang()" ${link.type==7?'checked':''}>任务
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="8" name="type"  onclick="qyincang()" ${link.type==8?'checked':''} >招聘
	 	 </label>
	 	 <label class="checkbox-inline">
	    	<input type="radio" value="9" name="type"  onclick="qyincang()" ${link.type==9?'checked':''} >出租出售
	 	 </label>
	 	 </div>
	  </div>
	  <div class="form-group" id="q1" style="display:none">
	    <label class="col-sm-2 control-label">轮播分类：</label>
	     <div class="col-sm-10">
	    	<c:forEach var="classify" items="${classifyList}" varStatus="status">
    				<label class="checkbox-inline">
    					<input type="checkbox" name="classifyid" value="${classify.uid}">${classify.classifyname}
    				</label>
	        </c:forEach>
	         <input type="hidden" name="classifyids" id="classifyids">
	     </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="id" name="id" value="${link.id }"/>
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