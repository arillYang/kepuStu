<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function deleteNotice(id){
	if(confirm("确定要删除这条记录吗?")){
		$.post("${pageContext.request.contextPath}/community/article/delete",{id:id},
			function(data){
				if(data.result=='error'){
					alert(result.errorInfo)
				}else{
					alert("删除成功");
					window.location.href="${pageContext.request.contextPath}/community/article/list/"+"${communityId}";
				}
			}
		);
	}
}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/community/article/list/${communityId}" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="content"  value="${article.content}" placeholder="请输入要查询的内容...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <c:if test="${communityId!=-1}">
	  <div class="col-md-6" >
	  	<button type="button" class="btn btn-primary"  onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/list'">返回社团</button>
	  </div>
  </c:if>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;table-layout:fixed">
	  	
			<tr>
		  	<th width="5%">序号</th>
		  	<th width="30%">内容</th>
		  	<th>作者</th>
		  	<th>发布时间</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="article" items="${articleList}" varStatus="status">
		  	<tr>
		  		<td >${status.index+1}</td>
		  		<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis">${article.content}</td>   
		  		<td >${article.username}</td>
		  		<td><fmt:formatDate value="${article.publishtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		  		<td>
		  			<button type="button" class="btn btn-danger btn-xs" onclick="deleteNotice(${article.uid})">
	  				删除
	  				</button>		  			
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



