package com.wise.myapp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller

public class HomeController {

	@Autowired

	HomeDao homeDao;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)

	public String home(Model model) {

		List<HomeDto> list = homeDao.sel();

		for (int i = 0; i < list.size(); i++) {

			/*
			 * logger.info(list.get(i).getAccount_idx());
			 * 
			 * logger.info(list.get(i).getId());
			 */

			model.addAttribute("ID", list.get(0).getUser_id());

		}

		return "home";

	}

}
