package Pizzacato.model;

public class K�ytt�j� {
		private String k�ytt�j�_id;
		private String k�ytt�j�tunnus;
		private String salasana;
		private String osoite;
		private String s�hk�posti;
		private boolean omistaja;


public K�ytt�j�(String k�ytt�j�, String k�ytt�j�tunnus, String salasana, boolean omistaja){
		this.k�ytt�j�_id = k�ytt�j�_id;
		this.k�ytt�j�tunnus = k�ytt�j�tunnus;
		this.salasana = salasana;
		this.osoite = osoite;
		this.s�hk�posti = s�hk�posti;
		this.omistaja = false;
		
}

public K�ytt�j�(){
	super();
	this.k�ytt�j�_id = "";
	this.k�ytt�j�tunnus = "";
	this.salasana = "";
	this.osoite = "";
	this.s�hk�posti = "";
	this.omistaja = false;
}

public String getK�ytt�j�_id() {
	return k�ytt�j�_id;
}

public void setK�ytt�j�_id(String k�ytt�j�_id) {
	this.k�ytt�j�_id = k�ytt�j�_id;
}

public String getK�ytt�j�tunnus() {
	return k�ytt�j�tunnus;
}

public void setK�ytt�j�tunnus(String k�ytt�j�tunnus) {
	this.k�ytt�j�tunnus = k�ytt�j�tunnus;
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

public String getS�hk�posti() {
	return s�hk�posti;
}

public void setS�hk�posti(String s�hk�posti) {
	this.s�hk�posti = s�hk�posti;
}

public boolean isOmistaja() {
	return omistaja;
}

public void setOmistaja(boolean omistaja) {
	this.omistaja = omistaja;
}

@Override
public String toString() {
	return "K�ytt�j� [k�ytt�j�_id=" + k�ytt�j�_id + ", k�ytt�j�tunnus="
			+ k�ytt�j�tunnus + ", salasana=" + salasana + ", osoite=" + osoite
			+ ", s�hk�posti=" + s�hk�posti + ", omistaja=" + omistaja + "]";
}


}
