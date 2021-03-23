package it.polito.tdp.spellchecker.model;

public class RichWord {

	private String parola;
	private boolean corretta;
	
	public RichWord(String parola) {
		this.parola = parola;
		corretta=false;
	}
	
	public void setCorretta() {
		corretta=true;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public String getParola() {
		return parola;
	}
	
	
}
