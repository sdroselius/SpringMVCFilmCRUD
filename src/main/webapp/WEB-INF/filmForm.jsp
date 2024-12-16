<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<c:choose>
<c:when test="${not empty editFilm }">
<form action="updateFilm.do" method="POST">
</c:when>
<c:otherwise>
<form action="addFilm.do" method="POST">
</c:otherwise>
</c:choose>
	<c:if test="${not empty editFilm }">
	<input type="hidden" name="id" value="${editFilm.id }">
	</c:if>
	<div class="row">
		<label class="form-label col" for="title">Title</label>
		<input class="form-control col" type="text" name="title" value="${editFilm.title }">
	</div>

	<div class="row">
		<label class="form-label col" for="releaseYear">Release Year</label>
		<input class="form-control col" type="number" name="releaseYear" value="${editFilm.releaseYear }">
	</div>

	<div class="row">
		<label class="form-label col" for="rating">Rating</label>
		<div class="col">
		<select class="form-select rating-dropdown" name="rating">
			<option value="G"
			<c:if test='${editFilm.rating == "G" }'> selected </c:if>
			>G</option>
			<option value="PG"
			<c:if test='${editFilm.rating == "PG" }'> selected </c:if>
			>PG</option>
			<option value="PG-13"
			<c:if test='${editFilm.rating == "PG-13" }'> selected </c:if>
			>PG-13</option>
			<option value="R"
			<c:if test='${editFilm.rating == "R" }'> selected </c:if>
			>R</option>
			<option value="NC17"
			<c:if test='${editFilm.rating == "NC17" }'> selected </c:if>
			>NC17</option>
		</select>
		</div>
	</div>

	<div class="row">
		<label class="form-label col" for="description">Description</label>
		<textarea class="form-control col" rows="3" cols="40" name="description">${editFilm.description }</textarea>
	</div>
	
	<div class="row">
		<label class="form-label col" for="language">Language</label>
		<div class="col">
		<select class="form-select rating-dropdown" name="languageId">
			<option value="1"
			<c:if test='${editFilm.language == "English" }'> selected </c:if>
			>English</option>
			<option value="2"
			<c:if test='${editFilm.language == "Italian" }'> selected </c:if>
			>Italian</option>
			<option value="3"
			<c:if test='${editFilm.language == "Japanese" }'> selected </c:if>
			>Japanese</option>
			<option value="4"
			<c:if test='${editFilm.language == "Mandarin" }'> selected </c:if>
			>Mandarin</option>
			<option value="5"
			<c:if test='${editFilm.language == "French" }'> selected </c:if>
			>French</option>
			<option value="6"
			<c:if test='${editFilm.language == "German" }'> selected </c:if>
			>German</option>
		</select>
		</div>
	</div>

	<div class="row">
		<label class="form-label col" for="length">Length (minutes)</label>
		<input class="form-control col" type="number" name="length" value="${editFilm.length }">
	</div>

	<div class="row">
		<label class="form-label col" for="rentalDuration">Rental Duration (days)</label>
		<input class="form-control col" type="number" name="rentalDuration" value="${editFilm.rentalDuration }">
	</div>

	<div class="row">
		<label class="form-label col" for="rentalRate">Rental Rate</label>
		<input class="form-control col" type="number" name="rentalRate" value="${editFilm.rentalRate }">
	</div>

	<div class="row">
		<label class="form-label col" for="replacementCost">Replacement Cost</label>
		<input class="form-control col" type="number" name="replacementCost" value="${editFilm.replacementCost }">
	</div>

	<div class="row">
		<div class="col">Special Features</div>
		<div class="col">
			<div class="form-check form-check-inline">
				<div>
					<label class="form-check-label col" for="Trailers">Trailers</label>
					<input class="form-check-input col" type="checkbox" id="Trailers" name="features"
			<c:if test='${editFilm.specialFeatures.contains("Trailers") }'> checked </c:if>
					value="Trailers">
				</div>
			</div>
			<div class="form-check form-check-inline">
				<div>
					<label class="form-check-label col" for="Commentaries">Commentaries</label>
					<input class="form-check-input col" type="checkbox" id="Commentaries" name="features"
			<c:if test='${editFilm.specialFeatures.contains("Commentaries") }'> checked </c:if>
					 value="Commentaries">
				</div>
			</div>
			<div class="form-check form-check-inline">
				<div>
					<label class="form-check-label col" for="Deleted Scenes">Deleted Scenes</label>
					<input class="form-check-input col" type="checkbox" id="Deleted Scenes" name="features" 
			<c:if test='${editFilm.specialFeatures.contains("Deleted Scenes") }'> checked </c:if>
					value="Deleted Scenes">
				</div>
			</div>
			<div class="form-check form-check-inline">
				<div>
					<label class="form-check-label col" for="Behind the Scenes">Behind the Scenes</label>
					<input class="form-check-input col" type="checkbox" id="Behind the Scenes" name="features" 
			<c:if test='${editFilm.specialFeatures.contains("Behind the Scenes") }'> checked </c:if>
					value="Behind the Scenes">
				</div>
			</div>
		</div>
	</div>

<c:choose>
<c:when test="${not empty editFilm }">
	<button class="btn btn-primary">Update Film</button>
</c:when>
<c:otherwise>
	<button class="btn btn-primary">Add Film</button>
</c:otherwise>
</c:choose>
	
</form>

