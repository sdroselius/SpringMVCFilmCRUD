<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="container">
	<h1>Welcome to our Film Site</h1>

	<c:if test="${filmDeleted}"><h2>Film Deleted.</h2></c:if>

	<a href="goToAddFilm.do" class="btn btn-primary">Add a Film</a>
	<c:if test="${not empty keyword }">
		<h4>${films.size() } results found</h4>
	</c:if>
	<table class="table table-striped">
		<thead class="table-dark">
			<tr>
				<td>Title</td>
				<td>Release Year</td>
				<td>Rating</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="film" items="${films}">
			<tr>
				<td> <a href="showFilm.do?filmId=${film.id}">${film.title }</a> </td>
				<td>${film.releaseYear }</td>
				<td>${film.rating }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>