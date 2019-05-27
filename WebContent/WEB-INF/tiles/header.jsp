<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="<c:url value="/"/>" class="title">Offers</a>
<sec:authorize access="!isAuthenticated()">
		<a href="<c:url value="/login"/>" class="login">Login</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
		<a href="<c:url value="/logout"/>" class="logout">Logout</a>
	<!--<c:url var="logoutUrl" value="/logout" />
	<form action="${logoutUrl}" method="post">
		<input class="login" type="submit" value="Log out"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	-->
</sec:authorize>