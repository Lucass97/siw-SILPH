package it.uniroma3.siw.services;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.repository.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	public Album salvaAlbum(Album album) {
		return this.albumRepository.save(album);
	}
	
	public Album getAlbumById(long id) {
		Optional<Album> optionalAlbum = this.albumRepository.findById(id);
		try {
			return optionalAlbum.get();
		} catch (NoSuchElementException e) {
			return null;
		}
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
