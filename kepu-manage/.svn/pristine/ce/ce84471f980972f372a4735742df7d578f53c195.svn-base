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
            <td width="15%"><div align="right"><strong>购买人编号：</strong></div></td>
            <td id="buyUserId"><font color="red">${withdrawCash.buyUserId}</font></td>
            <td width="15%"><div align="right"><strong>购买人手机号:</strong></div></td>
             <td id="buyUserPhone">${withdrawCash.buyUserPhone}</td>
          </tr>
          <tr>
            <td><div align="right"><strong>消费积分：</strong></div></td>
            <td id="consumeScore">${withdrawCash.consumeScore}</td>
            <td><div align="right"><strong>兑换描述：</strong></div></td>
            <td id="wcDesc"><font color="red">${withdrawCash.wcDesc}</font></td>
          </tr>
          <tr>
            <td><div align="right"><strong>当前积分：</strong></div></td>
            <td id="nowScore"><span class="require-field">${withdrawCash.nowScore}</span></td>
            <td><div align="right"><strong>兑换后余额：</strong></div></td>
            <td id="nowBalance">${withdrawCash.nowBalance}</td>
          </tr>
          <tr>
            <td><div align="right"><strong>兑换前积分：</strong></div></td>
            <td id="beforeScore">${withdrawCash.beforeScore}</td>
            <td><div align="right"><strong>交易时间：</strong></div></td>
            <td id="wcTime"><fmt:formatDate value="${withdrawCash.wcTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>
	</table>



