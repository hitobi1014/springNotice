<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.date {
	color: #b2b2b2;
	font-size: 0.9em;
}

.reply {
	font-weight: bold;
	font-size: 1.4em;
}

.userid {
	font-weight: bold;
	font-size: 1.2em;
}

.replyText, .replyText2 {
	border: 2px solid #b2b2b2;
	border-radius: 5px;
	padding: 8px;
	overflow: auto;
}

.replyText textarea, .replyText2 textarea {
	border: 0px;
	height: 50px;
	width: 900px;
}

#modBtn {
	float: left;
	margin-right: 5px;
}
</style>
<script>
	$(document).ready(function() {
		$('textarea').on('keyup', function() {
			textareaHeight();
		});
		
		function textareaHeight() {
			var textEle = $('textarea');
			textEle[0].style.height = 'auto';
			var textEleHeight = textEle.prop('scrollHeight');
			textEle.css('height', textEleHeight);
		};

	$('.repMod').on('click',function() {
		var divlen = $('.rep').length;
		var rep_num = $(this).attr('value');
		var cont = $(this).attr('val');
		for (var i = 0; i < divlen; i++) {
			if (rep_num == $('.rep:eq(' + i+ ') .repnum').val()) {
				$('.rep:eq(' + i + ')').html(
					"<div class='replyText2'>"
				+ "<p class='userid'><c:if test='${S_MEMBER.user_id != null }'>${S_MEMBER.user_id}</c:if></p>"

				+ "<form method='post' action='${cp}/replyUpdate'>"
				+ "	<input type='hidden' value='${S_MEMBER.user_id}' name='replyuserid'/>"
				+ "	<input type='hidden' value='"+rep_num+"' name='repnum'/>"
				+ "	<input type='hidden' value='${nvo.nt_num }' name='replyNtnum' />"
				+ "	<textarea name='replyCont'>"
				+ cont	
				+ "</textarea><br>"
				+ "	<p style='float:right'><button type='submit'>등록</button></p>"
				+ "	<p style='float:right'><button type='button' onclick='location.reload()'>취소</button></p>"
				+ "</form>"
				+ "</div>");
					}
				}
			})
		})
</script>
<div class="col-sm-8 blog-main">
	<div class="sub-header">
		<h2>${nvo.nt_title}</h2>
		<div>
			<span class="userid">${nvo.user_id} </span><br>
			<p style="float: right">${ngvo.ntgu_name}</p>
			<p style="float: left;" class="date">
				<fmt:formatDate value="${nvo.nt_dt}" pattern="yyyy-MM-dd HH:mm:ss" />
			</p>
		</div>
		<br>
	</div>
	<br>
	<div class="table-responsive">
		<div style="float: right">
			<c:if test="${nfvoList !=null }">
				<c:forEach items="${nfvoList}" var="nfvo">
					<button type="button" id="fileDownload" onclick="location.href='${cp}/notice/fileDownload?filenum=${nfvo.filenum}'">첨부파일
						: ${nfvo.filename}</button>
				</c:forEach>
			</c:if>
		</div>
		<p style="font-size: 1.2em">${nvo.nt_cont}</p>
	</div>
	<hr>
	<a href="${cp}/notice/answer?nt_num=${nvo.nt_num}"><span>답글쓰기</span></a>
	<br> <br>

	<!-- 댓글목록 -->
	<p class="reply">댓글</p>
	<c:forEach items="${replyList}" var="reply">
		<div class="rep">
			<span class="userid">${reply.user_id}</span><br>
			<c:choose>
				<c:when test="${reply.rep_stat == 0}">
					<span>[삭제된 댓글입니다]</span>
					<br>
				</c:when>
				<c:otherwise>
					<span class="repcont">${reply.rep_cont}</span>
					<br>
					<c:set var="rcont" value="${reply.rep_cont}" />
				</c:otherwise>
			</c:choose>
			<input type="hidden" value="${reply.rep_num }" name="repnum"
				class="repnum" />
			<c:if test="${S_MEMBER.user_id == reply.user_id }">
				<span class="date"><fmt:formatDate value="${reply.rep_dt}" pattern="yyyy-MM-dd HH:mm:ss" /></span> &nbsp; <c:if test="${reply.rep_stat ==1 }">
					<span><a href="#" class="repMod" value="${reply.rep_num}" val="${reply.rep_cont}">수정</a></span>
					<span><a href="${cp}/reply/delete?user_id=${reply.user_id}&rep_num=${reply.rep_num}&nt_num=${nvo.nt_num}">삭제</a></span>
				</c:if>
			</c:if>
		</div>
		<hr>
	</c:forEach>

	<!-- 댓글 쓰기 -->
	<div class="replyText">
		<p class="userid">
			<c:if test="${S_MEMBER.user_id != null }">${S_MEMBER.user_id}</c:if>
		</p>
		<form method="post" action="${cp}/reply/write">
			<input type="hidden" value="${S_MEMBER.user_id}" name="user_id" />
			<input type="hidden" value="${nvo.nt_num }" name="nt_num" />
			<textarea name="rep_cont"></textarea>
			<br>
			<p style="float: right">
				<button type="submit">등록</button>
			</p>
		</form>
	</div>

	<!-- 본인이 쓴 글에 대해 게시글 수정, 삭제 기능 -->
	<div>
		<c:if test="${S_MEMBER.user_id == nvo.user_id}">
			<p id="modBtn">
				<a class="btn btn-default pull-right" href="${cp }/notice/modify?user_id=${nvo.user_id}&nt_num=${nvo.nt_num}">수정</a>
				<a class="btn btn-default pull-right" href="${cp }/notice/delete?ntgu_code=${nvo.ntgu_code}&user_id=${nvo.user_id}&nt_num=${nvo.nt_num}">삭제</a>
			</p>
		</c:if>
	</div>

	<!-- 나중에 할 댓글 페이징처리==> 안해도될듯-->
	<div class="text-center">
		<ul class="pagination">
			<c:forEach var="i" begin="1" end="${pages }">
				<c:choose>
					<c:when test="${i == page }">
						<li class="active"><span>${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${pageContext.request.contextPath }/memberList?page=${i }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	</div>
</div>