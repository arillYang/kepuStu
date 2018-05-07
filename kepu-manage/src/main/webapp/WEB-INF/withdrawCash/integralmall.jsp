<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<script type="text/javascript">
	function newsDelete(id){
		if(confirm("确定要删除这条记录吗?")){
			$.post("${pageContext.request.contextPath}/withdrawCash/integralDelete",{id:id},
				function(data){
					//var result=eval('('+result+')');
					
					if(data.result=='error'){
						alert(result.errorInfo)
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/withdrawCash/integralmall";
					}
				}
			);
		}
	}
	function cancel(id,type){
			if(confirm("确定要取消吗?")){
				$.post("${pageContext.request.contextPath}/news/cancel",{id:id,type:type},
					function(data){
						if(data.result!='success'){
							alert(data.result)
						}else{
							alert("取消成功");
							window.location.href="${pageContext.request.contextPath}/news/list${temp}";
						}
					}
				);
			}
	}
</script>


<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>


<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/withdrawCash/findintegralmall" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="name"   placeholder="请输入要查询的商品名字...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/withdrawCash/preSave'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>商品名字</th>
		  	<th>商品标题</th>
		  	<th>图片</th>
		  	<th>邮费</th>
		  	<th>价格</th>
		  	<th>绿币</th>
		  	<th>数量</th>
		  	<th>创建时间</th>
		  	<th>操作</th>
		  </tr>
		   <c:forEach items="${list }" var="news"> 
		  	<tr>
		  		<td width="13%">${news.commodityname}</td>
		  		 <td width="10%">${news.title}</td>
		  		<td width="10%"  >
		  		  <img height="25px"  alt="" src="${news.displaythepicture}">  
		  		</td>
		  	<%-- 	<td><fmt:formatDate value="${news.displaythepicture}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
		  		<td width="10%"><c:if test="${news.freight==0}">
		  		包邮
		  		</c:if> <c:if test="${news.freight!=0}">
		  		${news.freight}元
		  		</c:if></td>
		  		
		  		<td width="12%">${news.money}元</td>
		  	<%-- 	<td><fmt:formatDate value="${news.money}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
		  		<td width="10%">${news.integral}</td>	
		  		<td width="10%">${news.numbe}</td>	
		  		<td width="13%"><fmt:formatDate value="${news.createtime}" pattern="yyyy-MM-dd"/></td><%-- 
		  			<td><fmt:formatDate value=" ${news.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>  --%>
		  		<td width="15%">
		  		<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/withdrawCash/updatepreSave?id=${news.id }'">编辑</button>
		  		<button type="button" class="btn btn-danger btn-xs" onclick="newsDelete(${news.id})">删除</button>
		  			</td>
		  	</tr>
		  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode}<c:if test="${type==0}"><font color=red size=4 > 阅读数共${viewNum}</font></c:if>
		</ul>
	</nav>
</div>



