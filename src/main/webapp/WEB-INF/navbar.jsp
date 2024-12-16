<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<nav class="navbar navbar-expand-sm bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="home.do">Films</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="home.do">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
      </ul>
      <form class="d-flex" role="search" action="showFilm.do" method="GET">
        <button class="btn btn-outline-success" type="submit">Find</button>
        <input class="form-control me-2" type="search" placeholder="Film ID" aria-label="Search" name="filmId">
      </form>

      <form class="d-flex" role="search" action="showFilm.do" method="GET">
        <button class="btn btn-outline-success" type="submit">Search</button>
        <c:choose>
        <c:when test="${not empty keyword }">
        <input class="form-control me-2" type="search" value="${keyword}" aria-label="Search" name="keyword">
        </c:when>
        <c:otherwise>
        <input class="form-control me-2" type="search" placeholder="Keyword" aria-label="Search" name="keyword">
        </c:otherwise>
        </c:choose>
      </form>
    </div>
  </div>
</nav>