<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">

</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/village/country/list/${parent}" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="name"  value="${village.name }" placeholder="请输入要查询的名称...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
    	<button type="button" class="btn btn-primary" style="float: right;margin-left:20px" onclick="javascript:window.location.href='${pageContext.request.contextPath}/village/country/preSave?parent=${parent}'">添加</button>
  		<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/village/town/list'">返回上一级</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>序号</th>
		  	<th>名称</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="village" items="${villageList}" varStatus="status">
		  	<tr>
		  		<td >${status.index+1}</td>
		  		<td >${village.name}</td>   
		  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/village/country/preSave?id=${village.id}'">编辑</button>		  			
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



