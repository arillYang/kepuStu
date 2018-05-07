<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function linkDelete(id){
		if(confirm("确定要删除这条记录吗?")){
			$.post("${pageContext.request.contextPath}/news/link/delete",{id:id},
				function(data){
					if(data.result=='error'){
						alert(result.errorInfo)
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/news/link/list";
					}
				}
			);
		}
	}
	function tuijian(id,isHome){
		if(isHome==1){
			if(confirm("确定要取消推荐吗?")){
				$.post("${pageContext.request.contextPath}/news/link/setHome",{id:id,value:isHome},
					function(data){
					if(data.result!='success'){
							alert(data.result)
						}else{
							alert("设置成功");
							window.location.href="${pageContext.request.contextPath}/news/link/list";
						}
					}
				);
			}
		}else{
			if(confirm("确定要进行推荐吗?")){
				$.post("${pageContext.request.contextPath}/news/link/setHome",{id:id,value:isHome},
					function(data){
						if(data.result!='success'){
							alert(result.errorInfo)
						}else{
							alert("设置成功");
							window.location.href="${pageContext.request.contextPath}/news/link/list";
						}
					}
				);
			}
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float:right;margin-right:-400px" onclick="javascript:window.location.href='${pageContext.request.contextPath}/news/link/preSave'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>序号</th>
	  	<th>外链</th>
	  	<th>类型</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="stlink" items="${linkList}" varStatus="status">
	  	<tr>
	  		<td>${status.index+1}</td>
	  		<td>${stlink.link}</td>
	  		<td>${types[stlink.type-1]}</td>
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/news/link/preSave?id=${stlink.id}'">编辑</button>
	  			<button type="button" class="btn btn-info btn-xs" onclick="tuijian(${stlink.id},${stlink.isHome})">${stlink.isHome==0?"推荐":"取消推荐"}</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="linkDelete(${stlink.id})">删除</button>
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



