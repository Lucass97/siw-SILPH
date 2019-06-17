package it.uniroma3.siw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.ResponseBody;

import it.uniroma3.siw.controller.form.FotoForm;
import it.uniroma3.siw.controller.response.FotoSelezionataResponse;
import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.services.AlbumService;
import it.uniroma3.siw.services.FotoService;
import it.uniroma3.siw.services.FotoValidator;
import it.uniroma3.siw.services.storage.StorageService;

@Controller
public class FotoController {

	@Autowired
	private StorageService storageService;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private FotoValidator fotoValidator;

	@RequestMapping("/inserisciFoto")
	public String addFoto(Model model) {
		model.addAttribute("fotoForm",new FotoForm());
		return "fotoForm.html";
	}

	@RequestMapping(value = "/foto/{id}" , method = RequestMethod.GET)
	public String getFoto(@PathVariable("id") String id, Model model) {
		Foto foto = this.fotoService.getFotoById(id);
		if(foto == null)
			return "redirect:/tuamadre.html";
		model.addAttribute("foto",foto);
		return "foto.html";
	}

	@RequestMapping(value = "/cancellaFoto/{id}" , method = RequestMethod.GET)
	public String deleteFoto(@PathVariable("id") String id, Model model) {
		Foto foto = this.fotoService.getFotoById(id);
		if(foto == null)
			return "redirect:/index.html";
		Album album = foto.getAlbum();
		this.storageService.delete(this.fotoService.generaNomeFile(foto));
		this.fotoService.deleteFotoById(id);
		return "redirect:/album/" + album.getId();
	}

	@PostMapping(value="/album/{id}/salvaFoto")
	public String salvaFoto(@PathVariable("id") Long id, @Valid @ModelAttribute("fotoForm") FotoForm fotoForm,
			Model model,BindingResult bindingResult) {
		this.fotoValidator.validate(fotoForm, bindingResult);
		if(!bindingResult.hasErrors()) {//in caso non ci siano errori
			Foto foto = new Foto();
			foto.setNome(fotoForm.getNome());
			foto.setDescrizione(fotoForm.getDescrizione());
			foto.setImageType(fotoForm.getFileImage().getContentType());
			this.fotoService.salvaFoto(foto,id,fotoForm.getParametroFotografo()); //esegui il persistence
			foto.setFilePath(fotoService.generaPath(foto));
			this.fotoService.salvaFoto(foto,id,fotoForm.getParametroFotografo());
			this.storageService.store(fotoForm.getFileImage(),this.fotoService.generaNomeFile(foto)); //salva immagine
			return "redirect:/album/" + id;
		}
		model.addAttribute("album",this.albumService.getAlbumById(id));
		return "album.html";
	}

	@GetMapping(value = "/selezionaFoto/{id}", headers="Accept=application/json")
	public @ResponseBody FotoSelezionataResponse selezionaFoto(@PathVariable("id") String id , HttpServletRequest request, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<String, Foto> fotoSelezionate = (Map<String, Foto>) session.getAttribute("fotoSelezionate");

		if(fotoSelezionate==null)
			fotoSelezionate = new HashMap<String,Foto>();

		Foto foto = fotoService.getFotoById(id);
		if(foto != null)
			fotoSelezionate.put(foto.getId(),foto);

		session.setAttribute("fotoSelezionate", fotoSelezionate);

		FotoSelezionataResponse fotoSelezionataResponse = new FotoSelezionataResponse(foto);
		return fotoSelezionataResponse;
	}
	
	@GetMapping(value = "/deselezionaFoto/{id}", headers="Accept=application/json")
	public @ResponseBody FotoSelezionataResponse deselezionaFoto(@PathVariable("id") String id , HttpServletRequest request, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<String, Foto> fotoSelezionate = (Map<String, Foto>) session.getAttribute("fotoSelezionate");

		if(fotoSelezionate==null)
			fotoSelezionate = new HashMap<String,Foto>();

		Foto foto = fotoService.getFotoById(id);
		if(foto != null)
			fotoSelezionate.remove(foto.getId());

		session.setAttribute("fotoSelezionate", fotoSelezionate);

		FotoSelezionataResponse fotoSelezionataResponse = new FotoSelezionataResponse(foto);
		return fotoSelezionataResponse;
	}

	@GetMapping(value = "/getFotoSelezionate", headers="Accept=application/json")
	public @ResponseBody Map<String, FotoSelezionataResponse> getFotoSelezionate(HttpServletRequest request, HttpSession session) {

		@SuppressWarnings("unchecked")
		Map<String, Foto> fotoSelezionate = (Map<String, Foto>) session.getAttribute("fotoSelezionate");

		if(fotoSelezionate==null)
			fotoSelezionate = new HashMap<String,Foto>();

		/* preparazione risposta */
		Map<String, FotoSelezionataResponse> fotoSelezionateResponse = new HashMap<String,FotoSelezionataResponse>();
		for(Foto fotoCorrente: fotoSelezionate.values()) {
			FotoSelezionataResponse fotoSelezionataResponse = new FotoSelezionataResponse(fotoCorrente);
			fotoSelezionateResponse.put(fotoSelezionataResponse.getId(), fotoSelezionataResponse);
		}
		return fotoSelezionateResponse;
	}
}
