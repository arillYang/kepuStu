<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function voteChange(id,type){
		if(confirm("确定要更改这条记录吗?")){
			$.post("${pageContext.request.contextPath}/vote/delete",{id:id,type:type},
				function(data){
					if(data.result=='error'){
						alert(result.errorInfo)
					}else{
						alert("操作成功");
						window.location.href="${pageContext.request.contextPath}/vote/list";
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/vote/list" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="title"  value="${title}" placeholder="请输入要查询的标题...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/vote/preSave'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>标题</th>
		  	<th>投票人数</th>
		  	<th>访问量</th>
		  	<th>参与数</th>
		  	<th>状态</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="vote" items="${voteList}" varStatus="status">
		  	<tr>
		  		<td width="25%">${vote.title}</td>
		  		<td>${vote.votenum}</td>
		  		<td>${vote.viewnum}</td>
		  		<td>${vote.joinnum}</td>	
		  		<td>${staName[vote.state-1]}</td>	    
		  		<td>
		  			<input type="hidden" name="type" value="${type}"/>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/vote/preSave?id=${vote.uid}&type=${type}'">编辑</button>
		  			<button type="button" class="btn btn-info btn-xs" onclick="voteChange(${vote.uid},4)">关闭投票</button>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/vote/joinUser?id=${vote.uid}'">查看排名</button>
		  			<button type="button" class="btn btn-danger btn-xs" onclick="voteChange(${vote.uid},-1)">删除</button>
		  		</td>
		  	</tr>
		  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode}
		</ul>
	</nav>
</div>



