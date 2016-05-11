package Pizzacato.model;


public class Tilaus implements Comparable<Tilaus>{

	private String tilaus_id;
	private Kayttaja kayttaja;
	private String tilausaika;
	private double hinta;
	private String tila;
	private boolean toimitus;
	private String osoite;
	private String puh;
	private String sahkoposti;
	
	public String getOsoite() {
		return osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}

	public String getPuh() {
		return puh;
	}

	public void setPuh(String puh) {
		this.puh = puh;
	}

	public String getSahkoposti() {
		return sahkoposti;
	}

	public void setSahkoposti(String sahkoposti) {
		this.sahkoposti = sahkoposti;
	}

	public boolean getToimitus() {
		return toimitus;
	}

	public void setToimitus(boolean toimitus) {
		this.toimitus = toimitus;
	}

	public Tilaus(String tilaus_id, Kayttaja kayttaja, String tilausaika, double hinta, String tila, boolean toimitus){
		this.tilaus_id = tilaus_id;
		this.kayttaja = kayttaja;
		this.tilausaika = tilausaika;
		this.hinta = hinta;
		this.tila = tila;
		this.toimitus = toimitus;
		this.osoite = this.kayttaja.getOsoite();
		this.puh = this.kayttaja.getPuh();
		this.sahkoposti = this.kayttaja.getSahkoposti();
	}

	
	public Tilaus(String tilaus_id, String tilausaika, double hinta, String tila, boolean toimitus, String osoite, String sahkoposti, String puh){
		this.tilaus_id = tilaus_id;
		this.kayttaja = null;
		this.tilausaika = tilausaika;
		this.hinta = hinta;
		this.tila = tila;
		this.toimitus = toimitus;
		this.osoite = osoite;
		this.sahkoposti = sahkoposti;
		this.puh = puh;
	}
	

	public Kayttaja getKayttaja() {
		return kayttaja;
	}

	public void setKayttaja(Kayttaja kayttaja) {
		this.kayttaja = kayttaja;
	}

	public String getTilaus_id() {
		return tilaus_id;
	}

	public void setTilaus_id(String tilaus_id) {
		this.tilaus_id = tilaus_id;
	}

	public String getTilausaika() {
		return tilausaika;
	}

	public void setTilausaika(String tilausaika) {
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
	
	public boolean voidaanPeruuttaa(){
		return !this.tila.equals("vastaanotettu");
	}

	@Override
	public int compareTo(Tilaus o) {
		return this.getTilausaika().compareTo(o.getTilausaika());
	}
	
}
