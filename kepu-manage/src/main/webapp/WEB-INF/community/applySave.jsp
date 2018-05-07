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
<script type="text/javascript">
function checkForm(){
    var t=$("input[name='state']:checked").val();
	if(t==null||t=="0"||t==0){
		$("#error").html("请选择审核结果");
		return false;
	}
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
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/community/apply/save/${communityId}" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">姓名：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="username" name="username" value="${apply.username}" style="width: 200px">
	    </div>
	  </div>
	  <%--  <div class="form-group">
	    <label class="col-sm-2 control-label">性别：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="sex" name="sex" value="${apply.sex}" style="width: 200px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">出生年月：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="birthday" name="birthday" value="${apply.birthday}" style="width: 200px">
	    </div>
	  </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">职业：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="career" name="career" value="${apply.career}" style="width: 200px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">职称：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="positional" name="positional" value="${apply.positional}" style="width: 200px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">教育程度：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="education" name="education" value="${apply.education}" style="width: 200px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">头像：</label>
	    <div class="col-sm-10">
	      <img src="${apply.photo}" style="width:20%;height:20%" >
	    </div>
	  </div> --%>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">手机号：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="mobile" name="mobile" value="${apply.mobile}" style="width: 200px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">职务：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="position" name="position" value="${apply.position}" style="width: 200px">
	    </div>
	  </div>
	  <div class="form-group">
	   	<label class="col-sm-2 control-label">身份证(正)：</label>
		   <div class="col-sm-10">
		      <img src="${apply.idcardz}" style="width:20%;height:20%" >
		   </div>
	  </div>
	  <div class="form-group">
	   	<label class="col-sm-2 control-label">身份证(反)：</label>
		   <div class="col-sm-10">
		      <img src="${apply.idcardf}" style="width:20%;height:20%" >
		   </div>
	  </div>
	   <div class="form-group">
	   	<label class="col-sm-2 control-label">证件：</label>
		   <div class="col-sm-10">
		      <img src="${apply.credential}" style="width:20%;height:20%" >
		   </div>
	  </div>
	 <%--   <div class="form-group">
	   	<label class="col-sm-2 control-label">手持正面身份证：</label>
		   <div class="col-sm-10">
		      <img src="${apply.idcardzhold}" style="width:20%;height:20%" >
		   </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">自我介绍：</label>
	    <div class="col-sm-10">
	    	<textarea class="form-control" rows="5" style="width:50%" name="introduce">${apply.selfintroduction}</textarea>
	    </div>
	  </div> --%>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">审核：</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" name="state" value="2" ${apply.state==2?'checked':''}>通过
	 	 </label>
	    <label class="checkbox-inline">
	    	<input type="radio" name="state" value="1" ${apply.state==1?'checked':''}>拒绝
	  	</label>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="uid" name="uid" value="${apply.uid }"/>
	       <input type="hidden" id="userid" name="userid" value="${apply.userid }"/>
	      <button type="submit" class="btn btn-primary">提交</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)">取消</button>
	      <font color="red" id="error"></font>
	    </div>
	  </div>
	  </form>
  </div>
</div>
</body>
</html>