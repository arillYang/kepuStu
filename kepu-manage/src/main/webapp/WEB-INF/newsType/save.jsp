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
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/news/type/save" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">分类名：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="classifyname" name="classifyname" value="${classify.classifyname}" style="width: 150px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">是否固定：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" name="fixed" value="1" ${classify.fixed==1?'checked':''} checked>是
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" name="fixed" value="0" ${classify.fixed==0?'checked':''}>否
	 	 </label>
	  </div>
	   <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="uid" name="uid" value="${classify.uid }"/>
	      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)">返回</button>
	    </div>
	  </div>
	  </form>
  </div>
</div>
</body>
</html>