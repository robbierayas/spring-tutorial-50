<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Offers</title>
</head>
<body>
	<br />

	<c:forEach var="row" items="${offers}">
	   <p><c:out value="${row}"></c:out></p>
	</c:forEach>


</body>
</html>