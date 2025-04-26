<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to BlockBlister</title>
</head>
<body>

	<h1>Welcome to BlockBlister</h1>

	<div id="filmForm">
	<c:choose>
	<c:when test="${not empty film}">
		<form action="updateFilm.do" method="POST">
	</c:when>
	<c:otherwise>
		<form action="addFilm.do" method="POST">
	</c:otherwise>
	</c:choose>
			<div>
				<label for="title">Title</label>
				<input type="text" name="title">
			</div>
			<div>
				<label for="description">Description</label>
				<textarea rows="3" cols="30" name="description"></textarea>
			</div>
			<div>
				<label for="languageId">Language Id</label>
				<input type="number" name="languageId">
			</div>
			<div>
				<label for="releaseYear">Release Year</label>
				<input type="number" name="releaseYear">
			</div>
			<div>
				<label for="length">Length in minutes</label>
				<input type="number" name="length">
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

</body>
</html>