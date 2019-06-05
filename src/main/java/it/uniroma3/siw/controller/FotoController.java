package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.services.FotoService;

@Controller
public class FotoController {
	@Autowired
	private FotoService fotoService;
	
	@RequestMapping("/addFoto")
	public String addFoto(Model model) {
		model.addAttribute("foto",new Foto());
		return "fotoForm.html";
	}
	
	@RequestMapping(value = "/foto/{id}" , method = RequestMethod.GET)
	public String getFoto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("foto",this.fotoService.getFotoById(id));
		return "foto.html";
	}
}