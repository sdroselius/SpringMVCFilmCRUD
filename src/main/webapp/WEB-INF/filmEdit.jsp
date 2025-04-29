<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to BlockBlister</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
</head>
<body>

<nav class="navbar">
	<a href="home.do" class="btn btn-outline-primary">Home</a>
</nav>

<div class="container">
	<h1>Welcome to BlockBlister</h1>

	<div id="filmForm">
	<c:choose>
	<c:when test="${not empty film}">
		<form action="updateFilm.do" method="POST">
		<input type="hidden" name="id" value="${film.id}">
	</c:when>
	<c:otherwise>
		<form action="addFilm.do" method="POST">
	</c:otherwise>
	</c:choose>
			<div>
				<label for="title">Title</label>
				<input type="text" name="title" value="${film.title}">
			</div>
			<div>
				<label for="description">Description</label>
				<textarea rows="3" cols="30" name="description">${film.description}</textarea>
			</div>
			<div>
				<label for="languageId">Language Id</label>
				<input type="number" name="languageId" value="${film.languageId}">
			</div>
			<div>
				<label for="rating">Rating</label>
				<input type="text" name="rating" value="${film.rating}">
			</div>
			<div>
				<label for="releaseYear">Release Year</label>
				<input type="number" name="releaseYear" value="${film.releaseYear}">
			</div>
			<div>
				<label for="length">Length in minutes</label>
				<input type="number" name="length" value="${film.length}">
			</div>
			<div>
				<label for="rentalDuration">Rental Duration</label>
				<input type="number" name="rentalDuration" value="${film.rentalDuration}">
			</div>
			<div>
				<label for="rentalRate">Rental Rate</label>
				<input type="number" name="rentalRate" value="${film.rentalRate}">
			</div>
			<div>
				<label for="replacementCost">Replacement Cost</label>
				<input type="number" name="replacementCost" value="${film.replacementCost}">
			</div>
			<div>
				<label for="specialFeatures">Special Features</label>
				<input type="text" name="specialFeatures" value="${film.specialFeatures}">
			</div>
			
		<c:choose>
		<c:when test="${not empty film}">
			<button>Update Film</button>
		</c:when>
		<c:otherwise>
			<button>Add Film</button>
		</c:otherwise>
		</c:choose>
			
		</form>
	</div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
</body>
</html>