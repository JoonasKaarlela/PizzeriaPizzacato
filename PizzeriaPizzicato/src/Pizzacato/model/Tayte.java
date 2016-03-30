package Pizzacato.model;

public class Tayte {
		
		private String tayte_id;
		private String nimi;
		private String alkupera;
		private String kuvaus;
		private double hinta;
		

public Tayte(String tayte_id, String nimi, String alkupera, String kuvaus, Double hinta) {
	super();
	this.tayte_id = tayte_id;
	this.nimi = nimi;
	this.alkupera = alkupera;
	this.kuvaus = kuvaus;
	this.hinta = hinta;
	
}

public Tayte(){
	tayte_id = "";
	nimi = "";
	alkupera = "";
	kuvaus = "";
	hinta = 0.00;
}

public String getTayte_id() {
	return tayte_id;
}

public void setTayte_id(String tayte_id) {
	this.tayte_id = tayte_id;
}

public String getNimi() {
	return nimi;
}

public void setNimi(String nimi){
	this.nimi = nimi;
}

public String getAlkupera() {
	return alkupera;
}

public void setAlkupera(String alkupera){
	this.alkupera = alkupera;
}

public String getKuvaus () {
	return kuvaus;
}

public void setKuvaus(String kuvaus){
	this.kuvaus = kuvaus;
}

public double getHinta() {
	return hinta;
}
public void setHinta(double hinta){
	this.hinta = hinta;
}

@Override
public String toString() {
	return "Tayte [tayte_id=" + tayte_id + ", ravintosisalto=" + ravintosisalto
			+ "]";
}

}


