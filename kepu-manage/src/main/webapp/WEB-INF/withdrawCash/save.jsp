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
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>



<script type="text/javascript">


var editor2;
KindEditor.ready(function(K) {
	editor2 = K.create('textarea[name="realcontent"]', {
		resizeType : 1,
		allowPreviewEmoticons : true,
		allowImageUpload : true,
		allowFileManager : true,
		items : [
			'image', 'preview','undo','redo','fullscreen','media'],
	    uploadJson : "${pageContext.request.contextPath}"+'/news/upload',
	    afterBlur: function () { 
	    	//alert(editor2.text());
	    	$("#myContent").val(editor2.text()); //this.sync();   获取文本值
	    	$("#tempContent").val(editor2.html()); 
	    	
	    	},//数据同步
	});
});

KindEditor.ready(function(K) {
	var editor = K.editor({
		allowFileManager : true,
		allowImageUpload : true,//运行上传图片  
		uploadJson : "${pageContext.request.contextPath}"+'/news/upload' //  服务端上传图片处理URI
	});
	K('#J_selectImage').click(function() {
		editor.loadPlugin('multiimage', function() {
			editor.plugin.multiImageDialog({
				clickFn : function(urlList) {
					var div = K('#J_imageView_hot');
					div.html('');
					K.each(urlList, function(i, data) {
						div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
						$("#launchPage").val(data.url);
						$("#pic_hot").attr("href",data.url); 
					});
					editor.hideDialog();
				}
			});
		});
	});
	K('#guide_pics').click(function() {
		editor.loadPlugin('multiimage', function() {
			editor.plugin.multiImageDialog({
				clickFn : function(urlList) {
					var imgArray = [];
					var div = K('#J_imageView_guides');
					div.html('');
					K.each(urlList, function(i, data) {
						imgArray.push(data.url);
						div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
					});
					$("#guides").val(imgArray.join(","));
					editor.hideDialog();
				}
			});
		});
	});
	K('#about_us_pics').click(function() {
		editor.loadPlugin('multiimage', function() {
			editor.plugin.multiImageDialog({
				clickFn : function(urlList) {
					var imgArray = [];
					var div = K('#J_imageView_about_us');
					div.html('');
					K.each(urlList, function(i, data) {
						imgArray.push(data.url);
						div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
					});
					$("#about_us").val(imgArray.join(","));
					editor.hideDialog();
				}
			});
		});
	});
});

function checkFormjk(){
	var roleid=$("#freight").val();
	if(roleid==70){
			$("#qita").html(' <input type="text" class="form-control" id="qita1" name="qita"  style="width: 200px">');
			return false;
		}
	else{
		$("#qita").html("");
		return false;
	}
}

function checkForm(){
	
	var id=${tUser.id==null?-1:tUser.id};
	var roleid=$("#commodityname").val();
	if(roleid==""){
			$("#commodityname12").html("请填写名字！");
			return false;
		}
	
	var g=$("#qita1").val();
	
	if(g!=null){
 	if(isNaN(g))
		{
			$("#qita").html("请填写正确邮费！");
			return false;
		} 
	}
		
	var freight=$("#freight").val();
	
	if(freight==1){
			$("#qita").html("请填写邮费！");
			return false;
		}
	
		var numbe=$("#numbe").val();
		
		if(isNaN(numbe)){
				$("#numbe1").html("请填写正确数量！");
				return false;
			}
		
	var money=$("#money").val();
	if(isNaN(money)){
			$("#money1").html("请填写正确价格！");
			return false;
		}
	
	var integral1=$("#integral").val();
	if(isNaN(integral1)){
			$("#integral1").html("请填写绿币！");
			return false;
		}	
} 
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/withdrawCash/insertIntegral" onsubmit="return checkForm()" >
	  <div class="form-group">
	    <label class="col-sm-2 control-label">商品名字：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="commodityname" name="commodityname" value="${tUser.username}" style="width: 150px">
	       <font color="red" id=commodityname12></font>
	    </div>
	   
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">标题：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="title" name="title" value="${tUser.truename}" style="width: 150px">
	    </div>
	  </div>
	    	   <div class="form-group">
	  	<label class="col-sm-2 control-label">展示图：</label>
	  	<div class="col-sm-10">
           <input type="button" id="about_us_pics"  value="上传图片" />
           <input type="hidden" id="about_us" name="displaythepicture"/>  
            <c:forEach var="pic" items="${logo}" varStatus="status">
	        	<img alt="帮助图" src="${pic}" style="width:10%;height:10%">
	        </c:forEach>
           <div id="J_imageView_about_us"></div>
	    </div>
	  </div>
	   <div class="form-group">
	     <label class="col-sm-2 control-label">邮费：</label>
	      <div class="col-sm-10">
		       <select class="form-control" style="width:120px;float:left" onchange="checkFormjk()" id="freight" name="freight">
		       			<option value="1">==请选择===</option>
	           			<option value="0" >包邮</option>
	           			<option value="10" >10元</option>
	           			<option value="20" >20元</option>
	           			<option value="30" >30元</option>
	           			<option value="40" >40元</option>
	           			<option value="50" >50元</option>
	           			<option value="70" >其他</option>
		    	</select>
		    		      <font color="red" id="qita"></font>
		  </div>
		
	    </div>
	    	   <div class="form-group">
	    <label class="col-sm-2 control-label">数量：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="numbe" name="numbe"  style="width: 200px">
	      <font color="red" id="numbe1"> </font>
	    </div>
	  </div>
	    
	    
	   <div class="form-group">
	    <label class="col-sm-2 control-label">价格：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="money" name="money"  style="width: 200px">
	      <font color="red" id="money1"> </font>
	    </div>
	  </div>
	  
	  
	     <div class="form-group">
	    <label class="col-sm-2 control-label">绿币：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="integral" name="integral"  style="width: 200px">
	      <font color="red" id="integral1"> </font>
	    </div>
	  </div>
	  
	  
	        <div class="form-group">
	    <label class="col-sm-2 control-label">详情内容：</label>
	    <div class="col-sm-10">
	      <textarea  name="realcontent" style="width:650px;height:500px;visibility:hidden;"></textarea>
	      <input type="hidden" id="myContent" name="details"/>
	      <input type="hidden" id="tempContent" name="tempContent"/>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	    <input type="hidden" id="userid" name="userid" value="${currentUser.id }">
	    <%--   <input type="hidden" id="id" name="id" value="${tUser.id }"/> --%>
	      <button type="submit"  class="btn btn-primary">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)">返回</button>
	    <!--   <font color="red" id="error"></font> -->
	    </div>
	  </div>
	  </form>
  </div>
</div>
</body>
</html>