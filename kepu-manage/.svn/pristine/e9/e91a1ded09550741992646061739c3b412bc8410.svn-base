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
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/product/skillSave" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">标题：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="title" name="title" value="${product.title}" style="width: 300px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">介绍：</label>
	    <div class="col-sm-10">
	      <textarea class="form-control" rows="5" style="width:50%" name="introduce">${product.introduce}</textarea>
	    </div>
	  </div>
	   <div class="form-group">
	   	<label class="col-sm-2 control-label">封面图片：</label>
		   <div class="col-sm-10">
		      <img src="${product.coverpic}" style="width:10%;height:10%" >
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
	    <label class="col-sm-2 control-label">联系人：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="realname" name="realname" value="${product.realname}" style="width: 150px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">联系方式：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="mobile" name="mobile" value="${product.mobile}" style="width: 150px">
	    </div>
	  </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">联系地址：</label>
	    <div class="col-sm-10">
	      <textarea  name="address" style="width:400px;height:150px;">${product.address}</textarea>
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">价格：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="price" name="price" value="${product.price}" style="width: 150px">
	    </div>
	  </div>
	  <c:if test="${product.state==2}">
		   <div class="form-group">
		    <label class="col-sm-2 control-label">审核：</label>
		  	<label class="checkbox-inline">
		    	<input type="radio" name="state" value="0" >通过
		 	 </label>
		    <label class="checkbox-inline">
		    	<input type="radio" name="state" value="3" >不通过
		  	</label>
		  </div>
	  </c:if>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="uid" name="uid" value="${product.uid }"/>
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