package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	Film findFilmById(int filmId);
	Actor findActorById(int actorId);
	List<Actor> findActorsByFilmId(int filmId);
	List<Film> searchFilmsByKeyword(String keyword);
	
	Film addFilm(Film newFilm);
	Film updateFilm(Film updatingFilm);
	void deleteFilm(Film filmToDelete);
}
