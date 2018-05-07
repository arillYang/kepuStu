<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">

	function SelectBuyUserPhone() {
		var BuyUserPhone = $("#BuyUserPhone").val();
		window.location.href = "${pageContext.request.contextPath}/withdrawCash/withdrawCashList?BuyUserPhone="
				+ BuyUserPhone;
	}
	
	
</script>
积分兑换列表页面
<div class="row search">

	<div id="cx" class="col-md-6 clearfix" style="width: 70%;">
		
		<div class="input_box clearfix" style="width: 260px; float:;">
			<span class="input-group-btn clearfix"
				style="display: block; float: left; width: 240px;"> <input
				type="text" class="form-control" name="BuyUserPhone" id="BuyUserPhone"
				style="width: 150px; float: left;" placeholder="请输入手机号">
				<button class="btn btn-default" type="submit"
					onclick="SelectBuyUserPhone()"
					style="float: left; margin-left: 10px;">
					<span class="glyphicon glyphicon-search"></span>&nbsp;查询
				</button>
			</span> 
		</div>

	</div>
</div>
<div>


	<div>
		<table class="table table-hover  table-bordered table-striped"
			style="margin-bottom: 0px;">
			<tr>
				<th>购买人手机号</th>
				<th>消费积分</th>
				<th>兑换日期</th>
				<th>获得余额</th>
				<th>操作</th>
			</tr>
			<c:forEach var="withdrawCashList" items="${withdrawCashList}"
				varStatus="status">
				<tr>
					<td>${withdrawCashList.buyUserPhone} </td>
					<td>${withdrawCashList.consumeScore}</td>
					<td><fmt:formatDate value="${withdrawCashList.wcTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>--
					<fmt:formatNumber type="number" value="${withdrawCashList.beforeBalance - withdrawCashList.nowBalance}" pattern="#.00"/>-&nbsp--</td>
					<td>
					<a class="btn btn-info btn-xs" href="${pageContext.request.contextPath}/withdrawCash/findwithdrawCashBywcId?wcId=${withdrawCashList.wcId}&buyUserId=${withdrawCashList.buyUserId}">查看详情</a>
					<a class="btn btn-danger btn-xs"
					onclick="sc()" >删除</a></td> 
				</tr>
			</c:forEach>
		</table>
		<nav>
			<ul class="pagination">${pageCode }
			</ul>
		</nav>
	</div>