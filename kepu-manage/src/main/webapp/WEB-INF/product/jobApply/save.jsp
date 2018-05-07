<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkForm(){
		
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
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/myJob/jobApplySave" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">标题：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="title" name="title" value="${job.title}" style="width: 300px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">详细描述：</label>
	    <div class="col-sm-10">
	      <textarea class="form-control" rows="5" style="width:50%" name="description">${job.description}</textarea>
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">发布人：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="realname" name="realname" value="${job.realname}" style="width: 150px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">联系方式：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="mobile" name="mobile" value="${job.mobile}" style="width: 150px">
	    </div>
	  </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">联系地址：</label>
	    <div class="col-sm-10">
	      <textarea  name="address" style="width:400px;height:150px;">${job.address}</textarea>
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">行业：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="hy" name="hy" value="${job.hy}" style="width: 600px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">职位：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="position" name="position" value="${job.position}" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="uid" name="uid" value="${job.uid }"/>
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