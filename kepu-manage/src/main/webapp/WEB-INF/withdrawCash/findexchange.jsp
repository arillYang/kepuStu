<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<script type="text/javascript">
function duihuan(){ 
     $.ajax({
             url: "${pageContext.request.contextPath}/withdrawCash/updatexchange" ,//url
             type: "POST",//方法类型
             dataType: "json",
             data: $('#form1').serialize(),
             success: function (result) {
                 console.log(result);//打印服务端返回的数据(调试用)
             },
             error : function() {
                 alert("异常！");
             }
         });
}

</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form id="form1" >
	  <div class="form-group">
	    <label class="col-sm-2 control-label">兑换余额：</label>
	    <div class="col-sm-10">
      <input type="text" class="form-control" id="green" name="green" value="${stexchang.exchange}" style="width: 200px">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10"><%-- 
	      <input type="hidden" id="id" name="id" value="${link.id }"/> --%>
	      <button type="button"  onclick="duihuan()" class="btn btn-primary">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)">返回</button>
	      <font color="red" id="error"></font>
	    </div>
	  </div>
	  </form>
  </div>
</div>
</body>
</html>