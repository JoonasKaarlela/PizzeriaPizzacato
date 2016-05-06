package Pizzacato.model;

public class Tayte {
		
		private String tayte_id;
		private String nimi;
		private String alkupera;
		private String kuvaus;
		private double hinta;
		

public Tayte(String tayte_id, String nimi, String alkupera, String kuvaus, Double hinta) {
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
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((tayte_id == null) ? 0 : tayte_id.hashCode());
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
	Tayte other = (Tayte) obj;
	if (tayte_id == null) {
		if (other.tayte_id != null)
			return false;
	} else if (!tayte_id.equals(other.tayte_id))
		return false;
	return true;
}



}


