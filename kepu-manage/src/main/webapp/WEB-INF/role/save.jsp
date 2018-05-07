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
	$(function() {
		var boxObj = $("input:checkbox[name='menuIds']");   
	    var expresslist ='${typeList}';
   		var express = expresslist.split(','); 
	    $.each(express, function(index, expressId){  
	       boxObj.each(function () {  
	            if($(this).val() == expressId) {  
	               $(this).attr("checked",true);  
	            }  
	        });  
	    });  
		 $('input[name="menuIds"]').change(function(){
		        $('#mids').val($('input[name="menuIds"]:checked').map(function(){return this.value}).get().join(','))
		  });
	});
	function CheckAll(val) { 
		 $("input[name='menuIds']").each(function() { 
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
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/role/save" onsubmit="return checkForm()">
	  <div class="form-group">
	    <label class="col-sm-2 control-label">角色名：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="rolename" name="rolename" value="${role.rolename}" style="width: 150px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">描述：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="description" name="description" value="${role.description}" style="width: 550px">
	    </div>
	  </div>
	  <c:if test="${role.uid!=1}">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">权限菜单：</label>
		     <div class="col-sm-10">
		    	<c:forEach var="menu" items="${menuList}" varStatus="status">
		    		 <br><label>${menu.key}</label><br>
		    		 <c:forEach var="m" items="${menu.value}" varStatus="status2">
	    				<label class="checkbox-inline">
	    					<input type="checkbox" name="menuIds" value="${m.uid}">${m.menuname}
	    				</label>
	    			</c:forEach>
	    			<br>
		        </c:forEach>
		         <input type="hidden" name="mids" id="mids">
		     </div>
		  </div>
	  </c:if>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="uid" name="uid" value="${role.uid }"/>
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