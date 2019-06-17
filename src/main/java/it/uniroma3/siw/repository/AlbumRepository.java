package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{
	
	@Query(value = "SELECT * FROM Album ORDER BY RANDOM() LIMIT ?1", nativeQuery = true)
	List<Album> getRandomAlbum(int limit);
	
	@Query(value = "SELECT a FROM Album a WHERE a.titolo LIKE %?1% OR a.descrizione LIKE %?1% ORDER BY a.titolo")
	List<Album> findAlbumByParametro(String parametro);
	
	List<Album> findByTitoloAndDescrizione(String titolo, String descrizione);
}
