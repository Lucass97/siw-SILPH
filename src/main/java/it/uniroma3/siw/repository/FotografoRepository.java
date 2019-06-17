package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Fotografo;

public interface FotografoRepository  extends CrudRepository<Fotografo, Long>{
	
	@Query(value = "SELECT * FROM Fotografo ORDER BY RANDOM() LIMIT ?1", nativeQuery = true)
	List<Fotografo> getRandomFotografi(int limit);
	
	@Query(value = "SELECT f FROM Fotografo f WHERE f.nome LIKE %?1% OR f.cognome LIKE %?1% OR f.email LIKE %?1% ORDER BY f.nome")
	List<Fotografo> ricercaFotografiByParametro(String parametro);
}
