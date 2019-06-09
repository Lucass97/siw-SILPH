package it.uniroma3.siw.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.repository.FotoRepository;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private AlbumService albumService;
	
	@Transactional
	public Foto salvaFoto(Foto foto,long album_id) {
		Album album = this.albumService.getAlbumById(album_id);
		album.aggiungiSingolaFoto(foto);
		foto.setAlbum(album);
		return this.fotoRepository.save(foto);
	}
	
	@Transactional
	public void deleteFotoById(String id) {
		this.fotoRepository.deleteById(id);
	}
	
	@Transactional
	public Foto getFotoById(String id) {
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
	
	public String generaNomeFile(Foto foto) {
		String fileName = foto.getId();
		String extension = ContentType.contentTypeToExtension(foto.getImageType());
		return fileName.concat(extension);
	}
	
}
