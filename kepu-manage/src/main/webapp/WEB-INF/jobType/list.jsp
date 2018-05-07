<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function myDelete(id){
	if(confirm("确定要删除这条记录吗?")){
		$.post("${pageContext.request.contextPath}/myJob/classify/job/delete",{id:id},
			function(data){
			     if(data.result=='error'){
					alert(data.errorInfo);
				}else{
					alert("删除成功");
					window.location.href="${pageContext.request.contextPath}/myJob/classify/job/${parent}";
				}
			}
		);
	}
}
</script>
<div class="col-md-6" >
	<c:if test="${parent!=-1}">
		<button type="button" class="btn btn-primary" style="float: right;margin-right:-400px" onclick="javascript:window.location.href='${pageContext.request.contextPath}/myJob/classify/job/-1'">返回上级</button>
	</c:if>
  	<button type="button" class="btn btn-primary" style="float: right;margin-right:-300px" onclick="javascript:window.location.href='${pageContext.request.contextPath}/myJob/classify/job/PreSave/${parent}'">添加</button>
</div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>序号</th>
		  	<th>分类名</th>
		  	<th>创建时间</th>
		  	<th>修改时间</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="menu" items="${menuList}" varStatus="status">
		  	<tr>
		  		<td >${status.index+1}</td>
		  		<td >${menu.showname}</td>   
		  		<td><fmt:formatDate value="${menu.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		  		<td><fmt:formatDate value="${menu.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>  
		  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/myJob/classify/job/PreSave/${parent}?id=${menu.uid}'">编辑</button>
		  			<button type="button" class="btn btn-danger btn-xs" onclick="myDelete(${menu.uid})">删除</button>
		  			<c:if test="${menu.parent==-1}">
		  				<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/myJob/classify/job/${menu.uid}'">二级分类</button>
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



