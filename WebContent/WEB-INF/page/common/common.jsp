<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<meta http-equiv="Cache-Control" content="no-store" />

<script src="${ctx}/statics/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/statics/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/statics/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/statics/jquery-validation/jquery.validate.method.min.js" type="text/javascript"></script>
<link href="${ctx}/statics/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/statics/jquery-validation/jquery.validate.min.css" type="text/css" rel="stylesheet" />

<style type="text/css">
	body{
	padding-left: 100px;padding-right: 100px;
	}
	
	.stdImg{
		width: 100px;
		height: 100px;
	}
</style>

<script type="text/javascript">
	$(function(){
		$("#messageBox").hide();
		
		var msg = "${msg}" || "${rqp.msg}" ;
		if(""!=msg){
			$("#msgSpan").html(msg);
			$("#messageBox").fadeIn(500);
			
			setTimeout(function(){
				$("#messageBox").fadeOut(500);
			},4000)
		}
	});
</script>