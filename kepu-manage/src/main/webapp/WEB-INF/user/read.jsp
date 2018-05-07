<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<script type="text/javascript">
	$(function () {
	    
	})
	function expExcel(){
		var param=""; 
		var t1 = $("#t1").val();
		var t2 = $("#t1").val();
		var title = $("#title").val();
		if(t1!=''&&t1!=null)
			param+="&t1="+t1;
		if(t2!=''&&t2!=null)
			param+="&t2="+t2;
		if(title!=''&&title!=null)
			param+="&title="+title;
		param=param.replace("&","?")
		var href="${pageContext.request.contextPath}/user/read/export"+param;
		window.open(href);
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/user/read/list" method="post">
	    <div class="input-group" style="width: 850px">
	          <div class="controls" style="float:left;margin-right:50px">
	            <label class="control-label" for="input01">时间</label>
	            <input id="t1" name="t1" value="${t1}" style="width:100px">
	            <label class="control-label" >至</label>
	            <input id="t2" name="t2"  value="${t2}" style="width:100px">
	          </div>
	          <input type="text" class="form-control" id="title" name="title" style="width:300px" value="${news.title }" placeholder="请输入要查询的标题...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		        <button class="btn btn-default" onclick="javascript:expExcel()"  style="margin-left:10px"><span class="glyphicon"></span>&nbsp;导出</button>
		      </span>
	    </div>
    </form>
  </div>
</div>
<div>
    <font color=red size=4 >查询结果(标题):   </font>${realTitle==null?"无数据":realTitle}
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>行政区</th>
	  	<th>阅读数</th>
	  </tr>
	  <c:forEach var="read" items="${list}" varStatus="status">
	  	<tr>
	  		<td>${read.name}</td>  
		  	<td>${read.num}</td> 	
	  	</tr>
	  </c:forEach>
	        <td>总计</td>  
		  	<td>${view==null?0:view}</td> 
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


