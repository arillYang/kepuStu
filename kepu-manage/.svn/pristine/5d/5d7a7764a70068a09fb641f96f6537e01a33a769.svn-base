<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<form action="${pageContext.request.contextPath}/order/updateCommit" id="afrom" method="post">
<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
<input type="hidden" name="orderId"  value ="${order.orderId}"/>
		   <tr>
		   		<td colspan="4"><font color=""><strong>基本信息：</strong></font></td>
		   </tr>
		   <tr class="no-border">
            <td width="18%"><div align="right"><strong>订单编号：</strong></div></td>
            <td width="34%">${order.orderId}</td>
            <td width="15%"><div align="right"><strong>订单状态：</strong></div></td>
                        <td><font color="green">
            	<select id ="select1" name = "orderStatu" onchange="show_sub(this.options[this.options.selectedIndex].value)" >
				  	<option value = "0" ${order.orderStatu eq 0 ? "selected" : ""}>待付款</option>
            		<option  value = "1" ${order.orderStatu eq 1 ? "selected" : ""}>待发货</option>
            		<option  value = "2" ${order.orderStatu eq 2 ? "selected" : ""}>已发货</option>
            		<option value = "3" ${order.orderStatu eq 3 ? "selected" : ""}>交易成功</option>
            		<option value = "4" ${order.orderStatu eq 4 ? "selected" : ""}>交易取消</option>
            	</select>
            </font></td>
          </tr>
          <tr>
          <!-- OrderInfo [orderId=2338333, sysAreaId=null, orderAddress=浙江杭州, orderPhone=13592695819, orderZipcode=466200, 
orderUser=发的说法发收发, orderType=null, orderStatu=4, orderSubjctId=null, orderPay=null, orderDes=null, orderDate=null,
 orderCode=null, productId=null, billPrice=null, billNum=null, orderLogisticsCode=null, orderLogisticsDate=null, 
 orderLogiscompanyCode=null, orderLogiscompanyPrice=null, sellUserId=null, buyUserId=null, payType=null, payEndTime=null, 
 endTime=null, orderNote=null, billTitle=null, billDesc=null, balance=null, credit=null, ratio=null, createTime=null, 
 isDelete=null]
 -->
            
            <td><div align="right"><strong>购买会员：</strong></div></td>
            <td>${BuyUserName}</td>
            <td><div align="right"><strong>下单时间：</strong></div></td>
            <td><fmt:formatDate value="${order.orderDate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>
          <tr>
            <td><div align="right"><strong>支付方式：</strong></div></td>
			<td><c:if test="${order.payType==1}">余额支付</c:if> <c:if
						test="${order.payType==2}">微信支付</c:if> <c:if
						test="${order.payType==3}">支付宝支付</c:if>
			</td>
            <td><div align="right"><strong>付款时间：</strong></div></td>
            <td><fmt:formatDate value="${order.payEndTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>
          <tr>
            <td><div align="right"><strong>商家姓名：</strong></div></td>
            <td> ${SellUser.nickname}</td>
            <td><div align="right"><strong>商家电话：</strong></div></td>
            <td>${SellUser.mobile}</td>
          </tr>
	</table>
	<br>
	<!-- 其他信息 -->
	<div>
		<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		   <tr >
		   		<td colspan="4"><font color=""><strong>其他信息：</strong></font></td>
		   </tr>
		   <tr class="no-border">
            <td width="18%"><div align="right"><strong>收货人姓名：</strong></div></td>
            <td width="34%"><input type="text" name = "orderUser" value ="${order.orderUser}"/></td>
            <td width="15%"><div align="right"><strong>收货人电话：</strong></div></td>
            <td><input type="text" name = "orderPhone" value ="${order.orderPhone}"/></td>
          </tr>
          <tr>
            <td><div align="right"><strong>地址：</strong></div></td>
            <td><input type="text" name = "orderAddress" value ="${order.orderAddress}"/></td>
            <td><div align="right"><strong>邮编：</strong></div></td>
            <td><input type="text" name = "orderZipcode" value ="${order.orderZipcode}"/></td>
          </tr>
	</table>
	</form>
	</div>
		<br>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		  <tr>
		  	<th>商品名称</th>
		  	<th>商品图片</th>
		  	<th>商品价格</th>
		  	<th>商品数量</th>
		  	<th>商品库存</th>
		  	<th>小计</th>
		  </tr>
		  <tr>
			  <td>可乐</td>
			   <td><img width="50px" height = "40px" src="${pageContext.request.contextPath}/img/kele.jpg"></td>
			    	<td>￥111</td>
				<td>2</td>
			  <td>3</td>
<%-- 			  <td>${order.billPrice}</td> --%>
              <td>￥222</td>

		  </tr>
	</table>
	<br>
	<div style="float:right;font-weight:bold;color:red;font-size:18px">
	<span>
			订单总金额:
	</span>
	<span>
		￥222.0
	</span>
	</div>
	<br><br>
	<div class="col-md-12 column" style="float:right;">
				 <button type="button" class="btn btn-lg btn-inverse"  style="float:right;" onclick="aaa()">返回</button>&nbsp;&nbsp;
				 <button type="button" class="btn btn-success btn-lg"  style="float:right;" onclick="aaa()">确认</button>
	</div>
<script type="text/javascript">
	var order= $("#orderId").val();
	var status2 ;
    function show_sub(v){     
    	status2 = v;
    }
	function aaa(){
		/* $.ajax({
            type: 'get',
            async:true,
            url: "${pageContext.request.contextPath}/order/updateStatus",
            data: {"orderId":order,"orderStatu":status2},
            success: function(data){
                if (200 == data){
                    alert("修改成功");
                    window.location.href="${pageContext.request.contextPath}/order/selectReturn";
                } else {
                    alert('修改失败!');
                    window.location.href="${pageContext.request.contextPath}/order/selectReturn";
                }
            }
        }) */
		document.getElementById("afrom").submit();
	}
	

</script>

