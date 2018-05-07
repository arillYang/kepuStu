<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function newsDelete(id){
		if(confirm("确定要删除这条记录吗?")){
			$.post("${pageContext.request.contextPath}/news/delete",{id:id},
				function(data){
					//var result=eval('('+result+')');
					
					if(data.result=='error'){
						alert(result.errorInfo)
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/news/list";
					}
				}
			);
		}
	}
	function cancel(id,type){
			if(confirm("确定要取消吗?")){
				$.post("${pageContext.request.contextPath}/news/cancel",{id:id,type:type},
					function(data){
						if(data.result!='success'){
							alert(data.result)
						}else{
							alert("取消成功");
							window.location.href="${pageContext.request.contextPath}/news/list${temp}";
						}
					}
				);
			}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/news/list" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="title"  value="${title}" placeholder="请输入要查询的标题...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/news/preSave'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>标题</th>
		  	<th>责任编辑</th>
		  	<th>作者</th>
		  	<th>编辑时间</th>
		  	<th>发表时间</th>
		  	<th>阅读数</th>
		  	<th>评论数</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="news" items="${newsList}" varStatus="status">
		  	<tr>
		  		<td width="25%">${news.title}</td>
		  		<td width="20%">${news.newsauthor}</td>
		  		<td >${news.username}</td>
		  		<td><fmt:formatDate value="${news.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		  		<td><fmt:formatDate value="${news.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		  		<td>${news.view}</td>	
		  		<td>${news.commentcount}</td>	    
		  		<td>
		  			<input type="hidden" name="type" value="${type}"/>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/news/preSave?id=${news.uid}&type=${type}'">编辑</button>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/news/push?id=${news.uid}&type=1'">推送</button>
		  			<c:if test="${news.carousel==1}">
		  				<button type="button" class="btn btn-info btn-xs" onclick="cancel(${news.uid},1)">取消轮播</button>
		  			</c:if>
		  			<c:if test="${news.stick==1}">
		  				<button type="button" class="btn btn-info btn-xs" onclick="cancel(${news.uid},2)">取消置顶</button>
		  			</c:if>
		  			<button type="button" class="btn btn-danger btn-xs" onclick="newsDelete(${news.uid})">删除</button>
		  		</td>
		  	</tr>
		  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode},<c:if test="${type==0}"><font color=red size=4 > 阅读数共${viewNum}</font></c:if>
		</ul>
	</nav>
</div>



