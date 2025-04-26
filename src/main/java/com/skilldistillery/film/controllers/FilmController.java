package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	
	@Autowired
	private FilmDAO filmDao;
	
	@RequestMapping(path={ "/", "home.do" })
	private String goHome(Model model) {
		return "home";
	}
	
	@RequestMapping(path="getFilm.do")
	private String lookupFilmById(@RequestParam("filmId") Integer filmId, Model model) {
		Film film = filmDao.findFilmById(filmId);
		model.addAttribute("film", film);
		return "filmDetails";
	}

	@RequestMapping(path="goToAddFilm.do")
	private String goToAddFilmForm() {
		return "filmEdit";
	}
	
	@RequestMapping(path="addFilm.do")
	private String addNewFilm(Film filmToAdd, Model model) {
		Film addedFilm = filmDao.addFilm(filmToAdd);
		model.addAttribute("film", addedFilm);
		return "filmDetails";
	}
	
	@RequestMapping(path="goToUpdateFilm.do")
	private String goToUpdateFilmForm(@RequestParam("filmId") Integer filmId, Model model) {
		Film film = filmDao.findFilmById(filmId);
		model.addAttribute("film", film);
		return "filmEdit";
	}
	
}
