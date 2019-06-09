package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.services.AlbumService;

@Controller
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping("/inserisciAlbum")
	public String addFoto(Model model) {
		model.addAttribute("album",new Album());
		return "albumForm.html";
	}
	
	@RequestMapping(value = "/cancellaAlbum/{id}" , method = RequestMethod.GET)
	public String deleteAlbum(@PathVariable("id") long id, Model model) {
		Album album = this.albumService.getAlbumById(id);
		if(album == null)
			return "redirect:/tuamadre.html";
		this.albumService.deleteAlbumById(id);
		return "redirect:/index.html";
	}
	
	@RequestMapping(value = "/album/{id}" , method = RequestMethod.GET)
	public String getFoto(@PathVariable("id") long id, Model model) {
		Album album = this.albumService.getAlbumById(id);
		if(album == null)
			return "redirect:/tuamadre.html";
		model.addAttribute("album",album);
		return "album.html";
	}
	
	@RequestMapping(value="/salvaAlbum",method = RequestMethod.POST)
	public String newFoto(@Valid @ModelAttribute("foto") Album album, Model model,BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {//in caso non ci siano errori
			this.albumService.salvaAlbum(album); //esegui il persistence
			return "redirect:/album/" + album.getId();
		}
		return "redirect:/index.html";
	}

}
