<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">

</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/community/apply/list/${communityId}" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="username"  value="${apply.username}" placeholder="请输入要查询的姓名...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float:right" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/list'">返回社团</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>序号</th>
		  	<th>姓名</th>
		  	<th>手机号</th>
		  	<th>职务</th>
		  	<th>申请时间</th>
		  	<th>状态</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="apply" items="${applyList}" varStatus="status">
		  	<tr>
		  		<td >${status.index+1}</td>
		  		<td >${apply.username}</td>   
		  		<td >${apply.mobile}</td>
		  		<td >${apply.position}</td>
		  		<td><fmt:formatDate value="${apply.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		  		<td >${apply.state==0?"申请中":(apply.state==1?"拒绝":"通过")}</td>
		  		<td>
		  			<c:if test="${apply.state==0}">
		  				<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/apply/preSave/${communityId}?id=${apply.uid}'">审核</button>		  			
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



