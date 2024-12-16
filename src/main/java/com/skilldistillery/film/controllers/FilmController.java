package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = { "/", "home.do" })
	public String goHome(Model model) {
		List<Film> films = filmDao.randomFilms(10);
		model.addAttribute("films", films);
		return "home";
	}

	@RequestMapping(path = { "showFilm.do" }, params = { "filmId" })
	public String showFilm(@RequestParam("filmId") Integer filmId, Model model) {
		Film film = filmDao.findFilmById(filmId);
		model.addAttribute("film", film);
		return "showFilm";
	}

	@RequestMapping(path = { "showFilm.do" }, params = { "keyword" })
	public String searchByKeyword(@RequestParam("keyword") String keyword, Model model) {
		List<Film> films = filmDao.searchFilmsByKeyword(keyword);
		model.addAttribute("films", films);
		model.addAttribute("keyword", keyword);
		return "home";
	}

	@RequestMapping(path = { "goToEditFilm.do" }, params = { "filmId" })
	public String goToEdit(@RequestParam("filmId") Integer filmId, Model model) {
		Film film = filmDao.findFilmById(filmId);
		model.addAttribute("editFilm", film);
		return "showFilm";
	}

	@RequestMapping(path = { "goToAddFilm.do" })
	public String goToAdd(Model model) {
		model.addAttribute("addingFilm", true);
		return "showFilm";
	}

	@RequestMapping(path = { "addFilm.do" }, method = RequestMethod.POST)
	public String addFilm(Model model, Film newFilm,
			@RequestParam(name = "features", defaultValue = "") String[] features,
			RedirectAttributes redir
	) {
		if (features != null && features.length > 0) {
			System.out.println(features);
			newFilm.setSpecialFeatures(String.join(",", features));
		}
		Film addedFilm = filmDao.createFilm(newFilm);
		if (addedFilm == null) {
			model.asMap().remove("film");
			model.addAttribute("addingFilm", true);
			model.addAttribute("errorAddingFilm", true);
			System.out.println(model);
			return "showFilm" ;
		} else {
			model.addAttribute("filmAdded", true);
			return "redirect:showFilm.do?filmId=" + addedFilm.getId();
		}
	}

	@RequestMapping(path = { "updateFilm.do" }, method = RequestMethod.POST)
	public String updateFilm(Model model, Film updatingFilm,
			@RequestParam(name = "features", defaultValue = "") String[] features, RedirectAttributes redir) {
		updatingFilm.setSpecialFeatures(String.join(",", features));
		int filmId = updatingFilm.getId();
		System.out.println(updatingFilm);
		System.out.println("+++++++++ " + filmId);
		Film updatedFilm = filmDao.createFilm(updatingFilm);
		if (updatedFilm == null) {
			redir.addFlashAttribute("errorUpdatingFilm", true);
			return "redirect:goToEditFilm.do?filmId=" + filmId;
		} else {
			model.addAttribute("updatedFilm", true);
			return "redirect:showFilm.do?filmId=" + updatedFilm.getId();
		}
	}

	@RequestMapping(path = { "deleteFilm.do" }, params = "filmId")
	public String deleteFilm(Model model, @RequestParam("filmId") Integer filmId, RedirectAttributes redir) {
		if (filmDao.deleteFilm(filmId)) {
			redir.addFlashAttribute("filmDeleted", true);
			return "redirect:home.do";
		} else {
			model.addAttribute("errorDeletingFilm", true);
			return "redirect:showFilm.do?filmId=" + filmId;
		}
	}

}
