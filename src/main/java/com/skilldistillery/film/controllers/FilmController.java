package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	
	@Autowired
	private FilmDAO filmDao;
	
	@RequestMapping( path={"index.do", "/"} )
	public String index(Model model) {
//		model.addAttribute("filmList", filmDao.findAllFilms());
		return "WEB-INF/index.jsp";
	}
	
	@RequestMapping( path={"showFilm.do"}, params="filmId" )
	public ModelAndView showFilm(@RequestParam("filmId") Integer filmId) {
		ModelAndView mv = new ModelAndView("WEB-INF/filmDetails.jsp");
		mv.addObject("film", filmDao.findFilmById(filmId));
		return mv;
	}
	
	//TODO: method mapped to getFilm.do, param filmId
	public ModelAndView addNewFilm(@RequestParam("title") String title, @RequestParam("description") String desc) {
		Film newFilm = new Film();
		newFilm.setTitle(title);
		ModelAndView mv = new ModelAndView();
		return null;
	}
	
	public ModelAndView addNewFilm(Film newFilm) {
		filmDao.addFilm(newFilm);
		return null;
	}

}
