package Pizzacato.model;

public class Käyttäjä {
		private String käyttäjä_id;
		private String käyttäjätunnus;
		private String salasana;
		private String osoite;
		private String sähköposti;
		private boolean omistaja;


public Käyttäjä(String käyttäjä, String käyttäjätunnus, String salasana, boolean omistaja){
		this.käyttäjä_id = käyttäjä_id;
		this.käyttäjätunnus = käyttäjätunnus;
		this.salasana = salasana;
		this.osoite = osoite;
		this.sähköposti = sähköposti;
		this.omistaja = false;
		
}

public Käyttäjä(){
	super();
	this.käyttäjä_id = "";
	this.käyttäjätunnus = "";
	this.salasana = "";
	this.osoite = "";
	this.sähköposti = "";
	this.omistaja = false;
}

public String getKäyttäjä_id() {
	return käyttäjä_id;
}

public void setKäyttäjä_id(String käyttäjä_id) {
	this.käyttäjä_id = käyttäjä_id;
}

public String getKäyttäjätunnus() {
	return käyttäjätunnus;
}

public void setKäyttäjätunnus(String käyttäjätunnus) {
	this.käyttäjätunnus = käyttäjätunnus;
}

public String getSalasana() {
	return salasana;
}

public void setSalasana(String salasana) {
	this.salasana = salasana;
}

public String getOsoite() {
	return osoite;
}

public void setOsoite(String osoite) {
	this.osoite = osoite;
}

public String getSähköposti() {
	return sähköposti;
}

public void setSähköposti(String sähköposti) {
	this.sähköposti = sähköposti;
}

public boolean isOmistaja() {
	return omistaja;
}

public void setOmistaja(boolean omistaja) {
	this.omistaja = omistaja;
}

@Override
public String toString() {
	return "Käyttäjä [käyttäjä_id=" + käyttäjä_id + ", käyttäjätunnus="
			+ käyttäjätunnus + ", salasana=" + salasana + ", osoite=" + osoite
			+ ", sähköposti=" + sähköposti + ", omistaja=" + omistaja + "]";
}


}
