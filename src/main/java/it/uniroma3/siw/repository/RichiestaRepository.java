package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.uniroma3.siw.model.Richiesta;

public interface RichiestaRepository extends JpaRepository<Richiesta, Long>{
	
	@Query(value = "SELECT * FROM Richiesta ORDER BY RANDOM() LIMIT ?1", nativeQuery = true)
	List<Richiesta> getRandomRichieste(int limit);
	
	@Query(value = "SELECT r FROM Richiesta r WHERE r.email LIKE %?1% OR r.descrizione LIKE %?1% ORDER BY r.email")
	List<Richiesta> findRichiesteByParametro(String parametro);
	
	@Query(value = "SELECT r.id,r.descrizione,r.email FROM richiesta_foto rf FULL JOIN richiesta r ON r.id = rf.richiesta_id WHERE rf.foto_id = ?1",nativeQuery = true)
	List<Richiesta> findRichiestaByIdFoto(String id);
}
