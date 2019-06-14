package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long>{
	
	List<Album> findByTitolo(String titolo);
}
