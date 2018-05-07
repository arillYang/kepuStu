<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/push/zyzn_1.css" type="text/css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/push/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/push/City_data.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/push/areadata.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/push/auto_area.js"></script>
<script type="text/javascript">
function yincang(){  
	 document.getElementById("xz").style.display="none";  
}
function show(){  
	 document.getElementById("xz").style.display="block";  
}
$(function() {
	var villageId='${villageId}';
	var townId='${townId}';
	if(townId!=0){
		document.getElementById("qx").style.display="none";  
		show();
	}
});
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/news/push/save" onsubmit="return checkForm()">	 
	 <div class="form-group" id="qx">
		 <div class="col-sm-10">
		    <label class="col-sm-2 control-label">全县推送：</label>
		    <label class="checkbox-inline">
		    	<input type="radio" name="xian" value="1"  onclick="yincang()">是 
		  	</label>
		  	<label class="checkbox-inline">
		    	<input type="radio" name="xian" value="0" onclick="show()">否
		 	 </label>
	 	 </div>
	 </div> 
	  <div class="form-group" id="xz" style="display:none" >
		    <div class="col-sm-10">
			    <label class="col-sm-2 control-label">乡镇多选：</label>
			     <c:if test="${villageId==0}">
		 		 	<input type="text" name="villageName" class="area-duoxuan" value=""  style="width:50%" data-value="" />
		  			<input type="hidden" id="vs" name="vs"  />
		 		 </c:if>
		 		 <c:if test="${villageId!=0}">
		 		 	<input type="text"  name="villageName" readonly value="${villageName}"  style="width:50%" />
		 		 	<input type="hidden" id="vs" name="vs" value="${villageId}" />
		 		 </c:if>
		   </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	       <input type="hidden" id="typeId" name="typeId" value="${typeId}"/>
	      <input type="hidden" id="type" name="type" value="${type}"/>
	      <button type="submit" class="btn btn-primary">推送</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)">返回</button>
	      <font color="red" id="error"></font>
	    </div>
	  </div>
	  </form>
  </div>
</div>
</body>
</html>