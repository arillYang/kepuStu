<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
</script>
	  	<div>
			<ol class="breadcrumb">
			  <li class="active">用户详情</li>
			</ol>
		</div>
<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		   <tr>
		   		<td colspan="4"><font color=""><strong>基本信息：</strong></font></td>
		   </tr>
		   <tr class="no-border">
            <td width="18%"><div align="right"><strong>昵称：</strong></div></td>
            <td width="34%">${stUser.nickname}</td>
            <td width="15%"><div align="right"><strong>手机号：</strong></div></td>
            <td><font color="red">${stUser.mobile}</font></td>
          </tr>
          <tr>
            <td><div align="right"><strong>注册时间：</strong></div></td>
            <td><fmt:formatDate value="${stUser.regtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><div align="right"><strong>性别：</strong></div></td>
            <td>${stUser.sex==1?"男":"女"}</td>
          </tr>
          <tr>
            <td><div align="right"><strong>职业：</strong></div></td>
            <td><span class="require-field">${careerList[stUser.career-1]}</span></td>
            <td><div align="right"><strong>学历：</strong></div></td>
            <td>${eduList[stUser.education-1]}</td>
          </tr>
          <tr>
            <td><div align="right"><strong>生日：</strong></div></td>
            <td><fmt:formatDate value="${stUser.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><div align="right"><strong>活动地：</strong></div></td>
            <td>${stUser.address}</td>
          </tr>
          <tr>
            <td><div align="right"><strong>行政区：</strong></div></td>
            <td>${addressList}</td>
            <td><div align="right"><strong>用户地址：</strong></div></td>
            <td><a href="${pageContext.request.contextPath}/address/addressList?UserId=${stUser.userid}">查看</a></td>
          </tr>
          <tr>
          </tr>
	</table>
	<br>
	<!-- 账户信息 -->
	<div>
		<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		   <tr >
		   		<td colspan="4"><font color=""><strong>账户信息:</strong></font></td>
		   </tr>
		   <tr class="no-border">
            <td width="18%"><div align="right"><strong>余额：</strong></div></td>
            <td width="34%">
            <font color = "red">
       <%--      ${stUserAccount.balance} --%>
       			￥1000
            </font></td>
            <td width="15%"><div align="right"><strong>积分：</strong></div></td>
            <td>
     <%--        	${stUserAccount.score} --%>
            	1000
            </td>
          </tr>
          <tr>
            <td><div align="right"><strong>积分详情：</strong></div></td>
            <td><a href="${pageContext.request.contextPath}/scoreDetail/scoreDetailList?BuyUserId=${stUser.userid}&TransactionCurrencyType=2&find=false">查看</a></td>
            <td><div align="right"><strong>余额详情：</strong></div></td>
            <td><a href="${pageContext.request.contextPath}/scoreDetail/scoreDetailList?BuyUserId=${stUser.userid}&TransactionCurrencyType=1&find=false">查看</a></td>
          </tr>
	</table>
	</div>
		<br>
			<div class="col-md-12 column" style="float:right;">
				 <button type="button" class="btn btn-lg btn-inverse"  style="float:right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/selectReturn'">返回</button>&nbsp;&nbsp;
			</div>
