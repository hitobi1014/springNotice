<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${ngvo.ntgu_name}</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>글번호</th>
					<!-- <th>부모글번호</th> -->
					<th>제목</th>
					<th>아이디</th>
					<th>작성일</th>
				</tr>
				<tbody id="noticeList">
					<c:forEach items="${noticeList}" var="notice">
						<tr data-userid="${notice.user_id }">
							<td>${notice.nt_num }</td>
							<%-- <td>${notice.nt_panum }</td> --%>
							<c:choose>
								<c:when test="${notice.ntcont_stat == 0}">
									<td><c:if test="${level >1 }">
											<c:forEach begin="1" end="${level}">
														&nbsp;&nbsp;&nbsp;&nbsp;
													</c:forEach>
										</c:if> [삭제된 게시글 입니다]</td>
								</c:when>
								<c:otherwise>
									<td><a href="${cp}/notice/detail?nt_num=${notice.nt_num}">
											<c:set var="level" value="${notice.level}" /> <c:if
												test="${level >1 }">
												<c:forEach begin="1" end="${level}">
														&nbsp;&nbsp;&nbsp;&nbsp;
													</c:forEach>
											</c:if> ${notice.nt_title }
									</a></td>
								</c:otherwise>
							</c:choose>
							<td>${notice.user_id }</td>
							<td><fmt:formatDate value="${notice.nt_dt }"
									pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<a class="btn btn-default pull-right" href="${cp}/notice/write">게시글작성</a>
		page : ${page}
		<div class="text-center">
			<ul class="pagination">
				<c:choose>
					<c:when test="${page==1}">
						<li class="active"><span>&lt;&lt;</span></li>
						<li class="active"><span>&lt;</span></li>
					</c:when>

					<c:otherwise>
						<li><a href="${cp}/noticeGubun/gubun?page=1&ntgu_code=${ngvo.ntgu_code}">&lt;&lt;</a></li>
						<li><a href="${cp}/noticeGubun/gubun?page=${page-1}&ntgu_code=${ngvo.ntgu_code}">&lt;</a></li>
					</c:otherwise>
				</c:choose>

				<c:forEach var="i" begin="1" end="${pages}">
					<c:choose>
						<c:when test="${i == page }">
							<li class="active"><span>${i}</span></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${cp}/noticeGubun/gubun?page=${i}&ntgu_code=${ngvo.ntgu_code}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${page==pages}">
						<li class="active"><span>&gt;</span></li>
						<li class="active"><span>&gt;&gt;</span></li>
					</c:when>

					<c:otherwise>
						<li><a
							href="${cp}/noticeGubun/gubun?page=${page+1}&ntgu_code=${ngvo.ntgu_code}">&gt;</a></li>
						<li><a
							href="${cp}/noticeGubun/gubun?page=${pages}&ntgu_code=${ngvo.ntgu_code}">&gt;&gt;</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</div>
