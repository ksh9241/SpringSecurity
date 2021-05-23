<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.addEventListener("DOMContentLoaded",function(){
		document.querySelector("#signupBt").addEventListener("click",function(){
			location.href = "signup"
		})
	})
</script>
</head>
<body>
	<table>
		<tr>
			<th>유저명</th>
			<th>비밀번호</th>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.username}</td>
				<td>${item.password }</td>
			</tr>
		</c:forEach>
	</table>
	<button type="button" id="signupBt">SignUp</button>
</body>
</html>