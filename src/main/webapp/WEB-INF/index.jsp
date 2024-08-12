<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
</head>
<body>

<h1>Welcome to the Film Site</h1>

<form action="showFilm.do">
<input type="text" name="filmId" placeholder="Film ID">
<button>Find Film by ID</button>
</form>

<%-- <c:if test="${not empty filmList }">
 <c:forEach var="film" items="${filmList }">
 <li>${film.title }</li>
 </c:forEach>
</c:if> --%>

<!-- TODO form with filmId field and button, action="getFilm.do" -->

<!-- <form action="createFilm.do">
<input type="text" name="title">
<input type="text" name="description">
<input type="text" name="releaseYear">
</form> -->
</body>
</html> 