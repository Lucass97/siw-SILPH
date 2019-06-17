package it.uniroma3.siw.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.model.Fotografo;
import it.uniroma3.siw.repository.FotografoRepository;
import it.uniroma3.siw.services.storage.StorageService;

@Service
public class FotografoService {
	
	@Autowired
	private FotografoRepository fotografoRepository;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired 
	private FotoService fotoService;
	
	@Autowired
	private RichiestaService richiestaService;
	
	
	@Transactional
	public Fotografo salvaFotografo(Fotografo fotografo) {
		return this.fotografoRepository.save(fotografo);
	}
	
	@Transactional
	public List<Fotografo> effettuaRicercaPerParametro(String parametro) {
		return (List<Fotografo>) this.fotografoRepository.findFotografiByParametro(parametro);
	}
	
	@Transactional
	public Fotografo trovaSingoloFotografoPerParametro(String parametro) {
		try {
			Fotografo fotografo = this.fotografoRepository.findFotografiByParametro(parametro).get(0);
			return fotografo;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	@Transactional
	public boolean esisteFotografo(String parametro) {
		List<Fotografo> listaFotografiTrovati = (List<Fotografo>) this.fotografoRepository.findFotografiByParametro(parametro);
		return !listaFotografiTrovati.isEmpty();
	}
	
	@Transactional
	public Fotografo getFotografoById(long id) {
		Optional<Fotografo> optionalFotografo = this.fotografoRepository.findById(id);
		try {
			return optionalFotografo.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Transactional
	public List<Fotografo> tutti() {
		return (List<Fotografo>) this.fotografoRepository.findAll();
	}
	
	@Transactional
	public List<Fotografo> getRandomFotografi(int limit) {
		return (List<Fotografo>) this.fotografoRepository.getRandomFotografi(limit);
	}
	
	@Transactional
	public void deleteFotografoById(long id) {
		Fotografo fotografo = this.getFotografoById(id);
		for(Foto foto : fotografo.getFoto()) {
			this.richiestaService.deleteFotoDaTutteLeRichieste(foto);
			this.storageService.delete(this.fotoService.generaNomeFile(foto));
			this.fotoService.deleteFotoById(foto.getId());
		}
		this.fotografoRepository.deleteById(id);
	}
	
}
