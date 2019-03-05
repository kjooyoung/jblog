<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css?ver=1">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn-comment").click(function(){
		$(".comment-content").show();
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<c:choose>
					<c:when test="${post ne null }">
						<div class="blog-content">
							<h4>${post.title }</h4>
							<p>${post.content }
							<p>
								<!-- 댓글 -->
							<button id="btn-comment" style="float:right;" type="button">댓글</button>
						</div>
						<div class="comment-content">
							<form action="" class="comment-form">
								<input name="${authUser.no }" type="hidden">
								<textarea id=cont placeholder="댓글을 입력해주세요."></textarea>
								<input type="submit" value="등록">
							</form>
							<table>
								<tr>
									<th>주영</th>
								</tr>
								<tr>
									<td>ㅎㅇ</td>
								</tr>
							</table>
						</div>
						<div class="blog-content">
						<ul class="blog-list">
							<c:forEach items="${postList }" var="vo">
								<li><a href="${pageContext.request.contextPath}/${id}/${vo.categoryNo}/${vo.no}">
										${vo.title }</a> <span>${vo.regDate }</span></li>
							</c:forEach>
						</ul>
						</div>
					</c:when>
					<c:otherwise>
						<div class="blog-content">
							<h4>해당 카테고리의 글이 없습니다.</h4>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blog.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList }" var="vo">
					<li><a
						href="${pageContext.request.contextPath}/${id}/${vo.no}">${vo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>