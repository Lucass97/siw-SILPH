package it.uniroma3.siw.controller;

public class RichiestaForm {
	
	private String email;
	private String descrizione;
	
	/* METODI GETTERS & SETTERS */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/* COSTRUTTORI */
	
	public RichiestaForm() {
		
	}
	
	public RichiestaForm(String email, String descrizione) {
		this();
		this.email = email;
		this.descrizione = descrizione;
	}
		
	
}
