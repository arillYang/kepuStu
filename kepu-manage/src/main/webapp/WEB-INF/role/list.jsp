<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function roleDelete(id){
	
		if(confirm("确定删除该角色吗?")){
			$.post("${pageContext.request.contextPath}/user/role/delete",{id:id,type:1},
				function(data){
					if(data.result=='error'){
						alert(data.errorInfo);
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/user/role/list";
					}
				}
			);
		}
}

</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/user/role/list" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="rolename"  value="${role.rolename}" placeholder="请输入角色名...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/role/preSave'">添加角色</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>序号</th>
	  	<th>角色名</th>
	  	<th>描述</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="role" items="${list}" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${role.rolename}</td>
	  		<td>${role.description}</td>  
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/role/preSave?id=${role.uid}'">修改</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="roleDelete(${role.uid})">删除</button>
	  		</td>
	  	</tr>
	  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode }
		</ul>
	</nav>
</div>



