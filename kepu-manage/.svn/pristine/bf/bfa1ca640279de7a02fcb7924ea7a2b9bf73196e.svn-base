<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function logout(){
		if(confirm('您确定要退出系统吗?')){
			window.location.href="${pageContext.request.contextPath}/user/logout";
		}
	}
</script>
<c:forEach var="menu" items="${initMenuList}" varStatus="status">
	<div class="list-group" style="width:70%">
		  <a href="#" class="list-group-item active">
		   ${menu.key} 
		  </a>
		    <c:forEach var="m" items="${menu.value}" varStatus="status2">
		  	  	<a href="${pageContext.request.contextPath}/${m.linkurl}" class="list-group-item">${m.menuname}</a>
		  	</c:forEach>
	</div>
 </c:forEach>