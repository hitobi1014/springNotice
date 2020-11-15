<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="replyText2">
	<p class="userid"><c:if test="${S_MEMBER.user_id != null }">${S_MEMBER.user_id}</c:if></p>
		<form method="post" action="${cp}/reply">
			<input type="hidden" value="${S_MEMBER.user_id}" name="replyuserid"/>
			<input type="hidden" value="${nvo.nt_num }" name="replyNtnum" />
			<textarea name="replyCont"></textarea><br>
			<p style="float:right"><button type="submit">등록</button></p>
			<p style="float:right"><button type="button">취소</button></p>
		</form>
</div>