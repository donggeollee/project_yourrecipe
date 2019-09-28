<%@page import="com.web.recipe.model.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 목록</title>
<style type="text/css">

.row {
	margin: 3% 10% 3% 10%;
}
.col-sm-4 {
	margin: 0% 0% 2% 0%;
}

.card {
	cursor: pointer;
}

.card-text {
	display: block;
	display: -webkit-box;
	max-width: 100%;
	margin: 10 auto;
	line-height: 1;
	-webkit-line-clamp: 3;
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
	line-height: 1;
}
.card-img-top{
 	width: 150px;
    height: auto;
}
.pagination{
	margin: 0% 37% 5% 37%;
}
#title{
	text-align:center;
	margin-top: 5%;
}
.search-cont{
	margin-top: 5%;
}
#curPage{
	text-decoration:underline;
}
</style>
</head>
<body>

<jsp:include
		page="${ requestScope.contextPath }/WEB-INF/jsp/include/bootstrapSetting.jsp"
		flush="false"></jsp:include>
<c:if test="${ not empty loginUser }" var="r">
	<jsp:include
			page="${ requestScope.contextPath }/WEB-INF/jsp/include/loginHeader.jsp"
			flush="false">
	<jsp:param  name="nickname" value="${ loginUser.nickname }"/>		
	</jsp:include>
</c:if>
<c:if test="${ not r}">
	<jsp:include
			page="${ requestScope.contextPath }/WEB-INF/jsp/include/logoutHeader.jsp"
			flush="false"></jsp:include>
</c:if>

	<h1>READ ARTICLELIST PAGE</h1>
	<div class="container">
	<h1 id="title" class="display-4">그대의 레시피</h1>

	<div class="search-cont">
		<label for="searchRecipe">레시피 검색</label>
		<input type="text" id="searchRecipe">
		<button type="button" id="btn-search"
		 class="btn btn-outline-dark btn-sm">검색</button>
		 <c:if test="${ not empty searchKeyword }">
			 <label>[<span id="searchResult">${ searchKeyword }</span>]에 대한 검색 결과입니다.</label>
		 </c:if>
	</div>

	<div class="row">
		<c:forEach  items="${ articleList }" var="article">
			<div class="col-sm-4">
				<div class="card" style="width: 18rem;"
					onclick="articleLink(${article.id});">
					<img
						src="<%=request.getContextPath()%>/resources/img/${ article.thumbnailName }"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title" id="title">${ article.title }
						</h5>
						<h5><span class="badge badge-secondary">${ article.user.nickname }</span></h5>
						<p class="card-text" id="content">${ article.content }</p>
						<span class="badge badge-primary badge-pill">추천수 ${ article.recommendationListSize }</span>
						<span class="badge badge-primary badge-pill">댓글수 ${ article.commentListSize }</span>
						<c:if test="${ not empty article.readCount }" var="r">
							<span class="badge badge-primary badge-pill">조회수 ${ article.readCount }</span>
						</c:if>
						<c:if test="${ not r }">
							<span class="badge badge-primary badge-pill">조회수 0</span>
						</c:if>
					</div>
					<div class="card-footer" style="text-align:center">
						<small class="text-muted">${ article.strCreatedAt }</small>
					</div>
				</div>
			</div>
			</c:forEach>
	</div>
	<div class="pagination">
		<c:if test="${ pageMap.previousPage != -1 }" var="previousPage_result">
			<a class="page-link" href="<%=request.getContextPath()%>/${pageMap.previousPage}/${not empty searchKeyword ? searchKeyword : ''}" 
			aria-label="Previous" >이전</a>
		</c:if>
		<c:forEach begin="${pageMap.startPage}" end="${pageMap.endPage}" var="page">
			<c:if test="${ page eq pageMap.curPage }" var="r">
				<a class="page-link" id="curPage" href="#">${page}</a>
			</c:if>
			<c:if test="${ not r}">
				<a class="page-link" href="<%=request.getContextPath()%>/${page}/${not empty searchKeyword ? searchKeyword : ''}">${page}</a>
			</c:if>
		</c:forEach>
		<c:if test="${ pageMap.nextPage != -1 }" var="nextPage_result">
			<a class="page-link" href="<%=request.getContextPath()%>/${pageMap.nextPage}/${not empty searchKeyword ? searchKeyword : ''}"
			aria-label="Next" >다음</a>
		</c:if>
	</div>
	</div>

</body>
<script type="text/javascript">
function articleLink(id){
	 window.location.href="<%=request.getContextPath()%>/article/detail/"+id;
} 
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/article/search.js"></script>
</html>