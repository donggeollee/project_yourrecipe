<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 작성</title>

<style>
body { 
  padding-top: 20%;
  padding-bottom: 30%;
}
</style>
</head>
<body>
<jsp:include
		page="${ requestScope.contextPath }/WEB-INF/jsp/include/bootstrapSetting.jsp"
		flush="false"></jsp:include>
<jsp:include
		page="${ requestScope.contextPath }/WEB-INF/jsp/include/loginHeader.jsp"
		flush="false">
		<jsp:param  name="nickname" value="${ loginUser.nickname }"/>	
</jsp:include>

<article>

		<div class="container" role="main">

			<h2 style="margin-bottom:5%">레시피 작성</h2>

			<form method="post" id="foo" action="<%=request.getContextPath()%>/article/write" enctype="multipart/form-data">

				<div class="mb-3">
					<label for="title">제목</label>
					<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
				</div>

				
				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="20" name="content" id="content" placeholder="내용을 입력해 주세요" ></textarea>
				</div>

				<div class="mb-3">
					<label for="tag">첨부이미지(다중 첨부 가능,사진을 한개 이상은 첨부해야 합니다.)</label>
					<input type="file" name="file" multiple="multiple" class="form-control">
				</div>
				
				<div >
				<button type="button" id="btn-submit" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-sm btn-primary" onclick="location.href='/1'" id="btnList">목록</button>
				</div>
				
			</form>

			

		</div>

	</article>

</body>
<script type="text/javascript">
$(function(){
	$("#btn-submit").on("click",function(){
		if ( $("input[name=file]").val().length == 0 ){
			alert("사진을 한개 이상 첨부해야 합니다.");	
			return;
		}
		$("#foo").submit();
	})
})
</script>
</html>

