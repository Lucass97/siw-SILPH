package it.uniroma3.siw.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.repository.FotoRepository;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Transactional
	public Foto salvaFoto(Foto foto) {
		return this.fotoRepository.save(foto);
	}
	
	@Transactional
	public Foto getFotoById(long id) {
		Optional<Foto> optionalFoto = this.fotoRepository.findById(id);
		try {
			return optionalFoto.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Transactional
	public boolean alreadyExists(Foto foto) {
		return this.fotoRepository.existsById(foto.getId());
	}
}
