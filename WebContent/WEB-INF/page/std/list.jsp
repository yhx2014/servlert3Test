<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>std list</title>
<%@include file="/WEB-INF/page/common/common.jsp"%>

<script src="${ctx}/statics/jbox/jquery.browser.js"
	type="text/javascript"></script>
<script src="${ctx}/statics/jbox/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>

<link href="${ctx}/statics/jbox/css/GreyBlue/jbox.css" type="text/css" rel="stylesheet" />


<style type="text/css">
table.stripe tr td{ height:28px; line-height:28px; text-align:center;background:#FFF;vertical-align:middle;} 
/* css注释：默认css背景被白色 */ 
table tr.alt td { background:#F2F2F2;} 
/* css 注释：默认隔行背景颜色 */ 
table tr.over td {background:#EEECEB;} 
/* css注释：鼠标经过时候背景颜色 */ 
</style>
<script type="text/javascript">
	$(function() {
		 $(".table tr").mouseover(function(){    
		       //如果鼠标移到class为stripe的表格的tr上时，执行函数    
		      $(this).addClass("over");}).mouseout(function(){    
		            //给这行添加class值为over，并且当鼠标一出该行时执行函数    
		            $(this).removeClass("over");}) //移除该行的class    
		  $(".table tr:even").addClass("alt");    
		    //给class为stripe的表格的偶数行添加class值为alt 
		    //www.divcss5.com 整理特效 
		    
		    $(".btn-add").click(function(){
		    	$("form").attr("action","detail?type=add")
		    	$("form").submit();
		    });
		    
		    $(".table").on("click",".edit",function(){
		    	var id = $(this).closest("tr").attr("id");
		    	$("form").attr("action","detail?type=edit&id=" + id)
		    	$("form").submit();
		    }).on("click",".del",function(){
		    	var id = $(this).closest("tr").attr("id");
		    	$.jBox.confirm("确认要删除", "确认信息", function(d){
		    		if(d=='ok'){
		    			$("form").attr("action","del?type=del&id=" + id)
				    	$("form").submit();
		    		}
		    	});
		    });
	});
	
	$(function(){
		function go(){
			var $t = $(this);
			var pageNow = ${page.pageNow} || 1;
			if($t.hasClass("disabled")){
				return;
			}
			if($t.hasClass("next")){
				//下一页
				pageNow = pageNow + 1;
			}else if($t.hasClass("previous")){
				//上一页
				pageNow = pageNow - 1;
			}else if($t.hasClass("first")){
				
				pageNow =  1;
			}else if($t.hasClass("end")){
				pageNow =  ${page.sumPage};
			}
			$("#pageNow").val(pageNow);
			$("form").submit();
		}
		
		var liClass = ".previous,.next,.first,.end";
		$(".pagination").on("click",liClass,go);
	});
</script>
</head>
<body>

	<div class="body-container">
		<div class="page-header">
			<h1>
				Std list page header 
			</h1>
		</div>
		
		<div class="alert alert-warning alert-dismissible" id="messageBox"
			role="alert">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">×</span><span class="sr-only">Close</span>
			</button>
			<span id="msgSpan">..........</span>
		</div>

		<form class="form form-inline" style="width: 1000px" id="query-form" method="post" action="list">
			<input type="hidden" name="q_pageNow" id="pageNow" value="${page.pageNow }">
			<div class="form-group">
				<label class="control-label"></label> <input type="text"
					class="form-control" name="q_name" value="${rqp.q_name }" placeholder="名称或者身份证号" style="width: 200px;">
			</div>
			<button type="submit" class="btn btn-primary">
				<span class="glyphicon glyphicon-search"> 查询</span>
			</button>
			<button type="button" class="btn btn-default btn-add">
				<span class="glyphicon glyphicon-plus"> 新增</span>
			</button>
		</form>

		<br>

		<div class="body-list">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">std heading</div>

				<!-- Table -->
				<table class="table">
					<thead>
						<tr>
							<th width="20%">id</th>
							<th width="20%">姓名</th>
							<th width="20%">年龄</th>
							<th width="20%">生日</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.items}" var="tmp">
							<tr id="${tmp.studentID }">
								<td>${tmp.studentID }</td>
								<td>${tmp.studentName }</td>
								<td>${tmp.studentAge }</td>
								<td>${tmp.studentBirthdate}</td>
								<td>
									<button type="button" class="btn btn-default btn-sm edit">
										<span class="glyphicon glyphicon-edit"> 修改</span>
									</button>
									<button type="button" class="btn btn-default btn-sm del">
										<span class="glyphicon glyphicon-remove"> 删除</span>
									</button>
<%-- 									<img class="stdImg" alt="" src="image?id=${tmp.studentID  }"> --%>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<ul class="pagination">
						<li><span>总数：${page.sumCount }</span></li>
						<li><span>总页数：${page.sumPage}</span></li>
						<li><span>每页显示：${page.pageSize}</span></li>
					</ul>
					
					<ul  class="pagination">
						<li class="${page.pageNow==1?"disabled":"" } first"><a href="#">&laquo;</a></li>
						
						<c:if test="${page.pageNow>1 }">
							<li class="previous"><a href="#">${page.pageNow-1 }</a></li>
						</c:if>
						
						<li class="active"><a href="#">${page.pageNow } <span class="sr-only">(current)</span></a></li>
						
						<c:if test="${page.pageNow<page.sumPage }">
							<li class="next"><a href="#">${page.pageNow+1 }</a></li>
						</c:if>
						
						<li class="${page.pageNow==page.sumPage?"disabled":""} end"><a href="#">&raquo;</a></li>
					</ul>
		</div>
	</div>
</body>

</html>