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
	if(t==null||t=="1"||t==1){
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
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/product/auth/companySave" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">公司名：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="companyname" name="companyname" value="${apply.companyname}" style="width: 200px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">法人：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="corporate" name="corporate" value="${apply.corporate}" style="width: 200px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">联系电话：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="mobile" name="mobile" value="${apply.mobile}" style="width: 200px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">社会统一代码：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="socialcode" name="socialcode" value="${apply.socialcode}" style="width: 200px">
	    </div>
	  </div>
	  <div class="form-group">
	   	<label class="col-sm-2 control-label">营业执照正面图：</label>
		   <div class="col-sm-10">
		      <img src="${apply.licencez}" style="width:20%;height:20%"  onclick="showBigImg('${apply.licencez}')">
		   </div>
	  </div>
	  <div class="form-group">
	   	<label class="col-sm-2 control-label">营业执照反面图：</label>
		   <div class="col-sm-10">
		      <img src="${apply.licencef}" style="width:20%;height:20%" onclick="showBigImg('${apply.licencef}')">
		   </div>
	  </div>
	   <div class="form-group">
	   	<label class="col-sm-2 control-label">资格许可证正面图：</label>
		   <div class="col-sm-10">
		      <img src="${apply.permitz}" style="width:20%;height:20%" onclick="showBigImg('${apply.permitz}')">
		   </div>
	  </div>
	  <div class="form-group">
	   	<label class="col-sm-2 control-label">资格许可证反面图：</label>
		   <div class="col-sm-10">
		      <img src="${apply.permitf}" style="width:20%;height:20%" onclick="showBigImg('${apply.permitf}')">
		   </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">营业地址：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" value="${apply.address}" style="width: 200px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">企业详细介绍：</label>
	    <div class="col-sm-10">
	    	<textarea class="form-control" rows="5" style="width:50%" name="introduce">${apply.introduce}</textarea>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">审核：</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" name="state" value="0" ${apply.state==0?'checked':''}>通过
	 	 </label>
	    <label class="checkbox-inline">
	    	<input type="radio" name="state" value="2" ${apply.state==2?'checked':''}>拒绝
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