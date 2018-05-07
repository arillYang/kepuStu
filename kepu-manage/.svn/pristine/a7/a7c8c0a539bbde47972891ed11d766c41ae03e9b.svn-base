<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/user/list" method="post">

    </form>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>标题</th>
	  	<th>栏目名称</th>
	  	<th>推送时间</th>
	  	<th>类型</th>
	  	<th>推送范围</th>
	  </tr>
	  <c:forEach var="push" items="${list }" varStatus="status">
	  	<tr>
	  		<td>${push.title }</td>
	  		<td>${push.author}</td>
	  		<td><fmt:formatDate value="${push.pushtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td>${push.type==1?"新闻":(push.type==2?"乡镇新闻":"公告")}</td>
	  		<td>${push.address}</td>
	  	</tr>
	  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode} 
		</ul>
	</nav>
</div>



