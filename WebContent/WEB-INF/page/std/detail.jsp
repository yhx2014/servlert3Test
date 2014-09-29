<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>std detail</title>
<%@include file="/WEB-INF/page/common/common.jsp"%>
<script src="${ctx}/statics/DatePicker/WdatePicker.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		function init(){
			var rules = {
					rules:{
						<c:if test="${type=='add' }">
						studentID:{
							required:true,
							card:true,
							remote:{
								type:"POST",
								url:"checkExist",
								data:{
									studentID:function(d){return $("#studentID").val();}
								},
								dataFilter:function(d,t){
									return d+"";									 
								}
							}
						},
						</c:if>
						studentName:{
							required:true,
							rangelength:[2,5]
						},
						studentAge:{
							required:true,
							digits:true,
							range:[0,150]
						},
						studentBirthdate:{
							required:true
						}
						 
					},
					messages:{
						studentID:{remote:"已经存在 重新输入"}
					}
			};

			$(".form").validate(rules);
			
			$("#studentID").focus();
			
			$(".btn-back").click(function(){
				//返回
				$("#listForm").submit();
			});
		}
		init();
	});
</script>
</head>
<body>
	<div class="body-container">
		<div class="page-header">
			<h1>Std detail header</h1>
		</div>


		<div class="alert alert-warning alert-dismissible" id="messageBox"
			role="alert">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">×</span><span class="sr-only">Close</span>
			</button>
			<span id="msgSpan">..........</span>
		</div>

		<form class="form form-horizontal" method="post" action="save"
			style="width: 500px" id="query-form" enctype="Multipart/form-data">
			<input type="hidden" name="type" value="${type }">
			<div class="form-group">
				<label for="studentID" class="col-sm-2 control-label">身份证</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="studentID"
						name="studentID" value="${model.studentID }" maxlength="20"
						${type=='edit'?"disabled":"" }
						placeholder="身份证件号">
						<c:if test="${type=='edit' }">
							<input type="hidden" name="studentID" value="${model.studentID }">
						</c:if>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="studentName">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="studentName"
						id="studentName" maxlength="30" value="${model.studentName}"
						placeholder="姓名">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="studentAge">年领</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="studentAge"
						id="studentAge" maxlength="3" value="${model.studentAge}"
						placeholder="年龄">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="studentBirthdate">生日</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="studentBirthdate"
						id="studentBirthdate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
						value="${model.studentBirthdate}" placeholder="生日">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="studentPicture">照片</label>
				<div class="col-sm-5">
					<input type="file" class="form-control" name="studentPicture"
						id="studentPicture" placeholder="照片">
				</div>
				<div class="col-sm-5">
				<c:if test="${model.existPic==1 }">
					<img class="stdImg" alt="" src="image?id=${model.studentID  }">
				</c:if>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-default btn-back">返回</button>
				</div>
			</div>
		</form>
		
		<form action="list" method="post" id="listForm">
			<input type="hidden" name="q_pageNow" value="${rqp.q_pageNow}">
			<input type="hidden" name="q_name" value="${rqp.q_name}">
		</form>
	</div>
</body>
</html>