<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function typeDelete(id){
		if(confirm("确定要删除这条记录吗?")){
			$.post("${pageContext.request.contextPath}/news/type/delete",{id:id},
				function(data){
					//var result=eval('('+result+')');
					
					if(data.result=='error'){
						alert(data.errorInfo)
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/news/type/list";
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/news/type/list" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="classifyname"  value="${classify.classifyname}" placeholder="请输入要查询的标题...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/news/type/preSave'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>分类名</th>
		  	<th>是否固定</th>
		  	<th>创建时间</th>
		  	<th>修改时间</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="classify" items="${classifieList}" varStatus="status">
		  	<tr>
		  		<td width="25%">${classify.classifyname}</td>
		  		<td >${classify.fixed==1?"是":"否"}</td>
		  		<td><fmt:formatDate value="${classify.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		  		<td><fmt:formatDate value="${classify.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>    
		  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/news/type/preSave?id=${classify.uid}'">编辑</button>
		  			<button type="button" class="btn btn-danger btn-xs" onclick="typeDelete(${classify.uid})">删除</button>
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



