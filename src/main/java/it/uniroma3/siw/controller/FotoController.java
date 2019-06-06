package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		Foto foto = this.fotoService.getFotoById(id);
		model.addAttribute("foto",foto);
		String base64EncodedImage = Base64.encodeBase64String(foto.getImage());
		model.addAttribute("base64EncodedImage", base64EncodedImage);
		return "foto.html";
	}

	@RequestMapping(value="/salvaFoto",method = RequestMethod.POST)
	public String newFoto(@Valid @ModelAttribute("foto") Foto foto, Model model,BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors())//in caso non ci siano errori
			this.fotoService.salvaFoto(foto); //esegui il persist
		return "index.html";
	}
}
