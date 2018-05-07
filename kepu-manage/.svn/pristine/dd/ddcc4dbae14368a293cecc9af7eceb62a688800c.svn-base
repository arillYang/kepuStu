<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	$(function () {
	})
	function expExcel(){
		var param=""; 
		var t1 = $("#t1").val();
		var t2 = $("#t2").val();
		if(t1!=''&&t1!=null)
			param+="&t1="+t1;
		if(t2!=''&&t2!=null)
			param+="&t2="+t2;
		param=param.replace("&","?")
		var href="${pageContext.request.contextPath}/user/author/export"+param;
		window.open(href);
	}
	function checkForm(){
		var t1=$("#t1").val();
		var t2=$("#t2").val();
		if(t1>t2){
			$("#t1").val();
			$("#t2").val();
			alert("时间区间有误,请重新选择");
			return false;
		}
		return true;
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/user/author/list" method="post">
	    <div class="input-group" style="width: 850px">
		       <div class="controls" style="float:left;margin-right:50px">
	            <label class="control-label" for="input01">时间</label>
	            <input id="t1" name="t1" value="${t1}" style="width:100px">
	            <label class="control-label" >至</label>
	            <input id="t2" name="t2"  value="${t2}" style="width:100px">
	          </div>
	          <input type="text" class="form-control" id="name" name="name" style="width:300px" value="${name}" placeholder="请输入要查询的作者...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>作者</th>
	  	<th>发表文章数</th>
	  	<th>点击量</th>
	  	<th>点赞数</th>
	  	<th>点赞率</th>
	  	<th>差评数</th>
	  	<th>差评率</th>
	  </tr>
	  <c:forEach var="statistic" items="${list}" varStatus="status">
	  	<tr>
	  		<td>${statistic.newsAuthor}</td>
	  		<td>${statistic.pm}</td>
	  		<td>${statistic.totalview}</td>
	  		<td>${statistic.totallike}</td>
	  		<td><fmt:formatNumber type="number" value="${statistic.totallike*1.0/statistic.pm}" pattern="0.00"/></td>
	  		<td>${statistic.totadisllike}</td>
	  		<td><fmt:formatNumber type="number" value="${statistic.totadisllike*1.0/statistic.pm}" pattern="0.00"/></td>
	  	</tr>
	  </c:forEach>
	  	<tr>
	  		<td>合计</td>
	  		<td>${t.pm}</td>
	  		<td>${t.totalview}</td>
	  		<td>${t.totallike}</td>
	  		<td><fmt:formatNumber type="number" value="${t.totallike*1.0/t.pm}" pattern="0.00"/></td>
	  		<td>${t.totadisllike}</td>
	  		<td><fmt:formatNumber type="number" value="${t.totadisllike*1.0/t.pm}" pattern="0.00"/></td>
	  	</tr>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode}
		</ul>
	</nav>
</div>
<script>
	;!function(){
		laydate({
		   elem: '#t1',
		   format: 'YYYY-MM-DD', 
		})
		laydate({
		   elem: '#t2',
		   format: 'YYYY-MM-DD', 
		})
	}();
</script>


