<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function tuijian(id, isHome) {
		if (isHome == 1) {
			if (confirm("确定要取消推荐吗?")) {
				$
						.post(
								"${pageContext.request.contextPath}/community/setHome",
								{
									id : id
								},
								function(data) {
									if (data.result != 'success') {
										alert(data.result)
									} else {
										alert("设置成功");
										window.location.href = "${pageContext.request.contextPath}/community/list";
									}
								});
			}
		} else {
			if (confirm("确定要进行推荐吗?")) {
				$
						.post(
								"${pageContext.request.contextPath}/community/setHome",
								{
									id : id
								},
								function(data) {
									//var result=eval('('+result+')');

									if (data.result != 'success') {
										alert(result.errorInfo)
									} else {
										alert("设置成功");
										window.location.href = "${pageContext.request.contextPath}/community/list";
									}
								});
			}
		}
	}

	function SelectBuyUserPhone() {
		var BuyUserPhone = $("#BuyUserPhone").val();
		window.location.href = "${pageContext.request.contextPath}/scoreDetail/scoreDetailList?BuyUserPhone="
				+ BuyUserPhone;
	}
	function SelectTransactionType() {
		var TransactionType = $("#TransactionType").val();
		window.location.href = "${pageContext.request.contextPath}/scoreDetail/scoreDetailList?TransactionType="
				+ TransactionType;
	}
	function sc(){
		if(confirm("确认删除吗")){
		   alert("已删除");
		  }
		  else{
		   alert("数据未发生改变")
		   return;
		  }
		}
	
	/* 隐藏查询 */
	$(document).ready(function(){
		var find=${find};//style="display:none;
		if(find==false){
			$("#cx").attr("style","display:none;");
		}
        //加载HTML，不需等待，立即加载
});
</script>
积分详情列表页面
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
			<span class="input-group-btn" style="display: block; float: right;">
				<select class="form-control" style="width: 140px; float: left"
				name="TransactionType" id="TransactionType">
					<option value="1">收入</option>
					<option value="2">支出</option>
					<option value="" selected>交易类型</option>
			</select>
				<button class="btn btn-default" type="submit"
					onclick="SelectTransactionType()">
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
				<th>编号</th>
				<th>交易货币类型</th>
				<th>交易时间</th>
				<th>手机号</th>
				<th>交易类型</th>
				<th>金额</th>
				<th>操作</th>
			</tr>
			<c:forEach var="scoreDetail" items="${ScoreDetailList}"
				varStatus="status">
				<tr>
					<td>${scoreDetail.scoreDetailId}</td>
					<td><c:if test="${scoreDetail.transactionCurrencyType==1}">余额</c:if> <c:if
							test="${scoreDetail.transactionCurrencyType==2}">积分</c:if>
					<td><fmt:formatDate value="${scoreDetail.time}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${scoreDetail.buyUserPhone}</td>
					<td><c:if test="${scoreDetail.transactionType==1}">支出</c:if> <c:if
							test="${scoreDetail.transactionType==2}">收入</c:if></td>
							<td>${scoreDetail.scoreNum}</td>
							<td><a class="btn btn-info btn-xs"
					href="${pageContext.request.contextPath}/scoreDetail/findScoreDetailById?ScoreDetailId=${scoreDetail.scoreDetailId}">查看详情</a>
					 <a class="btn btn-info btn-xs"
					href="${pageContext.request.contextPath}/scoreDetail/PreUpdateScoreDetail?ScoreDetailId=${scoreDetail.scoreDetailId}">编辑</a> 
					<a class="btn btn-danger btn-xs"
					onclick="sc()" >删除</a>
				</td>
				</tr>
			</c:forEach>
		</table>
		<nav>
			<ul class="pagination">${pageCode }
			</ul>
		</nav>
	</div>