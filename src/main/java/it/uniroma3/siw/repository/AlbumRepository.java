package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long>{
	
}
