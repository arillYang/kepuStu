<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function adminDelete(id,type){
	if(type==1){
		if(confirm("确定要解封该用户吗?")){
			$.post("${pageContext.request.contextPath}/user/admin/delete",{id:id,type:1},
				function(data){
					if(data.result=='error'){
						alert(result.errorInfo);
					}else{
						alert("解封成功");
						window.location.href="${pageContext.request.contextPath}/user/admin/list";
					}
				}
			);
		}
	}else{
		if(confirm("确定要封禁该用户吗?")){
			$.post("${pageContext.request.contextPath}/user/admin/delete",{id:id,type:0},
				function(data){
					/* var result=eval('('+result+')'); */
					if(data.result=='error'){
						alert(result.errorInfo);
					}else{
						alert("封禁成功");
						window.location.href="${pageContext.request.contextPath}/user/admin/list";
					}
				}
			);
		}
	}
}

</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/user/admin/list" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="username"  value="${tUser.username}" placeholder="请输入要查询的用户名...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/admin/preSave'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>序号</th>
	  	<th>用户名</th>
	  	<th>姓名</th>
	  	<!-- <th>角色名</th> -->
	  	<th>创建时间</th>
	  	<th>修改时间</th>
	  	<th>状态</th>
	  	<th>最后登录</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="tUser" items="${userList}" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${tUser.username}</td>
	  		<td>${tUser.truename}</td>
	  		<%-- <c:choose>
	  			<c:when test="${tUser.roleid==1}">
		  			<td>超级管理员</td>
		  		</c:when>
		  		<c:when test="${tUser.roleid==0}">
		  			<td>普通管理员</td>
		  		</c:when>
		  	</c:choose> --%>
	  		<td><fmt:formatDate value="${tUser.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td><fmt:formatDate value="${tUser.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<c:choose>
		  		<c:when test="${tUser.state==1}">
		  			<td>封禁</td>
		  		</c:when>
		  		<c:when test="${tUser.state==0}">
		  			<td>正常</td>
		  		</c:when>
	  		</c:choose>
	  		
  			<c:choose>
	  		<c:when test="${tUser.lastlogintime!=null}">
	  			<td><fmt:formatDate value="${tUser.lastlogintime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		</c:when>
	  		<c:when test="${tUser.lastlogintime==null}">
	  			<td>暂无登陆记录</td>
	  		</c:when>
  			</c:choose>
	  		  
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/admin/preSave?id=${tUser.id}'">编辑</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="adminDelete(${tUser.id},${tUser.state})">停用</button>
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



