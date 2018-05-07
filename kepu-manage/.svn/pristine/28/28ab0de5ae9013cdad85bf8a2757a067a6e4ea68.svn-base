<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
 function SelectOrder(){
	 var order_id=$("#orderId").val();
	 window.location.href= "${pageContext.request.contextPath}/order/orderList?OrderId="+order_id;
}
function SelectOrderUser(){
	 var order_User=$("#order_user").val();
	 window.location.href= "${pageContext.request.contextPath}/order/orderList?orderUser="+order_User;
}
function SelectOrderStuats(){
	 var order_status=$("#order_status").val();
	 window.location.href= "${pageContext.request.contextPath}/order/orderList?orderStatu="+order_status;
}
</script>
订单列表页面
<div class="row search">

	<div class="col-md-6 clearfix" style="width:70%;">
		<div class="input-group" style="width: 340px;float:left">
			<span>
				<input type="text" id="orderId" class="form-control" style="width: 150px;"
					name="order_id" placeholder="请输入订单编号...">
					
				<button class="btn btn-default" type="button" onclick="SelectOrder()" style="float:left; margin-left:10px;">
					<span class="glyphicon glyphicon-search"></span>&nbsp;查询
				</button>
			</span>
		</div>
		<div class="input_box clearfix" style="width:260px; float:;">
	      <span class="input-group-btn clearfix" style="display:block;float:left;width:240px;"> 
		  	    <input type="text" class="form-control" name="order_user" id="order_user" style="width:150px;float:left;" placeholder="请输入收货人...">
		        <button class="btn btn-default" type="submit" onclick="SelectOrderUser()" style="float:left; margin-left:10px;"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		  </span>
	      <span class="input-group-btn" style="display:block;float:right;">
			    <select class="form-control" style="width:140px;float:left" name="order_status" id ="order_status">
			    	<option value="0" >未付款</option>
			    	<option value="1">待发货</option>
			    	<option value="2">已发货</option>
			    	<option value="3">交易成功</option> 
			     	<option value="4">交易取消</option>
			    	 <option value="" selected>交易状态</option>
			    </select>
			       <button class="btn btn-default" type="submit" onclick="SelectOrderStuats()"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		 </span>
		 </div>
		
	</div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped"
		style="margin-bottom: 0px;">

		<tr>
			<th>订单ID</th>
			<th>商品名称</th>
			<th>下单时间</th>
			<th>收货人</th>
			<th>商家姓名</th>
			<th>总金额</th>
			<th>支付方式</th>
			<th>订单状态</th>
			<th>操作</th>
		</tr>
		<c:forEach var="order" items="${orderInfoList}" varStatus="status">
			<tr>
				<td>${order.orderId}</td>
				<td>可乐</td>
				<td><fmt:formatDate value="${order.orderDate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${order.orderUser}</td>
					<td>张三</td>
				<td>${order.billPrice}</td>
				<td><c:if test="${order.payType==1}">普通支付</c:if> <c:if
						test="${order.payType==2}">微信支付</c:if> <c:if
						test="${order.payType==3}">支付宝</c:if></td>
				<td><c:if test="${order.orderStatu==0}">待付款</c:if> <c:if
						test="${order.orderStatu==1}">待发货</c:if>
						<c:if test="${order.orderStatu==2}">已发货</c:if> 
						<c:if test="${order.orderStatu==3}">交易成功</c:if> 
						<c:if test="${order.orderStatu==4}">交易取消</c:if> 
				</td>
				<td><a class="btn btn-info btn-xs"
					href="${pageContext.request.contextPath}/order/findOrderById?order_id=${order.orderId}">查看详情</a>
					<a class="btn btn-info btn-xs"
					href="${pageContext.request.contextPath}/order/UpdateOrder?order_id=${order.orderId}">编辑</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<nav>
		<ul class="pagination">${pageCode }
		</ul>
	</nav>
</div>



