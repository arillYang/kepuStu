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
	  	<th>昵称</th>
	  	<th width="50%">内容</th>
	  	<th>提交时间</th>
	  	<th>手机号</th>
	  </tr>
	  <c:forEach var="advice" items="${adviceList }" varStatus="status">
	  	<tr>
	  		<td>${advice.nickname }</td>
	  		<td>${advice.advice}</td>
	  		<td><fmt:formatDate value="${advice.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td>${advice.mobile}</td>
	  	</tr>
	  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode}
		</ul>
	</nav>
</div>



