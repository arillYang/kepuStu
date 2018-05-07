<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	setTimeout("location.href='${pageContext.request.contextPath}/order/selectReturn';", 3000); 
</script>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="alert alert-success alert-dismissable">
				 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				<h4>
					注意!
				</h4> <strong>订单取消成功!</strong>三秒后自动跳转！！ <a href="${pageContext.request.contextPath}/order/selectReturn" class="alert-link">点击跳转</a>
			</div>
		</div>
	</div>
</div>

