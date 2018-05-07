<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">

</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/product/auth/companylist" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="companyname"  value="${apply.companyname}" placeholder="请输入公司名...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>序号</th>
		  	<th>公司名</th>
		  	<th>手机号</th>
		  	<th>申请时间</th>
		  	<th>状态</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="apply" items="${applyList}" varStatus="status">
		  	<tr>
		  		<td >${status.index+1}</td>
		  		<td >${apply.companyname}</td>   
		  		<td >${apply.mobile}</td>
		  		<td><fmt:formatDate value="${apply.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		  		<td >${apply.state==0?"通过":(apply.state==1?"待审核":"拒绝")}</td>
		  		<td>
		  			<c:if test="${apply.state==1}">
		  				<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/auth/companyPreSave?id=${apply.uid}'">审核</button>		  			
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



