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
		var t1 = $("#t1").val();
		var t2 = $("#t1").val();
		if(t1!=''&&t1!=null)
			param+="&t1="+t1;
		if(t2!=''&&t2!=null)
			param+="&t2="+t2;
		if(town!=''&&town!=null)
			param+="&town="+town;
		param=param.replace("&","?")
		var href="${pageContext.request.contextPath}/user/detail/export"+param;
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
	<form action="${pageContext.request.contextPath}/user/detail/list" method="post">
	    <div class="input-group" style="width: 850px">
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
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		        <button class="btn btn-default" onclick="javascript:expExcel()"  style="margin-left:10px"><span class="glyphicon"></span>&nbsp;导出</button>
		      </span>
	    </div>
    </form>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="width:1000px;margin-bottom: 0px;">
	  <tr>
	  	<th>行政区</th>
	  	<th>首页点击量</th>
	  	<c:forEach var="className" items="${classNamelist}" varStatus="status">
		  	<th>
		  		${className.classifyname}
		  	</th>
	  	</c:forEach>
	  </tr>
	  <c:forEach  var="v" items="${data}" varStatus="status">
		  	<tr>
		  		<td>${villageList[status.index].name}</td>
		  		<c:forEach var="l" items="${v}" varStatus="lie"> 
		  			<td>${data[status.index][lie.index]==null?"0.00":data[status.index][lie.index]}</td>
		  		</c:forEach>
		  	</tr>
	  </c:forEach>
	        <tr>
	        	<td>合计</td>
	        	    <td>${detailTotal}</td>
	  			<c:forEach var="spdata" items="${sp}" varStatus="to"> 
		  			<td><fmt:formatNumber type="number" value="${detailTotal==0?'0':spdata*1.0/detailTotal}" pattern="0.00"/></td>
		  		</c:forEach>
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


