package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.model.Funzionario;
import it.uniroma3.siw.services.FotoService;

@Controller
public class MainController {

	@Autowired
	private FotoService fotoService;

	// Login form
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("funzionario",new Funzionario());
		model.addAttribute("fotos",this.fotoService.getRandomFoto(3));
		return "index.html";
	}

	// Login form with error
	@RequestMapping("/login?error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";
	}

}
