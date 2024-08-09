package com.skilldistillery.film.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FilmController {
	
	@RequestMapping( path={"index.do", "/"} )
	public String index() {
		return "WEB-INF/index.jsp";
	}

}
