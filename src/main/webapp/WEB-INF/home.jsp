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

	<div id="filmLookupDiv">
		<h3>Know a Film's ID? Look it up!</h3>
		<form action="getFilm.do" method="POST">
			<label for="filmId">Enter Film ID</label>
			<input type="number" name="filmId" placeholder="Film Id #">
			<button>Search</button>
		</form>
	</div>
	
	<a href="goToAddFilm.do">Add a New Film</a>

</body>
</html>