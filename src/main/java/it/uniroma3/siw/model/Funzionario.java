package it.uniroma3.siw.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Funzionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	@OneToMany()
	private List<Richiesta> richieste;
	
	/* METODI GETTERS & SETTERS */
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
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
		Funzionario other = (Funzionario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funzionario [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}
	
	/* COSTRUTTORI */
	
	public Funzionario() {
		this.richieste = new LinkedList<Richiesta>();
	}

	public Funzionario(String username, String email, String password, List<Richiesta> richieste) {
		this();
		this.username = username;
		this.email = email;
		this.password = password;
		this.richieste = richieste;
	}

}
