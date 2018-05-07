<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<script type="text/javascript">
	$(function () {
	    //默认绑定乡镇
	    TownBind();
	})
	function expExcel(){
		var param=""; 
		var town = $("#town").val();
		var t1=$("#t1").val();
		var t2=$("#t2").val();
		var pingming=$("#pingming").val();
		var mobile=$("#mobile").val();
		param+="&town="+town;
		param+="&t1="+t1;
		param+="&t2="+t2; 
		param+="&pingming="+t2; 
		param+="&mobile="+mobile; 
		param=param.replace("&","?")
		var href="${pageContext.request.contextPath}/activity/export"+param;
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
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/activity/list" method="post">
	    <div class="input-group" style="width: 850px">
		       <select class="form-control" style="width:120px;float:left" id="town" name="town">
		    		<c:if test="${town!=null}">
		    			<option value="${town}" selected>${townName}</option>
		    		</c:if>
		    		<c:if test="${town==null}">
		    			<option value="">请选择...</option>
		    		</c:if>
		    		
		    	</select>
		     <div class="controls" style="float:left;margin-right:50px;margin-left:50px">
		            <label class="control-label" for="input01">时间</label>
		            <input id="t1" name="t1" value="${t1}" style="width:100px">
		            <label class="control-label" >至</label>
		            <input id="t2" name="t2"  value="${t2}" style="width:100px">
	          </div>
	           <div class="controls" style="float:left;margin-right:50px;margin-left:50px">
	            <label class="control-label" for="input01">前N名</label>
	            <input type="text" id="paiming" name="paiming" value="${paiming}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"  style="width:50px;">
	          </div>
	          <div class="controls" style="float:left;margin-top:20px;margin-left:50px">
	          	<input type="text" id="mobile" class="form-control" name="mobile"  style="width:200px;" value="${mobile}" placeholder="请输入要查询的手机号...">
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
	  	<th>昵称</th>
	  	<th>手机号</th>
	  	<th>总分</th>
	  	<th>名次</th>
	  	<th>所属行政区</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="statistic" items="${list}" varStatus="status">
	  	<tr>
	  		<td>${statistic.showName}</td>
	  		<td>${statistic.mobile}</td>
	  		<td>${statistic.total}</td>
	  		<td>${statistic.rowNum}</td>
	  		<td>${addressList[status.index]}</td>
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/activity/detail?id=${statistic.userId}'">查看明细</button>
	  		</td>
	  	</tr>
	  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode} </font>
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


