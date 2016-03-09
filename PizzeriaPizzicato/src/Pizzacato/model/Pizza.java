package Pizzacato.model;

public class Pizza implements Comparable<Pizza> {
	
	private String pizza_id;
	private String nimi;
	private String tayte_id;
	private String kuvaus;
	private boolean listalla;
	private double hinta;
	private String kuva;
	
	public Pizza(String pizza_id,String nimi, String tayte_id, String kuvaus,boolean listalla, Double hinta, String kuva){
		this.pizza_id = pizza_id;
		this.nimi = nimi;
		this.tayte_id = tayte_id;
		this.kuvaus = kuvaus;
		this.listalla = listalla;
		this.hinta = hinta;
		this.kuva = kuva;
		
		}
	

	public Pizza(){
		this.pizza_id = "";
		this.nimi = "";
		this.tayte_id = "";
		this.kuvaus = "";
		this.listalla = false;
		this.hinta = 0.00;
		this.kuva = "";
		
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getTayte_id() {
		return tayte_id;
	}

	public void setTayte_id(String tayte_id) {
		this.tayte_id = tayte_id;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public String getPizza_id() {
		return pizza_id;
	}

	public void setPizza_id(String pizza_id) {
		this.pizza_id = pizza_id;
	}
	
	public void setListalla(boolean listalla){
		this.listalla = listalla;
	}
	
	public boolean getListalla(){
		return this.listalla;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}
	
	public String getKuva() {
		return kuva;
	}
	
	public void setKuva(String kuva) {
		this.kuva = kuva;
	}
	
	

	@Override
	public String toString() {
		return "Pizza [pizza_id=" + pizza_id + ", nimi=" + nimi + ", tayte_id="
				+ tayte_id + ", kuvaus=" + kuvaus + ", listalla=" + listalla
				+ ", hinta=" + hinta + ", kuva=" + kuva + "]";
	}


	@Override
	public int compareTo(Pizza pizza) {	
		return Integer.parseInt(this.getPizza_id()) - Integer.parseInt(pizza.getPizza_id());
	}


}
