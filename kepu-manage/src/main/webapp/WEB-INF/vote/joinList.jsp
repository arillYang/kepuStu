<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/vote/joinUser" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="name"  value="${name}" placeholder="请输入要查询的姓名...">
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
		  	<th>排名</th>
		  	<th>姓名</th>
		  	<th>报名时间</th>
		  	<th>得票数</th>
		  	<th>报名编号</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="joiner" items="${voteList}" varStatus="status">
		  	<tr>
		  		<td>${status.index+1+pm}</td>
		  		<td>${joiner.name}</td>
		  		<td><fmt:formatDate value="${joiner.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		  		<td>${joiner.votenum}</td>
		  		<td>${joiner.number}</td>	    
		  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/vote/user?id=${joiner.uid}'">查看报名信息</button>
		  		</td>
		  	</tr>
		  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode}
		</ul>
	</nav>
</div>



