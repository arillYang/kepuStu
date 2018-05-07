<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function tuijian(id,isHome ){
	if(isHome==1){
		if(confirm("确定要取消推荐吗?")){
			$.post("${pageContext.request.contextPath}/community/setHome",{id:id},
				function(data){
					if(data.result!='success'){
						alert(data.result)
					}else{
						alert("设置成功");
						window.location.href="${pageContext.request.contextPath}/community/list";
					}
				}
			);
		}
	}else{
		if(confirm("确定要进行推荐吗?")){
			$.post("${pageContext.request.contextPath}/community/setHome",{id:id},
				function(data){
					//var result=eval('('+result+')');
					
					if(data.result!='success'){
						alert(result.errorInfo)
					}else{
						alert("设置成功");
						window.location.href="${pageContext.request.contextPath}/community/list";
					}
				}
			);
		}
	}
}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/community/list" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="name"  value="${community.name }" placeholder="请输入要查询的名称...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/preSave'">添加社团</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  	
			<tr>
		  	<th>序号</th>
		  	<th>名称</th>
		  	<th>类型</th>
		  	<th>创建时间</th>
		  	<th>人数</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="community" items="${communityList}" varStatus="status">
		  	<tr>
		  		<td >${status.index+1}</td>
		  		<td >${community.name}</td>   
		  		<td >${community.type==1?"正规":"普通"}</td>   
		  		<td><fmt:formatDate value="${community.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		  		<th>${community.membernum}</th>
		  		<td>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/article/list/${community.uid}'">帖子</button>	
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/notice/list/${community.uid}'">公告</button>
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/teach/list/${community.uid}'">教学</button>		
		  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/preSave?id=${community.uid}'">编辑</button>	
		  			<button type="button" class="btn btn-info btn-xs" onclick="tuijian(${community.uid},${community.carousel})">${community.carousel==0?"推荐":"取推"}</button>	  		
		  			<%-- <button type="button" class="btn btn-danger btn-xs" onclick="deleteProduct(${community.uid},${community.state})">
	  				${community.state==0?"删除":"恢复"}
	  				</button>	 --%>
	  				<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/member/list/${community.uid}'">成员</button>
	  				<c:if test="${community.type==1}">
	  					<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/community/apply/list/${community.uid}'">${community.applynum}待审核</button>
	  				</c:if>
		  		</td>
		  	</tr>
		  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode }
		</ul>
	</nav>
</div>



