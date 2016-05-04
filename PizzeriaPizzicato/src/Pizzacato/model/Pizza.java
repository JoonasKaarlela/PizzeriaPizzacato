package Pizzacato.model;

import java.util.ArrayList;

public class Pizza implements Comparable<Pizza> {
	
	private String pizza_id;
	private String nimi;
	private ArrayList<Tayte> taytteet;
	private ArrayList<Mauste> mausteet;
	private String kuvaus;
	private boolean listalla;
	private double hinta;
	private String kuva;
	
	public Pizza(String pizza_id,String nimi, ArrayList<Tayte> taytteet, ArrayList<Mauste> mausteet, String kuvaus,boolean listalla, Double hinta, String kuva){
		this.pizza_id = pizza_id;
		this.nimi = nimi;
		this.taytteet = taytteet;
		this.mausteet = mausteet;
		this.kuvaus = kuvaus;
		this.listalla = listalla;
		this.hinta = hinta;
		this.kuva = kuva;
	}
	
	public Pizza(String pizza_id,String nimi, ArrayList<Tayte> taytteet, String kuvaus,boolean listalla, Double hinta, String kuva){
		this.pizza_id = pizza_id;
		this.nimi = nimi;
		this.taytteet = taytteet;
		this.mausteet = new ArrayList<>();
		this.kuvaus = kuvaus;
		this.listalla = listalla;
		this.hinta = hinta;
		this.kuva = kuva;
	}
	
	public Pizza(String pizza_id,String nimi, String kuvaus,boolean listalla, Double hinta, String kuva){
		this.pizza_id = pizza_id;
		this.nimi = nimi;
		this.taytteet = new ArrayList<>();
		this.mausteet = new ArrayList<>();
		this.kuvaus = kuvaus;
		this.listalla = listalla;
		this.hinta = hinta;
		this.kuva = kuva;
	}
	
	public ArrayList<Mauste> getMausteet() {
		return mausteet;
	}


	public void setMausteet(ArrayList<Mauste> mausteet) {
		this.mausteet = mausteet;
	}


	public Pizza(){
		this.pizza_id = "";
		this.nimi = "";
		this.taytteet = new ArrayList<>();
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

	public ArrayList<Tayte> getTaytteet() {
		return this.taytteet;
	}

	public void setTaytteet(ArrayList<Tayte> taytteet){
		this.taytteet = taytteet;
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
	public int compareTo(Pizza pizza) {	
		return Integer.parseInt(this.getPizza_id()) - Integer.parseInt(pizza.getPizza_id());
	}


}
