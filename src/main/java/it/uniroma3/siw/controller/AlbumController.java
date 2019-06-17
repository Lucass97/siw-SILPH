package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.form.FotoForm;
import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.services.AlbumService;

@Controller
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping(value = "/cancellaAlbum/{id}")
	public String cancellaAlbum(@PathVariable("id") long id, Model model) {
		Album album = this.albumService.getAlbumById(id);
		if(album == null)
			return "redirect:/";
		this.albumService.deleteAlbumById(id);
		return "redirect:/album";
	}
	
	@GetMapping(value = "/album/{id}")
	public String getAlbum(@PathVariable("id") long id, Model model) {
		Album album = this.albumService.getAlbumById(id);
		if(album == null)
			return "redirect:/tuamadre.html";
		model.addAttribute("album",album);
		model.addAttribute("fotoForm",new FotoForm());
		return "album.html";
	}
	
	@RequestMapping(value = "/album" , method = RequestMethod.GET)
	public String getAlbums(Model model) {
		model.addAttribute("albums",this.albumService.getRandomAlbum(5));
		model.addAttribute("album",new Album());
		return "listaAlbum.html";
	}
	
	@RequestMapping(value="/salvaAlbum",method = RequestMethod.POST)
	public String salvaAlbum(@Valid @ModelAttribute("foto") Album album, Model model,BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {//in caso non ci siano errori
			this.albumService.salvaAlbum(album); //esegui il persistence
			return "redirect:/album/" + album.getId();
		}
		return "redirect:/album";
	}
	
	@PostMapping(value = "/cercaAlbum")
	public String cercaAlbum(Model model, @RequestParam("titolo") String titolo) {
		model.addAttribute("albums",this.albumService.effettuRicercaPerParametro(titolo));
		model.addAttribute("album",new Album());
		return "listaAlbum.html";
	}

}
