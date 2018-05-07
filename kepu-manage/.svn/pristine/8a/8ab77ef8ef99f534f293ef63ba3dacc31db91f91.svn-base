<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">

</script>
<div class="row search" >
  <div class="col-md-6">
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>序号</th>
		  	<th>名称</th>
		  	<th>类型</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="menu" items="${menuList}" varStatus="status">
		  	<tr>
		  		<td >${status.index+1}</td>
		  		<td >${menu.tabname}</td>   
		  		<td >${menu.tabtype==1?"外链":(menu.tabtype==2?"服务":"更多")}</td>   
		  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/indexPreSave?id=${menu.tabid}'">编辑</button>
		  			<c:if test="${menu.tabtype==4}">
		  				<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/smallClass'">更多管理</button>
		  			</c:if>
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



