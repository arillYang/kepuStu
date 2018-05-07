<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

用户地址更新页面
<form action="${pageContext.request.contextPath}/address/updateCommit" method="post" >
	<table class="table table-hover  table-bordered table-striped"
		style="margin-bottom: 0px;">
		<input id="addressId" name="addressId" type="hidden"
				value="${address.addressId}" />
		<tr>
			<td colspan="4"><font color=""><strong>基本信息：</strong></font></td>
		</tr>
		<tr class="no-border">
			<td width="15%"><div align="right">
					<strong>联系人：</strong>
				</div></td>
			<td><input id="scoreRatio" name="scoreRatio" type="text"
				value="${address.contactName}" /></td>
		</tr>
		<tr>
			<td><div align="right">
					<strong>积分描述：</strong>
				</div></td>
			<td><input id="scoreDetails" name="scoreDetails" type="text"
				value="${scoreDetail.scoreDetails}"></td>
			<td><div align="right">
					<strong>卖家编号：</strong>
				</div></td>
			<td><input type="text" id="sellerId" name="sellerId"
				value="${scoreDetail.sellerId}"></td>
		</tr>
		<tr>
			<td><div align="right">
					<strong>获取人：</strong>
				</div></td>
			<td>${BuyUserName}"</td>
			<td><div align="right">
					<strong>手机号：</strong>
				</div></td>
			<td><input id="buyUserPhone" type="text"  name="buyUserPhone"
				value="${scoreDetail.buyUserPhone}"></td>
		</tr>
		<tr>
			<td><div align="right">
					<strong>积分是否过期：</strong>
				</div></td>
			<td><input onclick="return false;" type="radio"
				name="radiobutton" value="1"> 已过期 <input
				onclick="return false;" type="radio" name="radiobutton" value="0"
				checked> 未过期</td>
			<td><div align="right">
					<strong>交易时间：</strong>
				</div></td>
			<td><fmt:formatDate value="${scoreDetail.time}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td><div align="right">
					<strong>购买商品名称：</strong>
				</div></td>
			<td><input id="commName" type="text" name="commName"
				value="${scoreDetail.commName}"></td>
			<td><div align="right">
					<strong>商品价格：</strong>
				</div></td>
			<td><input id="commMoney" type="text" name="commMoney"
				value="${scoreDetail.commMoney}"></td>
		</tr>
		<tr>
			<td><div align="right">
					<strong>交易类型：</strong>
				</div></td>
			<td><c:if test="${scoreDetail.transactionType==1}">支出</c:if> <c:if
					test="${scoreDetail.transactionType==2}">收入</c:if></td>
			<td><div align="right">
					<strong>交易数量：</strong>
				</div></td>
			<td><input id="scoreNum" type="text" name="scoreNum"
				value="${scoreDetail.scoreNum}"></td>
		</tr>
		<div class="col-md-12 column" style="float: right;">
	<button type="button" class="btn btn-lg btn-inverse"
		style="float: right;" onClick="javascript :history.back(-1);">返回</button>
	&nbsp;&nbsp;
		 <button type="submit" class="btn btn-success btn-lg"
		style="float: right;">保存</button>&nbsp;&nbsp;
</div>
	</table>
</form>
<br>
<br>
<table class="table table-hover  table-bordered table-striped"
	style="margin-bottom: 0px;">
	<tr>
		<th>商品名称</th>
		<th>商品价格</th>
		<th>商品数量</th>
		<th>商品库存</th>
		<th>小计</th>
	</tr>
	<tr>
		<td>可乐</td>
		<td>￥111</td>
		<%-- 			  	<td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>  --%>
		<td>2</td>
		<td>3</td>
		<td>￥222</td>
	</tr>
</table>
<br>
<div
	style="float: right; font-weight: bold; color: red; font-size: 18px">
	<span> 订单总金额: </span> <span> ￥5563.0 </span>

</div>
<br>
<br>

<script type="text/javascript">
	function update() {
		var scoreRatio = $("#scoreRatio").val();
		var scoreDetails = $("#scoreDetails").val();
		var sellerId = $("#sellerId").val();
		var buyUserId = $("#buyUserId").val();
		var buyUserPhone = $("#buyUserPhone").val();
		var commName = $("#commName").val();
		var commMoney = $("#commMoney").val();
		var scoreNum = $("#scoreNum").val();
		$
				.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/order/updateStatus",
					data : {
						"orderId" : order,
						"orderStatu" : status
					},
					success : function(data) {
						if ('200' == data) {
							alert("修改成功");
							window.location.href = "${pageContext.request.contextPath}/order/selectReturn";
						} else {
							alert('修改失败!');
							window.location.href = "${pageContext.request.contextPath}/order/selectReturn";
						}
					}
				})
	}
</script>

