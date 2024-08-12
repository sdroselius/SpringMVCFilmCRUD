package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	public Film findFilmById(int filmId);

	public Actor findActorById(int actorId);

	public List<Actor> findActorsByFilmId(int filmId);

	public List<Film> findByKeyword(String keyword);

	public void addFilm(Film newFilm);

	public boolean deleteFilmById(Film filmToDelete);

	List<Film> findAllFilms();
}
