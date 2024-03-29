package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Fotografo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	private String cognome;
	
	private LocalDate dataNascita;
	
	private String email;
	
	@OneToMany(mappedBy = "fotografo", cascade = CascadeType.REMOVE)
	private List<Foto> foto;

	/* METODI GETTERS & SETTERS */

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}
	
	public List<Foto> getFoto() {
		return foto;
	}

	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}
	
	/* METODI DI SERVIZIO */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fotografo other = (Fotografo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fotografo [id=" + id + ", nome=" + nome + "]";
	}
	
	/* METODI */
	
	public boolean aggiungiSingolaFoto(Foto foto) {
		return this.foto.add(foto);
	}
	
	public boolean aggiungiFoto(Collection<Foto> foto) {
		return this.foto.addAll(foto);
	}
	
	/* COSTRUTTORI */
	
	public Fotografo() {
		this.foto = new LinkedList<Foto>();
		this.dataNascita = LocalDate.now();
	}

	public Fotografo(String nome, String cognome, LocalDate dataNascita, String email) {
		this();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
	}
	
	
	
	

}
