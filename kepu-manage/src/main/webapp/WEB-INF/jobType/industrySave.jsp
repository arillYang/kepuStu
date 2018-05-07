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
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/industry/save" onsubmit="return checkForm()">
	   <div class="form-group">
	    <label class="col-sm-2 control-label">行业：<font color="red" >(以英文逗号分隔)</font></label>
	    <div class="col-sm-10">
	      <textarea  name="industry"  onkeyup="value=value.replace(/，/ig,',')"   style="width:400px;height:150px;">${industry}</textarea>
	      <%-- <input type="text" onkeyup="value=value.replace(/[^\u4E00-\u9FA5,]/g,'')"  class="form-control" id="industry" name="industry" value="${industry}" style="width: 700px"> --%>
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
</body>
</html>