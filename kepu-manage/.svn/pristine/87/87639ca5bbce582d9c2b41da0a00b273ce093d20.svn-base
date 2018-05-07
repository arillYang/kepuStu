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
	function checkForm(){
	
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
    <form class="form-horizontal" id="myForm" method="post" action="" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">姓名：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="title" name="title" value="${joiner.title}" style="width: 600px">
	    </div>
	  </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">详细介绍：</label>
	    <div class="col-sm-10">
	      <textarea  name="description" style="width:350px;height:100px;">${joiner.description}</textarea>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">封面图片：</label>
	    <div class="col-sm-10"> 
           <div id="J_imageView_hot">
		         <img src="${joiner.coverpic}" style="width:20%;height:20%;" >
           	</div>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">图片详情：</label>
	    <div class="col-sm-10">
           <div id="J_imageView_hot">
	           		<c:forEach var="pic" items="${logo}" varStatus="status">
	           			<c:if test="${pic!=null}">
		           			<img src="${pic}" style="width:20%;height:20%;" >
		           		</c:if>
		        	</c:forEach>
           	</div>
	    </div>
	  </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">视频详情：</label>
	    <div class="col-sm-10">
           <div id="J_imageView_hot">
	           		<c:forEach var="video" items="${videoList}" varStatus="status">
	           			<c:if test="${video!=null}">
		           			<video src="${video}" style="width:20%;height:20%;" controls="controls"> 您的浏览器不支持 video 标签。 </video> 
		           		</c:if>
		        	</c:forEach>
           	</div>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">联系方式：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="joinend"  value="${joiner.mobile}" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">联系地址：</label>
	    <div class="col-sm-10">
	      <textarea   style="width:350px;height:100px;">${joiner.address}</textarea>
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	     <%--  <input type="hidden" id="uid" name="uid" value="${vote.uid }"/>
	      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp; --%>
	      <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)">返回</button>
	      <br><font color="red" id="error"></font>
	    </div>
	  </div>
	  </form>
  </div>
</div>
</body>
</html>