<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
#selectNotice {
	height: 30px;
	width: 700px;
	margin-top: 10px;
}

#title {
	height: 30px;
	width: 900px;
	margin-top: 10px;
}

#subBtn {
	height: 30px;
	width: 70px;
	margin-bottom: 10px;
}
</style>
<script src="${cp}/js/summernote/summernote-lite.js"></script>
<script src="${cp}/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${cp}/css/summernote/summernote-lite.css">
</head>
<script>
	$(document).ready(function() {
		$('#selectNotice').on('click', function() {
			$('#selectNotice option:eq(0)').css('display', 'none');
		})

		$('#summernote').summernote({
			height : 300, // 에디터 높이
			minHeight : null, // 최소 높이
			maxHeight : null, // 최대 높이
			focus : true, // 에디터 로딩후 포커스를 맞출지 여부
			lang : "ko-KR", // 한글 설정
			placeholder : '최대 1000자까지 쓸 수 있습니다' //placeholder 설정

		});

		$('#subBtn').on('click', function() {
			$('#frm').submit();
		})

		var i = 1;
		$('#fileAddBtn').on('click', function() {
			console.log('i값 : ' + i);
			if (i > 5) {
				alert('파일은 최대 5개까지 첨부가능합니다');
			} else {
				$('.fileAdd[name=nt_file' + i + ']').show();
				i++;
			}
		})

		$('.fileAdd').hide();
	})
</script>
<div class="col-sm-8 blog-main">
	<div class="sub-header">
		<h2>${nt_panum}번게시글 : 답글쓰기</h2>
	</div>
	<div>
		<form method="post" action="${cp}/notice/answer" id="frm" enctype="multipart/form-data">
			<input type="hidden" value="${S_MEMBER.user_id}" name="user_id" id="user_id" />
			<div>
				<input type="hidden" value="${nt_panum}" name="nt_panum"/>
				<select id="selectNotice" name="ntgu_code">
					<option selected="selected" value="${ngvo.ntgu_code}">${ngvo.ntgu_name}</option>
				</select>
			</div>
			<div>
				<input type="text" id="title" placeholder="제목을 입력해주세요" name="nt_title" />
				<button id="subBtn" type="submit">등록</button>
			</div>
			<textarea id="summernote" name="editordata"></textarea>
			<div class="filediv">
				<input class="fileAdd" type="file" name="nt_file" value="파일추가"/>
			</div>
		</form>
	</div>
</div>