package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Category;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	  public Film findFilmById(int filmId);
	  public Actor findActorById(int actorId);
	  public List<Actor> findActorsByFilmId(int filmId);
	  public List<Category> findCategoriesByFilmId(int filmId);
	  List<Film> searchFilmsByKeyword(String keyword);
	  List<Film> randomFilms(int howMany);
	  
	  Film createFilm(Film film);
	  Film updateFilm(Film film);
	  boolean deleteFilm(int filmIdToDelete);

}
