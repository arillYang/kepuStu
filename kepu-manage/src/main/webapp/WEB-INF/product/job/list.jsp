<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function deletejob(id){
		if(confirm("确定要删除该项吗?")){
			$.post("${pageContext.request.contextPath}/myJob/jobDelete",{id:id},
				function(data){
					if(data.result=='error'){
						alert(result.errorInfo);
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/myJob/jobList";
					}
				}
			);
		}
	}
	function tuijian(id,isHome ){
		if(isHome==1){
			if(confirm("确定要取消推荐吗?")){
				$.post("${pageContext.request.contextPath}/myJob/jobSetHome",{id:id},
					function(data){
						if(data.result!='success'){
							alert(data.result)
						}else{
							alert("设置成功");
							window.location.href="${pageContext.request.contextPath}/myJob/jobList";
						}
					}
				);
			}
		}else{
			if(confirm("确定要进行推荐吗?")){
				$.post("${pageContext.request.contextPath}/myJob/jobSetHome",{id:id},
					function(data){
						if(data.result!='success'){
							alert(result.errorInfo)
						}else{
							alert("设置成功");
							window.location.href="${pageContext.request.contextPath}/myJob/jobList";
						}
					}
				);
			}
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/myJob/jobList" method="post">
	    <div class="input-group" style="width: 450px">
		      <input type="text" class="form-control" name="jobname"  value="${job.jobname }" placeholder="请输入要查询的职位...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
			</span>
	    </div>
    </form>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>序号</th>
	  	<th width="20%">职位</th>
	  	<th>上传时间</th>
	  	<th>发布人</th>
	  	<th>状态</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="job" items="${jobList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1}</td>
	  		<td>${job.jobname }</td>
	  		<td><fmt:formatDate value="${job.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td>${job.realname}</td> 
	  		<td>${job.state==0?"正常":(job.state==2?"待审核":"作废")}</td> 
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/myJob/jobPreSave?id=${job.uid}'">详情</button>
	  			<button type="button" class="btn btn-info btn-xs" onclick="tuijian(${job.uid},${carousel[status.index]})">${carousel[status.index]==0?"推荐":"取消推荐"}</button>
	  			 <c:if test="${job.state!=3}">
		  			<button type="button" class="btn btn-danger btn-xs" onclick="deletejob(${job.uid})">
		  			删除
		  			</button>
		  		</c:if>
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



