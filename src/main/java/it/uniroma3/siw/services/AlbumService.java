package it.uniroma3.siw.services;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.repository.AlbumRepository;
import it.uniroma3.siw.storage.StorageService;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private FotoService fotoService;
	
	public Album salvaAlbum(Album album) {
		return this.albumRepository.save(album);
	}
	
	public void deleteAlbumById(long id) {
		Album album = this.getAlbumById(id);
		List<Foto> fotos = album.getFoto();
		for(Foto foto: fotos) {
			this.storageService.delete(fotoService.generaNomeFile(foto));
		}
		this.albumRepository.deleteById(id);
	}
	
	public Album getAlbumById(long id) {
		Optional<Album> optionalAlbum = this.albumRepository.findById(id);
		try {
			return optionalAlbum.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public boolean alreadyExists(Album album) {
		return this.albumRepository.existsById(album.getId());
	}
	
	public boolean alreadyExistsById(long id) {
		return this.albumRepository.existsById(id);
	}
	
	public void aggiungiSingolaFotoAdAlbum(Album album, Foto foto) {
		if(foto==null) return;
		if(album==null) return;
		album.aggiungiSingolaFoto(foto);
	}
	
	public void aggiungiFotoAdAlbum(Album album, Collection<Foto> foto) {
		if(foto==null) return;
		if(album==null) return;
		album.aggiungiFoto(foto);
	}
	
}
