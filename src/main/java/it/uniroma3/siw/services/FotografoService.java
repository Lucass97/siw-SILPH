package it.uniroma3.siw.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Fotografo;
import it.uniroma3.siw.repository.FotografoRepository;

@Service
public class FotografoService {
	
	@Autowired
	private FotografoRepository fotografoRepository;
	
	@Transactional
	public Fotografo salvaFotografo(Fotografo fotografo) {
		return this.fotografoRepository.save(fotografo);
	}
	
	@Transactional
	public List<Fotografo> effettuaRicercaPerParametro(String parametro) {
		return (List<Fotografo>) this.fotografoRepository.ricercaFotografiByParametro(parametro);
	}
	
	@Transactional
	public Fotografo trovaSingoloFotografoPerParametro(String parametro) {
		try {
			Fotografo fotografo = this.fotografoRepository.ricercaFotografiByParametro(parametro).get(0);
			return fotografo;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	@Transactional
	public boolean esisteFotografo(String parametro) {
		List<Fotografo> listaFotografiTrovati = (List<Fotografo>) this.fotografoRepository.ricercaFotografiByParametro(parametro);
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
	
}
