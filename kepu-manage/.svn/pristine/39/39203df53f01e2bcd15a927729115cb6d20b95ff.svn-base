<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function expExcel(){
		var param=""; 
		var town = $("#town").val();
		var village = $("#village").val();
		param+="&town="+town;
		param+="&village="+$("#village").val();
		param=param.replace("&","?")
		var href="${pageContext.request.contextPath}/user/export"+param;
		//location.href=href; 
		window.open(href);
		    /* 这里不能用ajax请求，ajax请求无法弹出下载保存对话框     */
	}
	function userDelete(id,type){
		if(type==-1){
			if(confirm("确定要解封该用户吗?")){
				$.post("${pageContext.request.contextPath}/admin/user/delete",{id:id,type:0},
					function(data){
						/* var result=eval('('+result+')'); */
						if(data.result=='error'){
							alert(result.errorInfo);
						}else{
							alert("解封成功");
							window.location.href="${pageContext.request.contextPath}/admin/user/list";
						}
					}
				);
			}
		}else{
			if(confirm("确定要封禁该用户吗?")){
				$.post("${pageContext.request.contextPath}/admin/user/delete",{id:id,type:-1},
					function(data){
						/* var result=eval('('+result+')'); */
						if(data.result=='error'){
							alert(result.errorInfo);
						}else{
							alert("封禁成功");
							window.location.href="${pageContext.request.contextPath}/admin/user/list";
						}
					}
				);
			}
		}
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
	
	
	
	
    function changeText(id, status) {
    	alert("3");
        var text = document.getElementById("btn" + id).value;
        if ("解冻" == text) {
            window.location.href = "#" + id + "&state=" + status;
            document.getElementById("btn" + id).value = "解冻";
        }
        else {
            window.location.href = "#" + id + "&state=" + status;
            document.getElementById("btn" + id).value = "冻结";
        }
    }
	
	
	
</script>
<div class="row search" >
fddf
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/user/list" method="post">
	    <div class="input-group" style="width: 450px">
		      <%-- <input type="text" class="form-control" name="nickname"  value="${user.nickname }" placeholder="请输入要查询的用户名..."> --%>
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
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
<!-- 		        <a href="#" class="easyui-linkbutton" onclick="javascript:exportExcel()" iconCls="icon-save" plain="true" title="导出excel文件">导出excel</a>
 -->		        <button class="btn btn-default" onclick="javascript:expExcel()"  style="margin-left:10px"><span class="glyphicon"></span>&nbsp;导出</button>
		      </span>
	    </div>
    </form>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>昵称</th>
	  	<th>行政区</th>
	  	<th>手机号</th>
	  	<th>注册时间</th>
	  	<th>性别</th>
	  	<th>职业</th>
	  	<th>学历</th>
	  	<th>生日</th>
	  	<th>活动地</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="user" items="${userList }" varStatus="status">
	  	<tr>
	  		<td>${user.nickname }</td>
	  		<td>${addressList[status.index]}</td>
	  		<td>${user.mobile}</td>
	  		<td><fmt:formatDate value="${user.regtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td>${user.sex==1?"男":"女"}</td>
	  		<td >${careerList[user.career-1]}</td>
	  		<td>${eduList[user.education-1]}</td>
	  		<td><fmt:formatDate value="${user.birthday}" pattern="yyyy/MM"/></td>
	  		<td>${user.userid}</td>
	  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/userAccount/selectUserById?userId=${user.userid}'">查看</button>
		  	<%-- 		<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/findUser'">修改</button><!--  --> --%>
<%-- 		  			<button type="button" class="btn btn-danger btn-xs"  id="btn${user.userid}" onclick="changeText(${user.userid},${user.state})">
		  			<c:if test="${user.state eq 2}">冻结</c:if><c:if test="${user.state eq 0}">解冻</c:if>
		  			</button> --%>
		  	</td>
	  	</tr>
	  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode} <font color=red size=4 >,其中男${manNum}人,女${womanNum}人</font>
		</ul>
	</nav>
</div>



