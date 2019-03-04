<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${map.blog.title }</h1>
			<ul>
				<c:choose>
					<c:when test="${authUser eq null }">
						<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
						<c:if test="${authUser.no eq map.blog.userNo }">
							<li><a href="${pageContext.request.contextPath}/admin/${map.blog.userNo}">블로그 관리</a></li>
						</c:if>
					</c:otherwise>
				</c:choose>
				<li><a href="${pageContext.request.contextPath}">메인 화면</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${vo.title }</h4>
					<p>
						${vo.content }
					<p>
					<!-- 댓글 -->
				</div>
				<c:forEach items="${map.postList }" var="vo">
				<ul class="blog-list">
					<li><a href="">${vo.title }</a> <span>${vo.regDate }</span>	</li>
				</ul>
				</c:forEach>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${map.blog.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${map.categoryList }" var="vo">
				<li><a href="">${vo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>