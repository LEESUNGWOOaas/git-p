<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- j_spring_security_check만 쓰면 contextPath를 못찾아갈수도 있으니 tablib명시	 -->
	<form action="j_spring_security_check" method="post">
		ID:<input type="text" name="j_uesrname" /><br />
		PW:<input type="text" name="j_password" /><br />
		<input type="submit" value="LOGIN"/>
	</form>

</body>
</html>