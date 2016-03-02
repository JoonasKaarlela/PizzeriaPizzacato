package Pizzacato.model;

public class Pizza {
	
	private String pizzaId;
	private String nimi;
	private String tayteId;
	private String kuvaus;
	private boolean listalla;
	private double hinta;
	
	public Pizza (String pizzaId,String nimi, String tayteId, String kuvaus,boolean listalla, Double hinta){
		this.pizzaId = pizzaId;
		this.nimi = nimi;
		this.tayteId = tayteId;
		this.kuvaus = kuvaus;
		this.listalla = listalla;
		this.hinta = hinta;
		
		}
	
	public Pizza(){
		this.pizzaId = "";
		this.nimi = "";
		this.tayteId = "";
		this.kuvaus = "";
		this.listalla = false;
		this.hinta = 0.00;
		
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getTayteId() {
		return tayteId;
	}

	public void setTayteId(String tayteId) {
		this.tayteId = tayteId;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public String getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(String pizzaId) {
		this.pizzaId = pizzaId;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	@Override
	public String toString() {
		return "Pizza [pizzaId=" + pizzaId + ", nimi=" + nimi + ", tayteId="
				+ tayteId + ", kuvaus=" + kuvaus + ", hinta=" + hinta + "]";
	}
	
	
	

}
