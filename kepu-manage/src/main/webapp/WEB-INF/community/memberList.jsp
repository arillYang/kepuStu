<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function tuijian(id,isHome ){
	if(isHome==1){
		if(confirm("确定要取消管理吗?")){
			$.post("${pageContext.request.contextPath}/community/member/setHome/${communityId}",{id:id},
				function(data){
					if(data.result!='success'){
						alert(data.result)
					}else{
						alert("设置成功");
						window.location.href="${pageContext.request.contextPath}/community/member/list/${communityId}";
					}
				}
			);
		}
	}else{
		if(confirm("确定要设为管理吗?")){
			$.post("${pageContext.request.contextPath}/community/member/setHome/${communityId}",{id:id},
				function(data){
					//var result=eval('('+result+')');
					
					if(data.result!='success'){
						alert(result.errorInfo)
					}else{
						alert("设置成功");
						window.location.href="${pageContext.request.contextPath}/community/member/list/${communityId}";
					}
				}
			);
		}
	}
}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/community/member/list/${communityId}" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="nickName"  value="${member.nickName }" placeholder="请输入要查询的用户名...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
   <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/list'">返回社团</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>序号</th>
		  	<th>昵称</th>
		  	<th>手机号</th>
		  	<th>类型</th>
		  	<th>加入时间</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="member" items="${MemberList}" varStatus="status">
		  	<tr>
		  		<td >${status.index+1}</td>
		  		<td >${member.nickName}</td>  
		  		<td >${member.mobile}</td> 
		  		<td >${member.type==1?"管理":"成员"}</td>  
		  		<td><fmt:formatDate value="${member.jointime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="tuijian(${member.userid},${member.type})">${member.type==1?"取消管理":"设为管理"}</button>		  			
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



