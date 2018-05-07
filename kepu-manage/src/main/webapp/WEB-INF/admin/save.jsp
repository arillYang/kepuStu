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
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script> --%>
<script type="text/javascript">
	function checkForm(){
		var id=${tUser.id==null?-1:tUser.id};
		var roleid=$("#roleid").val();
		if(id==-1){
			var password=$("#password").val();
			if(password==''||password==null){
				$("#error").html("请设置密码");
				return false;
			}
		}
		if(roleid=='-1'||roleid==null){
			$("#error").html("请设置角色");
			return false;
		}
			
		return true;
	}
	$(function () {
	    //默认绑定乡镇
	    TownBind();
	    //绑定事件
	    $("#town").change( function () {
	    	villageBind();
	    })
	})
	function TownBind() {
		var v=$("#town").val();
		if(v==null)
		$("#town").html("");
		var str = "<option value='-100'>==请选择===</option>";
	    $.ajax({
	        type: "POST",
	        url: "${pageContext.request.contextPath}"+'/user/getAddress',
	        data: { "parentId":"-1"},
	        dataType: "JSON",
	        async: false,
	        success: function (data) {
	            $.each(data, function (i, item) {
	                str += "<option value=" + item.id + ">" + item.name + "</option>";
	            })
	            $("#town").append(str);
	        },
	        error: function () { alert("Error"); }
	    });
	}
	function villageBind() {
		var town = $("#town").val();
	    //判断省份这个下拉框选中的值是否为空
	    if (town == ""||town==null) {
	        return;
	    }
	    	$("#village").html("");
	    var str = "<option value='-100'>==请选择===</option>";
		$.ajax({
	        type: "POST",
	        url:"${pageContext.request.contextPath}"+'/user/getAddress',
	        data: { "parentId": town},
	        dataType: "JSON",
	        async: false,
	        success: function (data) {
	            $.each(data, function (i, item) {
	                str += "<option value=" + item.id + ">" + item.name + "</option>";
	            })
	            $("#village").append(str);
	        },
	        error: function () { alert("Error"); }
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
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/admin/save" onsubmit="return checkForm()">
	  <div class="form-group">
	    <label class="col-sm-2 control-label">用户名：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="username" name="username" value="${tUser.username}" style="width: 150px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">姓名：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="truename" name="truename" value="${tUser.truename}" style="width: 150px">
	    </div>
	  </div>
	  <div class="form-group">
	     <label class="col-sm-2 control-label">权限：</label>
	      <div class="col-sm-10">
		       <select class="form-control" style="width:120px;float:left" id="town" name="town">
		    		<c:if test="${town!=null}">
		    			<option value="${town}" selected>${townName}</option>
		    		</c:if>
		    		<c:if test="${town==null}">
		    			<option value="">请选择...</option>
		    		</c:if>
		    		
		    	</select>
		       <select class="form-control" style="width:120px;float:left;margin-left:10px" id="village" name="village">
		    		<c:if test="${village!=null}">
		    			<option value="${village}" selected>${villageName}</option>
		    		</c:if>
		    		<c:if test="${village==null}">
		    			<option value="">请选择...</option>
		    		</c:if>
		    	</select>
		    	</div>
	    </div>
	   <div class="form-group">
	     <label class="col-sm-2 control-label">角色：</label>
	      <div class="col-sm-10">
		       <select class="form-control" style="width:120px;float:left" id="roleid" name="roleid">
		       			<option value='-1'>==请选择===</option>
		    		<c:forEach var="role" items="${roleList}" varStatus="status">
	           			<option value="${role.uid}" ${tUser.roleid==role.uid?'selected':''} >${role.rolename}</option>
		        	</c:forEach>
		    	</select>
		  </div>
	    </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">重置密码：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="password" name="password"  style="width: 200px">
	      <div style="color:red">不修改则留空</div>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="id" name="id" value="${tUser.id }"/>
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