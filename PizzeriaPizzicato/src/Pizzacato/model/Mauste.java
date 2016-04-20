package Pizzacato.model;

public class Mauste {

	private String mauste_id;
	private String nimi;
	private double hinta;

	public Mauste(String mauste_id, String nimi, Double hinta) {
		this.mauste_id = mauste_id;
		this.nimi = nimi;
		this.hinta = hinta;
	}

	public Mauste() {
		mauste_id = "";
		nimi = "";
		hinta = 0.00;
	}

	public String getMauste_id() {
		return mauste_id;
	}

	public void setMauste_id(String mauste_id) {
		this.mauste_id = mauste_id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

}