<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">

</script>
积分详情页面
<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		   <tr >
		   		<td colspan="4"><font color=""><strong>基本信息：</strong></font></td>
		   </tr>
		   <tr class="no-border">
            <td width="15%"><div align="right"><strong>积分兑换比例：</strong></div></td>
            <td id="scoreRatio"><font color="red">${scoreDetail.scoreRatio}</font></td>
            <td width="15%"><div align="right"><strong>交易货币类型</strong></div></td>
            <td id="transactionType"><font color="red"><c:if test="${scoreDetail.transactionType==1}">余额</c:if> <c:if
							test="${scoreDetail.transactionType==2}">积分</c:if></font></td>
          </tr>
          <tr>
            <td><div align="right"><strong>积分描述：</strong></div></td>
            <td id="scoreDetails">${scoreDetail.scoreDetails}</td>
            
            
            <c:if test="${scoreDetail.transactionType==1}"><td><div align="right"><strong>卖家编号：</strong></div></td>
            <td id="sellerId">${scoreDetail.sellerId}</td></c:if>
            
            
          </tr>
          <tr>
            <td><div align="right"><strong>获取人：</strong></div></td>
            <td id="buyUserId"><span class="require-field">${scoreDetail.buyUserId}</span></td>
            <td><div align="right"><strong>手机号：</strong></div></td>
            <td id="buyUserPhone">${scoreDetail.buyUserPhone}</td>
          </tr>
          <tr>
            <td><div align="right"><strong>积分是否过期：</strong></div></td>
            <td id="isOverdue"><c:if test="${scoreDetail.isOverdue==1}">已过期</c:if> <c:if
							test="${scoreDetail.isOverdue==0}">未过期</c:if></td>
            <td><div align="right"><strong>交易时间：</strong></div></td>
            <td id="time"><fmt:formatDate value="${scoreDetail.time}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>
          <tr>
            <td><div align="right"><strong>购买商品名称：</strong></div></td>
            <td id="commName">${scoreDetail.commName}</td>
            <td><div align="right"><strong>商品价格：</strong></div></td>
            <td id="commMoney">${scoreDetail.commMoney}</td>
          </tr>
          <tr>
            <td><div align="right"><strong>交易类型：</strong></div></td>
            <td id="transactionType"><c:if test="${scoreDetail.transactionType==1}">支出</c:if> <c:if
							test="${scoreDetail.transactionType==2}">收入</c:if></td>
           
           <c:if test="${scoreDetail.transactionType==1}"> <td><div align="right"><strong>获得积分数量：</strong></font></div></td>
            <td id="scoreNum"><font color="green" ><strong>${scoreDetail.scoreNum}</strong></font></td></c:if>
            <c:if test="${scoreDetail.transactionType==2}"> <td><div align="right"><strong>支出积分:</strong></div></td>
            <td>${scoreDetail.scoreNum}</td></c:if>
          </tr>
	</table>
	<br>
	
		<br>
	<%-- <table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		  <tr>
		  	<th>商品名称</th>
		  	<th>商品价格</th>
		  	<th>商品数量</th>
		  	<th>商品库存</th>
		  </tr>
		  <tr>
			  <td>可乐</td>
			    	<td>￥111</td>
			  	<td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
			
			  <td>1</td>
			  <td>300</td>
		  </tr>
	</table> --%>
	<br>
	<div style="float:right;font-weight:bold;color:red;font-size:18px">
	<c:if test="${scoreDetail.transactionType==1}"> <span>剩余余额: 100￥</span></c:if>
            <c:if test="${scoreDetail.transactionType==2}"> <span>剩余积分:200/个</span></c:if>
	</div>



