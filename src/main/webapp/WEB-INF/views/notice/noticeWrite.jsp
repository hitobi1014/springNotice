<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>글작성 페이지</title>
<style>
#selectNotice{
	height: 30px;
	width: 700px;
	margin-top: 10px;
}
#title{
	height: 30px;
	width: 900px;
	margin-top: 10px;
}
#subBtn{
	height: 30px;
	width: 70px;
	margin-bottom: 10px;
}
</style>
<script>
$(document).ready(function(){
	$('#selectNotice').on('click',function(){
		$('#selectNotice option:eq(0)').css('display','none');
// 		$('#selectNotice option:eq(1)').prop('selected',true);
	})
	
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 1000자까지 쓸 수 있습니다'	//placeholder 설정
        
	});

	$('#subBtn').on('click',function(){
// 		console.log("정상작동");
		$('#frm').submit();
	})

	var i = 1;
	$('#fileAddBtn').on('click',function(){
		console.log('i값 : '+i);
		if(i>5){
			alert('파일은 최대 5개까지 첨부가능합니다');
		}else{
			$('.fileAdd[name=nt_file'+i+']').show();
			i++;
		}
	})
	
	$('.fileAdd').hide();
})
</script>

<div class="col-sm-8 blog-main">
	<div class="sub-header">
		<h2>글쓰기</h2>
	</div>
	<div>
		<form method="post" action="${cp}/noticeWrite" id="frm"
			enctype="multipart/form-data">
			<input type="hidden" value="${S_MEMBER.user_id}" name="user_id"
				id="user_id" />
			<div>
				<select id="selectNotice" name="noticeGubun">
					<option selected="selected">게시판을 선택해주세요</option>
					<c:forEach items="${noticeGubun}" var="noticeGubun">
						<c:if test="${noticeGubun.ntgu_stat ==1 }">
							<option value="${noticeGubun.ntgu_code}">${noticeGubun.ntgu_name}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div>
				<input type="text" id="title" placeholder="제목을 입력해주세요" name="title" />
				<button id="subBtn" type="submit">등록</button>
			</div>
			<textarea id="summernote" name="editordata"></textarea>
			<div class="filediv">
				<button type="button" id="fileAddBtn">파일추가</button>
				<input class="fileAdd" type="file" name="nt_file1" /> <input
					class="fileAdd" type="file" name="nt_file2" /> <input
					class="fileAdd" type="file" name="nt_file3" /> <input
					class="fileAdd" type="file" name="nt_file4" /> <input
					class="fileAdd" type="file" name="nt_file5" />
			</div>
		</form>
	</div>
</div>