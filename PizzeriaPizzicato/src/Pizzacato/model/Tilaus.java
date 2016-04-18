package Pizzacato.model;

import java.util.Date;

public class Tilaus {
	private String tilaus_id;
	private Date tilausaika;
	private double hinta;
	private String tila;
	
	public Tilaus(String tilaus_id, Date tilausaika, double hinta, String tila){
		this.tilaus_id = tilaus_id;
		this.tilausaika = tilausaika;
		this.hinta = hinta;
		this.tila = tila;
	}

	public String getTilaus_id() {
		return tilaus_id;
	}

	public void setTilaus_id(String tilaus_id) {
		this.tilaus_id = tilaus_id;
	}

	public Date getTilausaika() {
		return tilausaika;
	}

	public void setTilausaika(Date tilausaika) {
		this.tilausaika = tilausaika;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	public String getTila() {
		return tila;
	}

	public void setTila(String tila) {
		this.tila = tila;
	}
}
