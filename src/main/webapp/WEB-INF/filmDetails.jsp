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

<a href="index.do">Main Page</a>

<h1>Film Details</h1>

<c:choose>
<c:when test="${empty film}">
	<h3>Film not found.</h3>
</c:when>
<c:otherwise>
	<h3>${film.title}</h3>
	<p>${film.releaseYear }, Rated ${film.rating }</p>
	<p>${film.length } minutes</p>
	<blockquote>${film.description }</blockquote>
	
<form action="goToEditFilm.do?filmId=${film.id}">
<button>Edit this film</button>	
</form>
<form action="deleteFilm.do?filmId=${film.id}">
<button>Delete this film</button>	
</form>

</c:otherwise>
</c:choose>
	
</body>
</html> 