<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function newsDelete(id){
	if(confirm("确定要删除这条记录吗?")){
		$.post("${pageContext.request.contextPath}/product/smallClassDelete",{id:id},
			function(data){
			     if(data.result=='error'){
					alert(data.errorInfo);
				}else{
					alert("删除成功");
					window.location.href="${pageContext.request.contextPath}/product/smallClass";
				}
			}
		);
	}
}
</script>
  <div class="col-md-12" >
    <button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/index?page=1'">返回</button>
  	<button type="button" class="btn btn-primary" style="float: right;margin-right:10px" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/smallClassPreSave'">添加</button>
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
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/smallClassPreSave?id=${menu.tabid}'">编辑</button>
		  			<button type="button" class="btn btn-danger btn-xs" onclick="newsDelete(${menu.tabid})">删除</button>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/link?outchainId=${menu.tabid}'">外链管理</button>
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



