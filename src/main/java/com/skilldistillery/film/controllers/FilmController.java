package com.skilldistillery.film.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FilmController {
	
	@RequestMapping( path = {"/", "home.do"} )
	public String goHome(Model model) {
//		return "WEB-INF/home.jsp";
		return "home";
	}

}
