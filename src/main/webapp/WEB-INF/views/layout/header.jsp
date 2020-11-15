<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${cp}/main">JSP/SPRING 
				<c:if test="${S_MEMBER.user_id != null }">[${S_MEMBER.user_id} 님 환영합니다]</c:if>
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${cp}/noticeManage">게시판관리</a></li>
				<c:choose>
					<c:when test="${S_MEMBER.user_id != null }">
						<li><a href="${cp}/logout">로그아웃</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${cp}/login">로그인</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
</nav>