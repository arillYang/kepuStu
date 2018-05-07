<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function deleteProduct(id,type){
		if(type==1){
			if(confirm("确定要上架该商品吗?")){
				$.post("${pageContext.request.contextPath}/product/delete",{id:id},
					function(data){
						if(data.result=='error'){
							alert(result.errorInfo);
						}else{
							alert("上架成功");
							window.location.href="${pageContext.request.contextPath}/product/list";
						}
					}
				);
			}
		}else{
			if(confirm("确定要下架该商品吗?")){
				$.post("${pageContext.request.contextPath}/product/delete",{id:id},
					function(data){
						/* var result=eval('('+result+')'); */
						if(data.result=='error'){
							alert(result.errorInfo);
						}else{
							alert("下架成功");
							window.location.href="${pageContext.request.contextPath}/product/list";
						}
					}
				);
			}
		}
	}
	function tuijian(id,isHome ){
		if(isHome==1){
			if(confirm("确定要取消推荐吗?")){
				$.post("${pageContext.request.contextPath}/product/setHome",{id:id},
					function(data){
						if(data.result!='success'){
							alert(data.result)
						}else{
							alert("设置成功");
							window.location.href="${pageContext.request.contextPath}/product/list";
						}
					}
				);
			}
		}else{
			if(confirm("确定要进行推荐吗?")){
				$.post("${pageContext.request.contextPath}/product/setHome",{id:id},
					function(data){
						//var result=eval('('+result+')');
						
						if(data.result!='success'){
							alert(result.errorInfo)
						}else{
							alert("设置成功");
							window.location.href="${pageContext.request.contextPath}/product/list";
						}
					}
				);
			}
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/product/list" method="post">
	    <div class="input-group" style="width: 450px">
		      <input type="text" class="form-control" name="title"  value="${product.title }" placeholder="请输入要查询的商品...">
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
	  	<th>序号</th>
	  	<th width="20%">标题</th>
	  	<th>类别</th>
	  	<th>上传时间</th>
	  	<th>联系人</th>
	  	<th>联系方式</th>
	  	<th>价格</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="product" items="${productList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1}</td>
	  		<td>${product.title }</td>
	  		<td>${product.classfyname}</td>
	  		<td><fmt:formatDate value="${product.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td>${product.username}</td>
	  		<td>${product.mobile}</td>
	  		<td>${product.money}</td> 
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/product/preSave?id=${product.uid}'">详情</button>
	  			<button type="button" class="btn btn-info btn-xs" onclick="tuijian(${product.uid},${product.carousel})">${product.carousel==0?"推荐":"取消推荐"}</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="deleteProduct(${product.uid},${product.state})">
	  			${product.state==0?"下架":"上架"}
	  			</button>
	  		</td>
	  	</tr>
	  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode}
		</ul>
	</nav>
</div>



