<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function SelectContactPhone() {
		var ContactPhone = $("#ContactPhone").val();
		window.location.href = "${pageContext.request.contextPath}/address/addressList?ContactPhone="
				+ ContactPhone;
	}
	function sc(){
		if(confirm("确认删除吗")){
		   alert("已删除");
		  }
		  else{
		   alert("数据未发生改变")
		   return;
		  }
		}
</script>
用户地址列表页面
<!-- <div class="row search">

	<div class="col-md-6 clearfix" style="width: 70%;">
		
		<div class="input_box clearfix" style="width: 260px; float:;">
			<span class="input-group-btn clearfix"
				style="display: block; float: left; width: 240px;"> <input
				type="text" class="form-control" name="ContactPhone" id="ContactPhone"
				style="width: 150px; float: left;" placeholder="请输入手机号">
				<button class="btn btn-default" type="submit"
					onclick="SelectContactPhone()"
					style="float: left; margin-left: 10px;">
					<span class="glyphicon glyphicon-search"></span>&nbsp;查询
				</button>
			</span> 
		</div>
	</div>
</div> -->
<div>


	<div>
		<table class="table table-hover  table-bordered table-striped"
			style="margin-bottom: 0px;">
			<tr>
				<th>联系人</th>
				<th>联系人电话</th>
				<th>省</th>
				<th>市</th>
				<th>区</th>
				<th>街道</th>
				<th>详细地址</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<c:forEach var="addressList" items="${addressList}"
				varStatus="status">
				<tr>
					<td>${addressList.contactName}</td>
					<td>${addressList.contactPhone}</td>
					<td>${addressList.province}</td>
					<td>${addressList.city}</td>
					<td>${addressList.street}</td>
					<td>${addressList.addressDetail}</td>
					<td>${addressList.status}</td>
			
					<td><fmt:formatDate value="${addressList.createTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td> 
					<%-- <a class="btn btn-info btn-xs" href="${pageContext.request.contextPath}/address/addressList?addressId=${addressList.addressId}">编辑</a>   --%>
					<a class="btn btn-danger btn-xs" onclick="sc()" >删除</a>
				</td>
				</tr>
			</c:forEach>
		</table>
		<nav>
			<ul class="pagination">${pageCode }
			</ul>
		</nav>
	</div>