package Pizzacato.model;

public class Kayttaja {
		private String kayttaja_id;
		private String kayttajatunnus;
		private String salasana;
		private String osoite;
		private String sahkoposti;
		private boolean omistaja;


public Kayttaja(String kayttaja, String kayttajatunnus, String salasana, boolean omistaja){
		this.kayttaja_id = kayttaja_id;
		this.kayttajatunnus = kayttajatunnus;
		this.salasana = salasana;
		this.osoite = osoite;
		this.sahkoposti = sahkoposti;
		this.omistaja = false;
		
}

public Kayttaja(){
	super();
	this.kayttaja_id = "";
	this.kayttajatunnus = "";
	this.salasana = "";
	this.osoite = "";
	this.sahkoposti = "";
	this.omistaja = false;
}

public String getKayttaja_id() {
	return kayttaja_id;
}

public void setKayttaja_id(String kayttaja_id) {
	this.kayttaja_id = kayttaja_id;
}

public String getKayttajatunnus() {
	return kayttajatunnus;
}

public void setKayttajatunnus(String kayttajatunnus) {
	this.kayttajatunnus = kayttajatunnus;
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

public String getSahkoposti() {
	return sahkoposti;
}

public void setSahkoposti(String sahkoposti) {
	this.sahkoposti = sahkoposti;
}

public boolean isOmistaja() {
	return omistaja;
}

public void setOmistaja(boolean omistaja) {
	this.omistaja = omistaja;
}

@Override
public String toString() {
	return "Kayttaja [kayttaja_id=" + kayttaja_id + ", kayttajatunnus="
			+ kayttajatunnus + ", salasana=" + salasana + ", osoite=" + osoite
			+ ", sahkoposti=" + sahkoposti + ", omistaja=" + omistaja + "]";
}


}
