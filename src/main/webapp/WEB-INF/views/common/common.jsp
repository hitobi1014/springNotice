<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
$(document).ready(function(){
	var userid = $('#user_id').val();
	if(userid ==null || userid == ""){
		location.href="/login";
	} 
})
</script>
<input type="hidden" value="${S_MEMBER.user_id}" id="user_id"/>