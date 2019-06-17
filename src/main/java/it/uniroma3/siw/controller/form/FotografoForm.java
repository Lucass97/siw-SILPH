package it.uniroma3.siw.controller.form;

public class FotografoForm {
	
	private String nome;
	private String cognome;
	private String email;
	private String dataNascita;
	
	/* METTDI GETTERS & SETTERS */
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	/* METODI DI SERVIZIO */
	
	@Override
	public String toString() {
		return "FotografoForm [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", dataNascita="
				+ dataNascita + "]";
	}
	
	/* COSTRUTTORI */
	
	public FotografoForm() {
		
	}
	public FotografoForm(String nome, String cognome, String email, String dataNascita) {
		this();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataNascita = dataNascita;
	}

}
