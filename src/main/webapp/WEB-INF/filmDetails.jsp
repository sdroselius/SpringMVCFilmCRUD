<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>
</head>
<body>

	<c:choose>
		<c:when test="${empty film }">
			<h1>Film Not Found.</h1>
		</c:when>
		<c:otherwise>
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
		</c:otherwise>
	</c:choose>

</body>
</html>