package Pizzacato.model;

public class Validate {
	
	public boolean teksti(String nimi){
		if(nimi.matches("[a-zA-Z]+") && nimi.length() > 2 && nimi != null){ return true; }
		System.out.println("invalid text");
		return false;
	}
	
	public boolean salasana(String salasana){
		if(salasana.length() > 2 && salasana != null){ return true; }
		System.out.println("invalid password");
		return false;
	}
	
	public boolean hinta(String hinta){
		if(hinta.matches("[0-9]+([,.][0-9]{1,2})?") && !hinta.isEmpty() && hinta != null && Double.parseDouble(hinta) > 0 && Double.parseDouble(hinta) < 99){ return true; }
		System.out.println("invalid price");
		return false;
	}
	
	public boolean sahkoposti(String sahkoposti){
		if(!sahkoposti.isEmpty() && sahkoposti != null && sahkoposti.length() > 5){ return true; }
		System.out.println("invalid email");
		return false;
	}
	
	public boolean puh(String puh){
		if(puh.matches("[0-9]+") && puh.length() > 9 && puh != null && !puh.isEmpty()){ return true; }
		System.out.println("invalid phone");
		return false;
	}
	
	public boolean osoite(String osoite){
		if(osoite.length() > 5 && osoite != null && !osoite.isEmpty()){ return true; }
		System.out.println("invalid address");
		return false;
	}
	
}
