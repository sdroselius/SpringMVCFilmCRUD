<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details: ${film.title}</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">

</head>
<body>

	<jsp:include page="navbar.jsp" />

	<div class="container">

			<c:if test="${filmAdded }"><h2>Film Added.</h2></c:if>
			<c:if test="${filmUpdated }"><h2>Film Updated.</h2></c:if>
			<c:if test="${errorAddingFilm }"><h2>Error Adding Film.</h2></c:if>
			<c:if test="${errorUpdatingFilm }"><h2>Error Updating Film.</h2></c:if>
			<c:if test="${errorDeletingFilm }"><h2>Error Deleting Film.</h2></c:if>
		<c:choose>
			<c:when test="${not empty film }">
			<!-- DISPLAY FILM DETAILS -->
				<h1>${film.title } (${film.releaseYear })</h1>
				<p>Rated ${film.rating }</p>
				<p>${film.length } minutes</p>

				<div>${film.description }</div>
				
				<h3>Cast</h3>
				<ul>
					<c:forEach var="actor" items='${film.actors }'><li>${actor.firstName } ${actor.lastName }</li></c:forEach>
				</ul>
				
				<h3>Details</h3>
				<ul>
					<li>${film.language }</li>
					<li>Rental duration: ${film.rentalDuration }</li>
					<li>Rental fate: <fmt:formatNumber type="currency" value="${film.rentalRate }"/></li>
					<li>Replacement cost: <fmt:formatNumber type="currency" value="${film.replacementCost }"/></li>
					<c:if test="${not empty film.specialFeatures }">
					<li>Special features:
						<ul>
							<c:forEach var="feature" items='${film.specialFeatures.split(",") }'><li>${feature }</li></c:forEach>
						</ul>
					</li>
					</c:if>
					<c:if test="${not empty film.categories }">
					<li>Special features:
						<ul>
							<c:forEach var="category" items='${film.categories }'><li>${category.name }</li></c:forEach>
						</ul>
					</li>
					</c:if>
				</ul>
				
				<div class="row">
					<a class="btn btn-primary col-auto" href="goToEditFilm.do?filmId=${film.id }">Edit This Film</a>
					<a class="btn btn-danger col-auto" href="deleteFilm.do?filmId=${film.id }">Delete This Film</a>
				</div>
			</c:when>

			<c:when test="${not empty editFilm or not empty addingFilm}">
<jsp:include page="filmForm.jsp"/>
			</c:when>


			<c:otherwise>
				<h1>Film not found.</h1>
			</c:otherwise>
		</c:choose>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>