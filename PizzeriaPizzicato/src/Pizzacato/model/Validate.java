package Pizzacato.model;

public class Validate {
	
	public boolean teksti(String nimi){
		if(nimi.matches("[a-zA-Z]+") && nimi.length() > 2 && nimi != null){ return true; }
		return false;
	}
	
	public boolean salasana(String salasana){
		if(salasana.length() > 2 && salasana != null){ return true; }
		return false;
	}
	
	public boolean hinta(String hinta){
		if(hinta.matches("[0-9]+([,.][0-9]{1,2})?") && !hinta.isEmpty() && hinta != null && Double.parseDouble(hinta) > 0 && Double.parseDouble(hinta) < 99){ return true; }
		return false;
	}
	
	public boolean sahkoposti(String sahkoposti){
		if(sahkoposti.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")  && !sahkoposti.isEmpty() && sahkoposti != null ){ return true; }
		return false;
	}
	
	public boolean puh(String puh){
		if(puh.matches("[0-9]+") && puh.length() > 9 && puh != null && !puh.isEmpty()){ return true; }
		return false;
	}
	
	public boolean osoite(String osoite){
		if(osoite.length() > 5 && osoite != null && !osoite.isEmpty()){ return true; }
		return false;
	}
	
}
