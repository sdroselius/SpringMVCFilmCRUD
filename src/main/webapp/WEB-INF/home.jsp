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

	<div id="filmLookupDiv">
		<h3>Know a Film's ID? Look it up!</h3>
		<form action="getFilm.do" method="POST">
			<label for="filmId">Enter Film ID</label>
			<input type="number" name="filmId" placeholder="Film Id #">
			<button>Search</button>
		</form>
	</div>
	
	<div id="filmSearchDiv">
		<h3>Search by Keyword</h3>
		<form action="keywordSearch.do" method="GET">
			<label for="keyword">Enter Film ID</label>
			<input type="text" name="keyword" placeholder="Search Term(s)">
			<button>Search</button>
		</form>
	</div>
	
	<a href="goToAddFilm.do">Add a New Film</a>

	<hr>
	<c:if test="${not empty searchResults }">
	<div class="searchResults">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="film" items="${searchResults}">
				<tr>
					<td>${film.id }</td>
					<td><a href="getFilm.do?filmId=${film.id }">${film.title} (${film.releaseYear })</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
</body>
</html>