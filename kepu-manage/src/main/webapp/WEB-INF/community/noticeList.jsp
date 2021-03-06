<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function deleteNotice(id){
	if(confirm("确定要删除这条记录吗?")){
		$.post("${pageContext.request.contextPath}/community/notice/delete",{id:id},
			function(data){
				if(data.result=='error'){
					alert(result.errorInfo)
				}else{
					alert("删除成功");
					window.location.href="${pageContext.request.contextPath}/community/notice/list/"+"${communityId}";
				}
			}
		);
	}
}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/community/notice/list/${communityId}" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="title"  value="${notice.title}" placeholder="请输入要查询的标题...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary"  onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/notice/preSave/${communityId}'">添加</button>
  	<button type="button" class="btn btn-primary"  onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/list'">返回社团</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>序号</th>
		  	<th>标题</th>
		  	<th>作者</th>
		  	<th>发布时间</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="notice" items="${noticeList}" varStatus="status">
		  	<tr>
		  		<td >${status.index+1}</td>
		  		<td >${notice.title}</td>   
		  		<td >${notice.auchor}</td>
		  		<td><fmt:formatDate value="${notice.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/notice/preSave/${communityId}?id=${notice.uid}'">编辑</button>	
		  			<button type="button" class="btn btn-danger btn-xs" onclick="deleteNotice(${notice.uid})">
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



