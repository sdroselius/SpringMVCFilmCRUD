<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
</head>
<body>

<nav class="navbar">
	<a href="home.do" class="btn btn-outline-primary">Home</a>
</nav>

<div class="container">

	<c:if test="${not empty message }">
	<h2>${message }</h2>
	</c:if>
	<c:choose>
		<c:when test="${empty film }">
			<h1>Film Not Found.</h1>
		</c:when>
		<c:otherwise>
			<div class="filmDetails">
			<h1>${film.title} (${film.releaseYear})</h1>
			<blockquote>${film.description }</blockquote>
			<hr>
			<ul>
				<li>Rated ${film.rating }</li>
				<li>${film.length } minutes</li>
				<li>Language ${film.language }</li>
				<li>Rental Duration: ${film.rentalDuration }</li>
				<li>Rental Rate: ${film.rentalRate }</li>
				<li>Special Features: ${film.specialFeatures }
			</ul>
			<a href="goToUpdateFilm.do?filmId=${film.id}">Update</a>
			<a href="deleteFilm.do?filmId=${film.id}">Delete</a>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
</body>
</html>