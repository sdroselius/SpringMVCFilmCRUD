package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = { "/", "home.do" })
	private String goHome(Model model) {
		return "home";
	}

	@RequestMapping(path = "getFilm.do")
	private String lookupFilmById(@RequestParam("filmId") Integer filmId, Model model) {
		Film film = filmDao.findFilmById(filmId);
		model.addAttribute("film", film);
		return "filmDetails";
	}

	@RequestMapping(path = "keywordSearch.do")
	private String lookupFilmByKeyword(@RequestParam("keyword") String keyword, Model model) {
		model.addAttribute("searchResults", filmDao.searchFilmsByKeyword(keyword));
		return "home";
	}
	
	@RequestMapping(path = "goToAddFilm.do")
	private String goToAddFilmForm() {
		return "filmEdit";
	}

	@RequestMapping(path = "addFilm.do")
	private String addNewFilm(Film filmToAdd, Model model) {
		Film addedFilm = filmDao.addFilm(filmToAdd);
		model.addAttribute("film", addedFilm);
		return "filmDetails";
	}

	@RequestMapping(path = "deleteFilm.do")
	private String deleteFilm(@RequestParam("filmId") Integer filmId, Model model) {
		boolean deleted = filmDao.deleteFilm(filmId);
		if (deleted) {
			return "redirect:home.do";
		} else {
			model.addAttribute("film", filmDao.findFilmById(filmId));
			model.addAttribute("message", "Unable to delete film");
			return "filmDetails";
		}
	}

	@RequestMapping(path = "goToUpdateFilm.do")
	private String goToUpdateFilmForm(@RequestParam("filmId") Integer filmId, Model model) {
		Film film = filmDao.findFilmById(filmId);
		model.addAttribute("film", film);
		return "filmEdit";
	}

	@RequestMapping(path = "updateFilm.do")
	private String updateFilmForm(Film updatingFilm, Model model) {
		Film updatedFilm = filmDao.updateFilm(updatingFilm);
		if (updatedFilm != null) {
			return "redirect:getFilm.do?filmId=" + updatingFilm.getId();
		}
		else {
			return "redirect:goToUpdateFilm.do?filmId=" + updatingFilm.getId();
		}
	}
	
}
