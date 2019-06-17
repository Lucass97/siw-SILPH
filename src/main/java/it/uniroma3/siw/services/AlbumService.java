package it.uniroma3.siw.services;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.repository.AlbumRepository;
import it.uniroma3.siw.services.storage.StorageService;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private RichiestaService richiestaService;
	
	@Transactional
	public Album salvaAlbum(Album album) {
		return this.albumRepository.save(album);
	}
	
	@Transactional
	public void deleteAlbumById(long id) {
		Album album = this.getAlbumById(id);
		List<Foto> fotos = album.getFoto();
		for(Foto foto: fotos) {
			this.storageService.delete(fotoService.generaNomeFile(foto));
			this.richiestaService.deleteFotoDaTutteLeRichieste(foto);
		}
		this.albumRepository.deleteById(id);
	}
	
	@Transactional
	public Album getAlbumById(long id) {
		Optional<Album> optionalAlbum = this.albumRepository.findById(id);
		try {
			return optionalAlbum.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Transactional
	public List<Album> getRandomAlbum(int limit) {
		return this.albumRepository.getRandomAlbum(limit);
	}
	
	@Transactional
	public List<Album> tutti() {
		return (List<Album>) this.albumRepository.findAll();
	}
	
	@Transactional
	public List<Album> effettuRicercaPerParametro(String parametro) {
		return (List<Album>) this.albumRepository.findAlbumByParametro(parametro);
	}
	
	
	@Transactional
	public boolean alreadyExists(Album album) {	
		try {
			this.albumRepository.findByTitoloAndDescrizione(album.getTitolo(), album.getDescrizione()).get(0);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}
	
	@Transactional
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
