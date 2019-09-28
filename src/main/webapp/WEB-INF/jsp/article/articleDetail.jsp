<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>asdfasdf</title>
<style> 
	.article-title{
		text-align:center;
	}
	.noComment td{
		text-align:center;
		font-weight: bold;
	}
	.comment-label{
		font-weight: bold;
	}
	#btn-create-comment{
		margin-top:10px;
		margin-bottom:15px;
	}
	.article-title{
		margin:60px 0px 20px 0px;
	}
	#recomCount{
		margin-left:5px;
	}
	#article-content{
		margin-top:20px;
	}
</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/imageSlide.css">
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

<article>
	<div class="container" role="main">
		<h2 class="article-title">
		<span class="badge badge-secondary">${ article.user.nickname }</span>
		${ article.title }
			<span class="recommendation-cont">
			<c:set var="temp" value="false"/>
			<c:forEach items="${ article.recommendationList }" var="recommendation">
				<c:if test="${ recommendation.user.id eq loginUser.id }" var="r">
					<c:set var="temp" value="true"/>
					<c:set var="recommId" value="${ recommendation.id }"/>
				</c:if>
			</c:forEach>
				<c:if test="${ not temp }">
					<button class="btn btn-default btn-sm" onclick="btnLike()">추천
					<span id="recomCount">${ article.recommendationListSize }</span></button>
				</c:if>
				<c:if test="${ temp }">  
					<button class="btn btn-danger btn-sm" onclick="btnNoLike(<c:out value="${recommId}"/>);" >추천취소 
					<span id="recomCount">${ article.recommendationListSize }</span></button>
				</c:if>
			</span>
		</h2>
		
		<div class="slideshow-container">		
		<c:forEach items="${article.imageList}" var="articleImage" varStatus="status">
		<!-- Slideshow container -->
		  <!-- Full-width images with number and caption text -->
		  <div class="mySlides fade">
		    <div class="numbertext">${status.index + 1} / ${article.imageListSize}</div>
		    <img src="<%=request.getContextPath()%>/resources/img/${ articleImage.imageName }" 
		    class="img-content" style="width:100%">
		    <div class="text">사진</div>
		  </div>
		</c:forEach>
		<!-- Next and previous buttons -->
		<c:if test="${not empty article.imageList }">
		  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
		  <a class="next" onclick="plusSlides(1)">&#10095;</a>
		  </c:if>
		</div>
		<br>
		<!-- The dots/circles -->
		<div style="text-align:center">
		<c:forEach items="${article.imageList}" var="articleImage" varStatus="status">
		  <span class="dot" onclick="currentSlide(${status.index + 1})"></span>
		</c:forEach>
		</div>
		
		
		<div id="article-content" class="mb-3">
			<textarea class="form-control" rows="20" name="content" id="content"
			readonly>${ article.content }</textarea> 
		</div>

		<label class="comment-label">댓글 (${ article.commentListSize })</label>
		<div class="mb-3" id="comment-cont">
			 	<table class="table" style="table-layout:fixed">
			 		<thead>
			 			<tr>
			 				<th width="20%">작성자</th>
			 				<th width="60%">내용</th>  
			 				<th width="25%">작성시간</th> 
			 			</tr>
			 		</thead>
			 		<tbody id="comment-content">
			 		<c:if test="${article.commentListSize == 0}">
			 			<tr class="noComment">
			 				<td colspan="3">댓글이 없습니다. 첫 댓글을 작성해보세요!</td>
			 			</tr>
			 		</c:if>
			 		<c:forEach items="${ article.commentList }" var="comment">
			 			<tr>
			 				<td>${ comment.user.nickname }</td>
			 				<td id="update-content-${ comment.id }">${ comment.content }</td>
			 				<td>${ comment.strCreatedAt }
			 				<c:if test="${ loginUser.id eq comment.user.id }">
			 				<span id="btn-up-comment-${ comment.id }">
			 				<button class="btn btn-primary btn-sm" onclick="updateInputComment(${comment.id},'${comment.content}');" 
			 				id="btn-update-comment-${ comment.id }">수정</button>
			 				</span>  
			 				<button class="btn btn-warning btn-sm" onclick="deleteComment(${comment.id});" 
			 				id="btn-delete-comment">삭제</button>
			 				</c:if>
			 				</td>  
			 			</tr>
			 		</c:forEach>
			 		</tbody>
			 	</table>
			 
		</div>
		<div >
			<textarea class="form-control" rows="5" name="content" id="comment-write"></textarea>
			<button type="button" class="btn btn-primary" id="btn-create-comment">댓글작성</button>
		</div>
	</div>
	
</article> 
</body>
<script type="text/javascript">
	$("#content").val().trim();
	var articleId = "${article.id}";
	var userId = "${loginUser.id}";
	var image_exist_check = ${ empty article.imageList ? 0 : 1 };
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/comment/createComment.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/comment/deleteComment.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/comment/updateComment.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/recommendation/recommendation.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/article/imageSlide.js"></script>
</html>