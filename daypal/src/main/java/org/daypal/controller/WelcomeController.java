package org.daypal.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping({ "/", "/welcome", "login" })
	public String welcome(Map<String, Object> model) {

		return "Nothing to see here";
	}
}
