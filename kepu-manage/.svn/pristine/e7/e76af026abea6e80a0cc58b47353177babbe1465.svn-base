<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	function expExcel(){
		var param=""; 
		var t1=$("#t1").val();
		var t2=$("#t2").val();
		if(t1!=''&&t1!=null)
			param+="&t1="+t1;
		if(t2!=''&&t2!=null)
			param+="&t2="+t2;
		var c1=$("#click1").val();
		var c2=$("#click2").val();
		if(c1!=''&&c1!=null)
			param+="&c1="+c1;
		if(c2!=''&&c2!=null)
			param+="&c2="+c2;
		var town = $("#town").val();
		if(town!=''&&town!=null)
			param+="&town="+town;
		var village = $("#village").val();
		if(village!=''&&village!=null)
			param+="&village="+village;
		param=param.replace("&","?")
		var href="${pageContext.request.contextPath}/user/active/export"+param;
		window.open(href);
	}
	$(function () {
	    //默认绑定乡镇
	    TownBind();
	    //绑定事件
	    $("#town").change( function () {
	    	villageBind();
	    })
	})
	function TownBind() {
		var v=$("#town").val();
		if(v==null)
		$("#town").html("");
		var str = "<option value='-100'>==请选择===</option>";
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
	    //判断省份这个下拉框选中的值是否为空
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
		var c1=$("#click1").val();
		var c2=$("#click2").val();
		if(t1>t2){
			$("#t1").val();
			$("#t2").val();
			alert("时间区间有误,请重新选择");
			return false;
		}
		if(c1>c2){
			$("#c1").val();
			$("#c2").val();
			alert("点击量输入有误,请重新输入");
			return false;
		}
		return true;
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/user/active/list" method="post" onsubmit="return checkForm()">
	    <div class="input-group" style="width: 850px">
	          <div class="controls" style="float:left;margin-right:50px">
	            <label class="control-label" for="input01">时间</label>
	            <input id="t1" name="t1" value="${t1}" style="width:100px">
	            <label class="control-label" >至</label>
	            <input id="t2" name="t2"  value="${t2}" style="width:100px">
	          </div>
	          <div class="controls" >
	            <label class="control-label" for="input01">用户点击量</label>
	            <input type="text" name="click1" value="${click1}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"  style="width:50px;">
	            <label class="control-label" >至</label>
	            <input type="text" name="click2"  value="${click2}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"  style="width:50px">
	          </div>
	          <div class="controls" style="margin-top:20px">
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
		      </div>
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
	  	<th>行政名</th>
	  	<th>注册总数</th>
	  	<th>用户数</th>
	  	<th>活跃度(%)</th>
	  	<th>点击量</th>
	  </tr>
	  <c:forEach var="statistic" items="${list}" varStatus="status">
	  	<tr>
	  		<td>${statistic.name}</td>
	  		<td>${statistic.total}</td>
	  		<td>${statistic.people}</td>
	  		<td><fmt:formatNumber type="number" value="${statistic.total==0?'0':statistic.people*100.0/statistic.total}" pattern="0.00"/></td>
	  		<td>${statistic.view}</td>
	  	</tr>
	  </c:forEach>
	  	<tr>
	  		<td>合计</td>
	  		<td>${ativetotal}</td>
	  		<td>${ativepeople}</td>
	  		<td><fmt:formatNumber type="number" value="${ativetotal==0?'0':ativepeople*100.0/ativetotal}" pattern="0.00"/></td>
	  		<td>${ativeview}</td>
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


