<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	$(function () {
	    //默认绑定乡镇
	    TownBind();
	})
	function expExcel(){
		var param=""; 
		var town = $("#town").val();
		param+="&town="+town;
		param=param.replace("&","?")
		var href="${pageContext.request.contextPath}/user/statistic/export"+param;
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
	<form action="${pageContext.request.contextPath}/user/statistic/list" method="post">
	    <div class="input-group" style="width: 450px">
		      <%-- <input type="text" class="form-control" name="nickname"  value="${user.nickname }" placeholder="请输入要查询的用户名..."> --%>
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
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>行政区名</th>
	  	<th>用户注册数</th>
	  </tr>
	  <c:forEach var="statistic" items="${list}" varStatus="status">
	  	<tr>
	  		<td>${statistic.name}</td>
	  		<td>${statistic.number}</td>
	  	</tr>
	  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode} <font color=red size=4 >合计数:${count}人</font>
		</ul>
	</nav>
</div>



