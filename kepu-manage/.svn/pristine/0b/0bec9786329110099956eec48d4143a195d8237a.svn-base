<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<script type="text/javascript">
	$(function () {
	    TownBind();
	    $("#town").change( function () {
	    	villageBind();
	    })
	})
	function expExcel(){
		var param=""; 
		var t1 = $("#t1").val();
		var t2 = $("#t2").val();
		if(t1!=''&&t1!=null)
			param+="&t1="+t1;
		if(t2!=''&&t2!=null)
			param+="&t2="+t2;
		var town = $("#town").val();
			param+="&town="+town;
		var village = $("#village").val();
		if(village!=''&&village!=null)
			param+="&village="+village;
		param=param.replace("&","?")
		var href="${pageContext.request.contextPath}/user/click/export"+param;
		window.open(href);
	}
	function TownBind() {
		var v=$("#town").val();
		if(v==null)
		$("#town").html("");
		var str = "<option value='-100'>==仙居县===</option>";
	    $.ajax({
	        type: "POST",
	        url: "${pageContext.request.contextPath}"+'/user/getAddress',
	        data: { "parentId":"-1"},
	        dataType: "JSON",
	        async: false,
	        success: function (data) {
	            $.each(data, function (i, item) {
	                str += "<option value=" + item.id + ">" + item.name + "</option>";
	            })
	            $("#town").append(str);
	        },
	        error: function () { alert("Error"); }
	    });
	}
	function villageBind() {
		var town = $("#town").val();
	    if (town == ""||town==null) {
	        return;
	    }
	    	$("#village").html("");
	    var str = "<option value='-100'>==请选择===</option>";
		$.ajax({
	        type: "POST",
	        url:"${pageContext.request.contextPath}"+'/user/getAddress',
	        data: { "parentId": town},
	        dataType: "JSON",
	        async: false,
	        success: function (data) {
	            $.each(data, function (i, item) {
	                str += "<option value=" + item.id + ">" + item.name + "</option>";
	            })
	            $("#village").append(str);
	        },
	        error: function () { alert("Error"); }
	    });
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
	<form action="${pageContext.request.contextPath}/user/click/list" method="post">
	    <div class="input-group" style="width: 850px">
		      <%-- <input type="text" class="form-control" name="nickname"  value="${user.nickname }" placeholder="请输入要查询的用户名..."> --%>
		       <div class="controls" style="float:left;margin-right:50px">
	            <label class="control-label" for="input01">时间</label>
	            <input id="t1" name="t1" value="${t1}" style="width:100px">
	            <label class="control-label" >至</label>
	            <input id="t2" name="t2"  value="${t2}" style="width:100px">
	          </div>
		       <select class="form-control" style="width:120px;float:left" id="town" name="town">
		    		<c:if test="${town!=null}">
		    			<option value="${town}" selected>${townName}</option>
		    		</c:if>
		    		<c:if test="${town==null}">
		    			<option value="">请选择...</option>
		    		</c:if>
		    	</select>
		    	 <select class="form-control" style="width:120px;float:left;margin-left:10px" id="village" name="village">
			    		<c:if test="${village!=null}">
			    			<option value="${village}" selected>${villageName}</option>
			    		</c:if>
			    		<c:if test="${village==null}">
			    			<option value="">请选择...</option>
			    		</c:if>
			      </select>
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		        <button class="btn btn-default" onclick="javascript:expExcel()"  style="margin-left:10px"><span class="glyphicon"></span>&nbsp;导出</button>
		      </span>
	    </div>
    </form>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>行政区</th>
	  	<th>总点击量</th>
	  	<th>首页点击</th>
	  	<th>首页占比</th>
	  	<th>乡镇点击</th>
	  	<th>乡镇占比</th>
	  	<th>服务点击</th>
	  	<th>服务占比</th>
	  	<th>社团点击</th>
	  	<th>社团占比</th>
	  </tr>
	  <c:forEach var="statistic" items="${list}" varStatus="status">
	  	<tr>
	  		<td>${statistic.name}</td>
	  		<td>${statistic.total}</td>
	  		<td>${statistic.type1}</td>
	  		<td>${statistic.p1}</td>
	  		<td>${statistic.type2}</td>
	  		<td>${statistic.p2}</td>
	  		<td>${statistic.type3}</td>
	  		<td>${statistic.p3}</td>
	  		<td>${statistic.type4}</td>
	  		<td>${statistic.p4}</td>
	  	</tr>
	  </c:forEach>
	  	<tr>
	  		<td>合计</td>
	  		<td>${stotal}</td>
	  		<td>${stype1}</td>
	  		<td><fmt:formatNumber type="number" value="${stotal==0?'0':stype1*1.0/stotal}" pattern="0.00"/></td>
	  		<td>${stype2}</td>
	  		<td><fmt:formatNumber type="number" value="${stotal==0?'0':stype2*1.0/stotal}" pattern="0.00"/></td>
	  		<td>${stype3}</td>
	  		<td><fmt:formatNumber type="number" value="${stotal==0?'0':stype3*1.0/stotal}" pattern="0.00"/></td>
	  		<td>${stype4}</td>
	  		<td><fmt:formatNumber type="number" value="${stotal==0?'0':stype4*1.0/stotal}" pattern="0.00"/></td>
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


