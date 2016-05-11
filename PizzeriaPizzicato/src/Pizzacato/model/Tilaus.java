package Pizzacato.model;


public class Tilaus implements Comparable<Tilaus>{

	private String tilaus_id;
	private Kayttaja kayttaja;
	private String tilausaika;
	private double hinta;
	private String tila;
	private boolean toimitus;
	
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
	}
	
	public Tilaus(String tilaus_id, String tilausaika, double hinta, String tila, boolean toimitus){
		this.tilaus_id = tilaus_id;
		this.kayttaja = null;
		this.tilausaika = tilausaika;
		this.hinta = hinta;
		this.tila = tila;
		this.toimitus = toimitus;
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
		if(!this.tila.equals("valmis") && !this.tila.equals("toimituksessa") && !this.tila.equals("valmistuksessa")){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int compareTo(Tilaus o) {
		return this.getTilausaika().compareTo(o.getTilausaika());
	}
	
}
