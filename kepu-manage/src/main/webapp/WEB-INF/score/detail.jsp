<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<div class="row search" >
  <div class="col-md-12">
	<form action="${pageContext.request.contextPath}/activity/detail?id=${id}" method="post">
	    <div class="controls" style="float:left;margin-right:50px;margin-left:50px">
		            <label class="control-label" for="input01">时间</label>
		            <input id="t1" name="t1" value="${t1}" style="width:100px">
		            <label class="control-label" >至</label>
		            <input id="t2" name="t2"  value="${t2}" style="width:100px">
	    </div>
	     <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		 		<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/activity/list'">返回上一级</button>
		 </span>
    </form>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>昵称</th>
	  	<th>手机号</th>
	  	<th>操作时间</th>
	  	<th>积分</th>
	  	<th>信息摘要</th>
	  </tr>
	  <c:forEach var="detail" items="${list}" varStatus="status">
	  	<tr>
	  		<td>${detail.showname }</td>
	  		<td>${detail.mobile}</td>
	  		<td><fmt:formatDate value="${detail.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td>${detail.score}</td>
	  		<td>${detail.message}</td>
	  	</tr>
	  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode}</font>
		</ul>
	</nav>
</div>
<script>
	;!function(){
		laydate({
		   elem: '#t1',
		   format: 'YYYY-MM-DD', 
		})
		laydate({
		   elem: '#t2',
		   format: 'YYYY-MM-DD', 
		})
	}();
</script>



