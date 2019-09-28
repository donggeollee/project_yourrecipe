<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 수정</title>
<style>
body {
	padding-top: 10%; 
	padding-bottom: 20%;  
}

.img-cont {
	text-align: left;
	float: none;
}

.img-cont img {
	width: 24%;
	height: 200px;
	cursor: pointer;
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
		<h2 style="margin-bottom:5%">레시피 수정</h2>
		<form method="post" id="form-cont" action="<%=request.getContextPath()%>/article/update" enctype="multipart/form-data">

			<div class="mb-3">
				<label for="title">제목</label>
				<input type="text" class="form-control" name="title" id="title" 
				value="${ article.title }" placeholder="제목을 입력해 주세요">
				<input type="hidden" name="id" value="${article.id}">
			</div>
			
			<div class="mb-3" id="article-content">
				<label for="content">내용</label>
				<textarea class="form-control" rows="20" name="content" id="content" 
				placeholder="내용을 입력해 주세요">${ article.content }</textarea>
			</div>
			
			<div class="img-cont">
			<div class="alert alert-primary" role="alert" style="max-width:50%;">이미지를 클릭하면 삭제됩니다.</div>
			<c:forEach items="${article.imageList}" var="articleImage">
				<img src="<%=request.getContextPath()%>/resources/img/${ articleImage.imageName }" 
				    id="article-image-${articleImage.id}" class="img-content" onclick="deleteImg(${articleImage.id});">
			</c:forEach>
			</div>
			
			<div class="mb-3">
				<label for="tag">첨부이미지 추가(다중 첨부 가능)</label>
				<input type="file" name="file" multiple="multiple" class="form-control">
			</div>
			
			<div >
			<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
			<button type="button" class="btn btn-sm btn-primary" onclick="location.href='/user/myPage/myLecipe'" id="btnList">목록</button>
			</div>
		</form>
	</div>
</article>
</body>
<script type="text/javascript">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/article/deleteImg.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/article/updateMyArticle.js"></script>
</html>