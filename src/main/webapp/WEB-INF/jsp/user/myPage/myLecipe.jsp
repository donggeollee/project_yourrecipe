<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.recipe-cont {
	margin: 0% 10% 20% 10%;
}

.recipe_title {
	margin: 2% 10% 5% 10%;
	text-align: center;
}

.btn-link {
	margin: 5% 10% 0% 10%;
}

.title {
	display: block;
	display: -webkit-box;
	max-width: 100%;
	margin: 10 auto;
	line-height: 1;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
}

.content {
	display: block;
	display: -webkit-box;
	max-width: 100%;
	margin: 10 auto;
	line-height: 1;
	-webkit-line-clamp: 3;
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
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

<h1>나의 레시피</h1>
<div class= "btn-link">
<a href="<%=request.getContextPath()%>/user/myPage/myLecipe" class="btn btn-danger">나의 레시피</a>
<a href="<%=request.getContextPath()%>/user/myPage/myRecommend" class="btn btn-warning">추천한 레시피</a>
<a href="<%=request.getContextPath()%>/user/myPage/myInfo" class="btn btn-primary">내 정보 수정</a>
</div>
<h1 class="recipe_title">나의 레시피</h1>

<div class="recipe-cont">
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col" width="5%">No</th>
      <th scope="col" width="10%">제목</th>
      <th scope="col" width="48%">내용</th>
      <th scope="col" width="8%">좋아요</th>
      <th scope="col" width="8%">댓글수</th>
      <th scope="col" width="8%">조회수</th>
      <th scope="col" width="11%">관리</th>
    </tr>
  </thead>
  <tbody>
  <c:if test="${ empty articleList }">
  	<tr>
  	<th colspan=7 style="text-align:center">내가 작성한 게시글이 존재하지 않습니다. 레시피를 등록해보세요</th>
  	</tr>
  </c:if>
  <c:forEach items="${ articleList }" var="article" varStatus="status">
  	 <tr id="lecipe-row-${ article.id }">
      <th scope="row">${ status.index + 1 }</th> 
      <td><div class="title"><a href="<%=request.getContextPath()%>/article/detail/${article.id}">${ article.title }</a></div></td>
      <td><div class="content">${ article.content }</div></td>   
      <td>${ article.commentListSize }</td>
      <td>${ article.recommendationListSize }</td>
		<td><c:if test="${ not empty article.readCount }" var="r">
				${ article.readCount }
			</c:if> <c:if test="${ not r }">
				 0
			</c:if></td>
		<td>
		<a href="<%=request.getContextPath()%>/article/update/${article.id}" class="btn btn-primary btn-sm">수정</a>
		<button class="btn btn-warning btn-sm" onclick="deleteMyArticle(${article.id});" 
		id="btn-delete-comment">삭제</button>
      </td>
    </tr>
  </c:forEach>
   
  </tbody>
</table>
</div>

</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/article/deleteMyLecipe.js"></script>
</html>