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
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/myJob/jobSave" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">职位：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="jobname" name="jobname" value="${job.jobname}" style="width: 300px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">详细描述：</label>
	    <div class="col-sm-10">
	      <textarea class="form-control" rows="5" style="width:50%" name="description">${job.description}</textarea>
	    </div>
	  </div>
	   <div class="form-group">
	   	<label class="col-sm-2 control-label">封面图片：</label>
		   <div class="col-sm-10">
		      <img src="${job.coverpic}" style="width:10%;height:10%" >
		   </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">详情图片：</label>
	    <div class="col-sm-10">
           <div id="J_imageView_hot">
           		<c:forEach var="pic" items="${logo}" varStatus="status">
	           		<img src="${pic}" style="width:10%;height:10%" >
	        	</c:forEach>
           	</div>
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
	    <label class="col-sm-2 control-label">工作经验：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="workexperience" name="workexperience" value="${job.workexperience}" style="width: 600px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">技能要求：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="jobrequire" name="jobrequire" value="${job.jobrequire}" style="width: 600px">
	    </div>
	  </div>
	  <%-- <c:if test="${job.state==2}">
		   <div class="form-group">
		    <label class="col-sm-2 control-label">审核：</label>
		  	<label class="checkbox-inline">
		    	<input type="radio" name="state" value="0" >通过
		 	 </label>
		    <label class="checkbox-inline">
		    	<input type="radio" name="state" value="3" >不通过
		  	</label>
		  </div>
	  </c:if> --%>
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