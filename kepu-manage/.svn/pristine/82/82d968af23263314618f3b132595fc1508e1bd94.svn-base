<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function newsDelete(id){
		if(confirm("确定要删除这条记录吗?")){
			$.post("${pageContext.request.contextPath}/notice/delete",{id:id},
				function(data){
					//var result=eval('('+result+')');
					
					if(data.result=='error'){
						alert(result.errorInfo)
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/notice/list";
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/notice/list" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="title"  value="${news.title }" placeholder="请输入要查询的标题...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/notice/preSave'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>公告标题</th>
		  	<th>公告作者</th>
		  	<th>发布时间</th>
		  	<th>评论数</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="news" items="${newsList}" varStatus="status">
		  	<tr>
		  		<td width="25%">${news.title}</td>
		  		<td>${news.newsauthor}</td>
		  		<td><fmt:formatDate value="${news.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		  		<td>${news.commentcount}</td>	    
		  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/notice/preSave?id=${news.uid}'">编辑</button>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/news/push?id=${news.uid}&type=3'">推送</button>
		  			<button type="button" class="btn btn-danger btn-xs" onclick="newsDelete(${news.uid})">删除</button>
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



