<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
#tb, th {
	text-align: center;
}
</style>
<div class="col-sm-8 blog-main">
	<h2 class="sub-header">게시판관리</h2>
	<form action="${cp}/noticeGubunInsert" method="post">
		<input type="text" id="ntgu_code" name="ntgu_code" placeholder="게시판코드" />
		<input type="text" id="ntgu_name" name="ntgu_name" placeholder="게시판이름" />
		<button type="submit">게시판 생성</button>
	</form>
	<div class="table-responsive">
		<table id="tb" class="table table-striped">
			<tr>
				<th>게시판코드</th>
				<th>게시판명</th>
				<th>사용여부</th>
				<th>버튼</th>
			</tr>
			<tbody id="noticeGubunList">
				<c:forEach items="${noticeGubun}" var="nogu">
					<form action="${cp}/noticeGubunModify" method="post">
						<tr>
							<input type="hidden" value="${nogu.ntgu_code}" name="ntgu_code" />
							<td>${nogu.ntgu_code}</td>
							<td>${nogu.ntgu_name}</td>
							<td><select name="noticeUse">
									<c:if test="${nogu.ntgu_stat==1 }">
										<option value="1" selected="selected">사용중</option>
										<option value="0">미사용</option>
									</c:if>
									<c:if test="${nogu.ntgu_stat==0 }">
										<option value="1">사용중</option>
										<option value="0" selected="selected">미사용</option>
									</c:if>
							</select></td>
							<td>
								<button type="submit">변경</button>
							</td>
						</tr>
					</form>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>