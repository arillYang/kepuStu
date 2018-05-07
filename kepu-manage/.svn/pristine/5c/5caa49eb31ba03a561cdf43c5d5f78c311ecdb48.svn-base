<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function newsDelete(id){
	if(confirm("确定要删除这条记录吗?")){
		$.post("${pageContext.request.contextPath}/product/linkDelete",{id:id},
			function(data){
			     if(data.result=='error'){
					alert(data.errorInfo);
				}else{
					alert("删除成功");
					window.location.href="${pageContext.request.contextPath}/product/link?outchainId="+${outchainId};
				}
			}
		);
	}
}
</script>
  <div class="col-md-12" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/smallClass'">返回</button>
    <button type="button" class="btn btn-primary" style="float: right;margin-right:10px" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/linkPreSave?outchainId=${outchainId}'">添加</button>
  </div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>序号</th>
		  	<th>名称</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="link" items="${linkList}" varStatus="status">
		  	<tr>
		  		<td >${status.index+1}</td>
		  		<td >${link.sitetitle}</td>    
		  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/linkPreSave?id=${link.uid}'">编辑</button>
		  			<button type="button" class="btn btn-danger btn-xs" onclick="newsDelete(${link.uid})">删除</button>
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



