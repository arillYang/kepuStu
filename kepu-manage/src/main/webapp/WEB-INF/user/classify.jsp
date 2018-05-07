<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	$(function () {
	})
	
	function checkForm(){
		
		return true;
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/user/classify/list" method="post">
	    <div class="input-group" style="width: 850px">
	          <input type="text" class="form-control" id="name" name="name" style="width:300px" value="${name}" placeholder="请输入要查询的分类名...">
		      <input type="week" id="week" name="week" value="${week}" style="margin-top:3px;margin-left:20px"/>
		       <input type="week" id="week2" name="week2" value="${week2}" style="margin-top:3px;margin-left:20px"/>
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
	  	<th>分类名</th>
	  	<th>文章数</th>
	  	<th>阅读数</th>
	  </tr>
	  <c:forEach var="statistic" items="${list}" varStatus="status">
	  	<tr>
	  		<%-- <td>${statistic.classifyResult.classifyName}</td>
	  		<td>${statistic.classifyResult.pm}</td>
	  		<td>${statistic.view}</td> --%>
	  		<td>${statistic.classifyname}</td>
	  		<td>${statistic.pm}</td>
	  		<td>${statistic.view}</td>
	  	</tr>
	  </c:forEach>
	  <%-- <tr>
	  		<td>合计</td>
	  		<td>${classifyNum}</td>
	  		<td>${classifyView}</td>
	  	</tr> --%>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode}
		</ul>
	</nav>
</div>


