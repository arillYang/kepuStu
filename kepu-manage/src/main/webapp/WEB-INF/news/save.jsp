<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
  <style type="text/css">
    .preview-bb-center {
    width: 330px;
    height: 615px;
    position: fixed;
    top: 4%;
    left: 40%;
    background: url(http://ikow.oss-cn-shanghai.aliyuncs.com/newsImages/9bd67fd6796c4947a1b57ec3012f62e8.png) no-repeat;
    background-size: 100% 100%;
    z-index: 99999;
    display: none;
}
.preview-bb-center a.close {
    padding: 0 14px;
    float: right;
    font-size: 20px;
    margin-top: -5px;
    margin-right: -5px;
    height: 40px;
    line-height: 40px;
    background: #f00;
    color: #fff;
    border-radius: 50px;
    -webkit-border-radius: 50px;
    display: inline;
}
.input_wenbk {
    font-family: 微软雅黑;
    width: 450px;
    height: 33px;
    line-height: 33px;
    padding-left: 10px;
    color: rgb(102, 102, 102);
    border-width: 1px;
    border-style: solid;
    border-color: rgb(204, 204, 204);
    border-image: initial;
    background: rgb(255, 255, 255);
}
.kzjxx_iteam {
    height: 35px;
    overflow: hidden;
    margin-bottom: 10px;
    line-height: 35px;
}
  </style>
<script type="text/javascript">
function yincang(){  
	 document.getElementById("task").style.display="none";  
	 $("#time").val("");
}
function show(){  
	 document.getElementById("task").style.display="block";  
}
function qyincang(){  
	 document.getElementById("q1").style.display="none";  
	 
}
function qshow(){  
	 document.getElementById("q1").style.display="block";  
}
	$(function() {
		$(".preview-bb-center a.close").click(function(){
			$(".preview-bb-hidden,.preview-bb-center").css("display","none");
			$("#mobile-preview").attr("src", "");
		});
		var hourValue='${hour}';
		var questionStr='${questionStr}';
		if(questionStr!=null&&questionStr!=''){
			document.getElementById("q1").style.display="block";  
		}
		if(hourValue!=null&&hourValue!=''){
			document.getElementById("task").style.display="block";  
			 var hour = document.getElementById("hour"); 
			 var ops = hour.options;  
	            for(var i=0;i<ops.length; i++){  
	                var tempValue = ops[i].value;  
	                if(tempValue == hourValue)   
	                {  
	                    ops[i].selected = true;  
	                    break;  
	                }  
	            }   
		}
		var boxObj = $("input:checkbox[name='classifyid']");   
	   // var express = '${typeList}';  
	    var expresslist ='${typeList}';
   		var express = expresslist.split(','); 
	    $.each(express, function(index, expressId){  
	       boxObj.each(function () {  
	            if($(this).val() == expressId) {  
	               $(this).attr("checked",true);  
	            }  
	        });  
	    });  
	    
	    $('input[name="classifyid"]').change(function(){
	        $('#classify').val($('input[name="classifyid"]:checked').map(function(){return this.value}).get().join(','))
	      })
	});
	function CheckAll(val) { 
		 $("input[name='classifyid']").each(function() { 
		 	this.checked = val; 
		 }); 
	 } 
	KindEditor.ready(function(K) {
		var editor = K.editor({
			allowFileManager : true,
			allowImageUpload : true,//运行上传图片  
			uploadJson : "${pageContext.request.contextPath}"+'/news/upload' //  服务端上传图片处理URI
		});
		K('#J_selectImage').click(function() {
			editor.uploadJson="${pageContext.request.contextPath}"+'/news/upload'+"?head=1";
			editor.loadPlugin('multiimage', function() {
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var imgArray = [];
						var div = K('#J_imageView_hot');
						div.html('');
						K.each(urlList, function(i, data) {
							imgArray.push(data.url);
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
							$("#pic_hot").attr("href",data.url); 
						});
						  $("#newsimages").val(imgArray.join(","));
						editor.hideDialog();
					}
				});
			});
		});
		K('#J_selectImage_latterPic').click(function() {
			editor.loadPlugin('multiimage', function() {
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var imgArray = [];
						var div = K('#J_imageView_latterPic');
						div.html('');
						K.each(urlList, function(i, data) {
							imgArray.push(data.url);
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
							$("#pic_hot").attr("href",data.url); 
						});
					    $("#latterpic").val(imgArray.join(","));
						editor.hideDialog();
					}
				});
			});
		});
		K('#J_selectImage_firstPic').click(function() {
			editor.loadPlugin('multiimage', function() {
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var div = K('#J_imageView_firstPic');
						div.html('');
						K.each(urlList, function(i, data) {
							div.append('<img src="' + data.url + '"  style="width:20%;height:20%;" >');
							$("#firstpic").val(data.url);
							$("#pic_firstPic").attr("href",data.url); 
						});
						editor.hideDialog();
					}
				});
			});
		});
	});
	
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
	function fun(name){
		 obj = document.getElementsByName(name); 
         check_val = []; 
	     for(k in obj){ 
           if(obj[k].checked) 
           check_val.push(obj[k].value); 
	     } 
		return check_val.length==0;
	}
	function checkForm(){
		//var t=$("#classifyid").val()
		var t="${news.uid}";
		var v=$('#classify').val();
		var k=$('#keywords').val();
		var a=$('#newsauthor').val();
		var username=$('#username').val();
		var block=$("#q1").css('display');
		var question=block=="block"?"1":"0";
		// var question=$("input[name='question']:checked").val();
		if((t==null||t=="")&&(v==null||v=="")){
			$("#error").html("请选择分类");
			return false;
		}
		if(k==null||k==""){
			$("#error").html("请填写文章来源");
			return false;
		}
		if(a==null||a==""){
			$("#error").html("请填写栏目名称");
			return false;
		}
		if(username==null||username==""){
			$("#error").html("请填写作者");
			return false;
		}
		if(question=='1'){
			//  检查互动完整
			var title1=$("input[name='title1']").val(); //1
			var opA1=$("input[name='opA1']").val();
			var opB1=$("input[name='opB1']").val();
			var opC1=$("input[name='opC1']").val();
			var opD1=$("input[name='opD1']").val();
			if(title1==null||title1==""||opA1==null||opA1==""||opB1==null||opB1==""||opC1==null||opC1==""
				||opD1==null||opD1==""||fun("answer1")){
				$("#error").html("请填写问题1");
				return false;
			}
			var title2=$("input[name='title2']").val(); //2
			var opA2=$("input[name='opA2']").val();
			var opB2=$("input[name='opB2']").val();
			var opC2=$("input[name='opC2']").val();
			var opD2=$("input[name='opD2']").val();
			var answer2=$("input[name='answer2']").val();
			if(title2==null||title2==""||opA2==null||opA2==""||opB2==null||opB2==""||opC2==null||opC2==""
				||opD2==null||opD2==""||fun("answer2")){
				$("#error").html("请填写问题2");
				return false;
			}
			var title3=$("input[name='title3']").val(); //3
			var opA3=$("input[name='opA3']").val();
			var opB3=$("input[name='opB3']").val();
			var opC3=$("input[name='opC3']").val();
			var opD3=$("input[name='opD3']").val();
			var answer3=$("input[name='answer3']").val();
			if(title3==null||title3==""||opA3==null||opA3==""||opB3==null||opB3==""||opC3==null||opC3==""
				||opD3==null||opD3==""||fun("answer3")){
				$("#error").html("请填写问题3");
				return false;
			}
		}
		return true;
	}
	function saveAndLive(){
		var t="${news.uid}";
		if(t==null||t==""){
			$.ajax({
				type: "post",
		   	    url: "${pageContext.request.contextPath}/news/save2",
		   	    data: $("#myForm").serialize(),
		   	    dataType: "json",
		   	    success: function(result){
		   	    	var tp=JSON.stringify(result); 
		   	    	var json = JSON.parse(tp); 
		   	    	t=json.newsId;
		   	    	$("#uid").val(t);
		   	    	$("#error").html("http://kp.appwzd.cn/header/Iknow/newsDetail.html?newsId="+t);
		   	    	//alert("http://kp.appwzd.cn/header/Iknow/newsDetail.html?newsId="+t);
	                //$("#mobile-preview").attr("src", "http://kp.appwzd.cn/header/Iknow/newsDetail.html?newsId="+t);
	                //$("#url").val("http://kp.appwzd.cn/header/Iknow/newsDetail.html?newsId="+t);
		   	    }
			});
		}else{
			//$("#url").val("http://kp.appwzd.cn/header/Iknow/newsDetail.html?newsId="+t);
			$("#error").html("http://kp.appwzd.cn/header/Iknow/newsDetail.html?newsId="+t);
		}
	 //document.getElementById("preView").style.display="block";  
	}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" id="myForm" method="post" action="${pageContext.request.contextPath}/news/save" onsubmit="return checkForm()">	 
	  <div class="form-group">
	    <label class="col-sm-2 control-label">新闻标题：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="title" name="title" value="${news.title}" style="width: 600px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">样式分类：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" name="newsstyle" value="1" ${news.newsstyle==1?'checked':''} checked>单图(小)
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" name="newsstyle" value="2" ${news.newsstyle==2?'checked':''}>单图(大)
	 	 </label>
	 	 <label class="checkbox-inline">
	    	<input type="radio" name="newsstyle" value="3" ${news.newsstyle==3?'checked':''}>三图
	 	 </label>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">新闻logo：</label>
	    <div class="col-sm-10">
           <input type="button" id="J_selectImage"  value="上传图片" />
           <span style="color:red">温馨提示：大单图比例，15:6；三图比例：4:3；小单图比例：4:3,图片控制在1M之内,不能用gif格式图片</span>
           <input type="hidden" id="newsimages" name="newsimages"/>  
           <div id="J_imageView_hot">
	           		<c:forEach var="pic" items="${logo}" varStatus="status">
	           			<c:if test="${pic!=null}">
		           			<img src="${pic}" style="width:20%;height:20%;" >
		           		</c:if>
		        	</c:forEach>
           	</div>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">新闻分类：</label>
	     <div class="col-sm-10">
	    	<c:forEach var="classify" items="${classifyList}" varStatus="status">
    				<label class="checkbox-inline">
    					<input type="checkbox" name="classifyid" value="${classify.uid}">${classify.classifyname}
    				</label>
	        </c:forEach>
	        <label class="checkbox-inline">
	         	<input id="checkAll" type="checkbox"  onclick="CheckAll(this.checked)"/>全选
	         </label>
	         <input type="hidden" name="classify" id="classify">
	     </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">文章来源：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="keywords" name="keywords" value="${news.keywords}" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">责任编辑：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="newsauthor" name="newsauthor" value="${news.newsauthor}" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">作者(用于统计)：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="username" name="username" value="${news.username}" style="width: 300px">
	    </div>
	  </div>
	    <div class="form-group">
	    <label class="col-sm-2 control-label">新闻内容：</label>
	    <div class="col-sm-10">
	      <textarea  name="realcontent" style="width:650px;height:500px;visibility:hidden;">${realcontent.content}</textarea>
	      <input type="hidden" id="myContent" name="myContent"/>
	      <input type="hidden" id="tempContent" name="tempContent"/>
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">外链名称：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="urlName" name="urlName" value="${urlName}" style="width: 300px">
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">外链：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="url" name="url" value="${url}" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">是否置顶：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" name="stick" value="1" ${news.stick==1?'checked':''} checked>是
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" name="stick" value="0" ${news.stick==0?'checked':''}>否
	 	 </label>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">是否轮播：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" value="1" name="carousel"  ${news.carousel==1?'checked':''} checked>是
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="0" name="carousel"  ${news.carousel==0?'checked':''}>否
	 	 </label>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">是否草稿：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" value="1" name="draft"  ${news.draft==1?'checked':''} checked>是
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="0" name="draft"  ${news.draft==0?'checked':''}>否
	 	 </label>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">是否定时：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" value="1" name="dingshi" onclick="show()" ${dateStr!=null?'checked':''}>是
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="0" name="dingshi"  onclick="yincang()" ${dateStr==null?'checked':''} >否
	 	 </label>
	  </div>
	   <div class="form-group" style="display:none" id="task">
	    <label class="col-sm-2 control-label">定时发布(选填)</label>
	    <div class="col-sm-10">
	      <input id="time" name="time" class="form-control" style="width: 100px;float:left" value="${dateStr}">
	      <label class="col-sm-2 control-label">小时</label>
	      <select class="form-control" style="width: 150px" id="hour" name="hour">
	    		<option >请选择...</option>
	    			<% for(int i=0;i<24;i++){
	    				%>
	    				<option value="<%=i%>" ><%=i<10?"0"+i:i%></option>
	    			<%}
	    			%>
	    	</select>
	    </div>
	  </div>
	   <div class="form-group">
	    <label class="col-sm-2 control-label">互动问答：</label>
	    <label class="checkbox-inline">
	    	<input type="radio" value="1" name="question" onclick="qshow()" ${questionStr!=null?'checked':''}>有
	  	</label>
	  	<label class="checkbox-inline">
	    	<input type="radio" value="0" name="question"  onclick="qyincang()" ${questionStr==null?'checked':''} >无
	 	 </label>
	  </div>
	   <div class="form-group" style="display:none" id="q1">
	      <label class="col-sm-2 control-label">题目设置：</label>
	        <div class="col-sm-10">
	              <div class="kzjxx_iteam" >
	                  <label class="col-sm-2 control-label">问题1：</label>
	                  <input name="title1" type="text" value="${qList[0].subject}" class="input_wenbk" style="width:350px" >
	                   <label><input type="hidden" id="uid1" name="uid1" value="${qList[0].urid}"/><span style="color:Red">题目内容</span></label>
	              </div>
                  <div class="kzjxx_iteam" style="margin-left:40px">
                      <label class="col-sm-2 control-label">选项A：</label>
                      <input name="opA1" type="text" class="input_wenbk" value="${qList[0].choice1}" style="width:320px">
                      <label><input name="answer1" value="A" ${fn:contains(qList[0].answer,'A')?'checked':''} type="checkbox"  class="fxk"> <span>答案</span></label>
                  </div>
                 <div class="kzjxx_iteam" style="margin-left:40px">
                     <label class="col-sm-2 control-label">选项B：</label>
                        <input name="opB1" type="text" class="input_wenbk" value="${qList[0].choice2}" style="width:320px">
                     <label><input name="answer1" value="B" ${fn:contains(qList[0].answer,'B')?'checked':''}  type="checkbox"  class="fxk"> <span>答案</span></label>
                    </div>
                    <div class="kzjxx_iteam" style="margin-left:40px">
                        <label class="col-sm-2 control-label">选项C：</label> 
                        <input name="opC1" type="text" class="input_wenbk" value="${qList[0].choice3}"  style="width:320px">
                        <label><input name="answer1" value="C" ${fn:contains(qList[0].answer,'C')?'checked':''}  type="checkbox"  class="fxk"> <span>答案</span></label>
                    </div>
                    <div class="kzjxx_iteam" style="margin-left:40px">
                        <label class="col-sm-2 control-label">选项D：</label> 
                        <input name="opD1" type="text" class="input_wenbk"  value="${qList[0].choice4}" style="width:320px">
                        <label><input name="answer1" value="D" ${fn:contains(qList[0].answer,'D')?'checked':''}  type="checkbox"  class="fxk"> <span>答案</span></label>
                    </div>
                    <div class="kzjxx_iteam" >
	                  <label class="col-sm-2 control-label">问题2：</label>
	                  <input name="title2" type="text" value="${qList[1].subject}" class="input_wenbk" style="width:350px" >
	                   <label><input type="hidden" id="uid2" name="uid2" value="${qList[1].urid}"/><span style="color:Red">题目内容</span></label>
	              </div>
                  <div class="kzjxx_iteam" style="margin-left:40px">
                      <label class="col-sm-2 control-label">选项A：</label>
                      <input name="opA2" type="text" class="input_wenbk"  value="${qList[1].choice1}" style="width:320px">
                      <label><input name="answer2" value="A" type="checkbox" ${fn:contains(qList[1].answer,'A')?'checked':''} class="fxk"> <span>答案</span></label>
                  </div>
                 <div class="kzjxx_iteam" style="margin-left:40px">
                     <label class="col-sm-2 control-label">选项B：</label>
                        <input name="opB2" type="text" class="input_wenbk"  value="${qList[1].choice2}" style="width:320px">
                     <label><input name="answer2" value="B" type="checkbox" ${fn:contains(qList[1].answer,'B')?'checked':''} class="fxk"> <span>答案</span></label>
                    </div>
                    <div class="kzjxx_iteam" style="margin-left:40px">
                        <label class="col-sm-2 control-label">选项C：</label> 
                        <input name="opC2" type="text" class="input_wenbk"  value="${qList[1].choice3}" style="width:320px">
                        <label><input name="answer2" value="C" type="checkbox" ${fn:contains(qList[1].answer,'C')?'checked':''} class="fxk"> <span>答案</span></label>
                    </div>
                    <div class="kzjxx_iteam" style="margin-left:40px">
                        <label class="col-sm-2 control-label">选项D：</label> 
                        <input name="opD2" type="text" class="input_wenbk"  value="${qList[1].choice4}" style="width:320px">
                        <label><input name="answer2" value="D" type="checkbox"  ${fn:contains(qList[1].answer,'D')?'checked':''} class="fxk"> <span>答案</span></label>
                    </div>
                    <div class="kzjxx_iteam" >
	                  <label class="col-sm-2 control-label">问题3：</label>
	                  <input name="title3" type="text" value="${qList[2].subject}" class="input_wenbk" style="width:350px" >
	                   <label><input type="hidden" id="uid3" name="uid3" value="${qList[2].urid}"/><span style="color:Red">题目内容</span></label>
	              </div>
                  <div class="kzjxx_iteam" style="margin-left:40px">
                      <label class="col-sm-2 control-label">选项A：</label>
                      <input name="opA3" type="text" class="input_wenbk" value="${qList[2].choice1}"  style="width:320px">
                      <label><input name="answer3" value="A" type="checkbox" ${fn:contains(qList[2].answer,'A')?'checked':''} class="fxk"> <span>答案</span></label>
                  </div>
                 <div class="kzjxx_iteam" style="margin-left:40px">
                     <label class="col-sm-2 control-label">选项B：</label>
                        <input name="opB3" type="text" class="input_wenbk" value="${qList[2].choice2}" style="width:320px">
                     <label><input name="answer3" value="B" type="checkbox"  ${fn:contains(qList[2].answer,'B')?'checked':''} class="fxk"> <span>答案</span></label>
                    </div>
                    <div class="kzjxx_iteam" style="margin-left:40px">
                        <label class="col-sm-2 control-label">选项C：</label> 
                        <input name="opC3" type="text" class="input_wenbk" value="${qList[2].choice3}" style="width:320px">
                        <label><input name="answer3" value="C" type="checkbox" ${fn:contains(qList[2].answer,'C')?'checked':''} class="fxk"> <span>答案</span></label>
                    </div>
                    <div class="kzjxx_iteam" style="margin-left:40px">
                        <label class="col-sm-2 control-label">选项D：</label> 
                        <input name="opD3" type="text" class="input_wenbk" value="${qList[2].choice4}"  style="width:320px">
                        <label><input name="answer3" value="D" type="checkbox" ${fn:contains(qList[2].answer,'D')?'checked':''}  class="fxk"> <span>答案</span></label>
                    </div>
               </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="uid" name="uid" value="${news.uid }"/>
	      <input type="hidden" name="type" value="${type}"/>
	      <button type="button" class="btn btn-primary" onclick="saveAndLive()">获取外链</button>&nbsp;&nbsp;
	      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)">返回</button>
	      <br><font color="red" id="error"></font>
	    </div>
	  </div>
	  </form>
	  <div class="preview-bb-center" id="preView" style="display: none;">
		<a class="close" href="javascript:;">X</a>
		<iframe id="mobile-preview" style=" float:left; margin-left:10px;margin-top:20px; border:1px solid #aaa;overflow-x : hidden;overflow-y : hidden;" frameborder="0" width="310" height="480" marginwidth="0" marginheight="0" scrolling="yes" src="http://kp.appwzd.cn/header/Iknow/newsDetail.html?newsId=${news.uid }"></iframe>
	</div>
  </div>
</div>
<script>
	;!function(){
		laydate({
		   elem: '#time',
		   format: 'YYYY-MM-DD', 
		})
	}();
</script>
</body>
</html>