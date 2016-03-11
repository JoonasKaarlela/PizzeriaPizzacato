package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pizzacato.model.Pizza;

public class PizzaDAO extends DataAccessObject{

	
	public ArrayList<Pizza> haePizzat() throws SQLException{
		//HAETAAN KAIKKI PIZZAT
		
		// YHTEYS
		Connection conn = getConnection();
		
		// TYHJ� LISTA PIZZOILLE
		ArrayList<Pizza> pizzat = new ArrayList<>();
		
		// HAKU LAUSE
		String query = "SELECT * FROM PIZZA";
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);
		
		// ITEROI TULOKSET => LUO UUSI PIZZA OLIO =>  LISTAAN...
		while(results.next()){
			String id = results.getString(1);
			String nimi = results.getString(2);
			String taytteet = results.getString(3);
			String kuvaus = results.getString(4);
			boolean listalla = results.getBoolean(5);
			double hinta = results.getDouble(6);
			String kuva = results.getString(7);
			Pizza pizza = new Pizza(id, nimi, taytteet, kuvaus, listalla, hinta, kuva);
			pizzat.add(pizza);
		}
		
		// PALAUTA PIZZAT
		return pizzat;
	}
	

	public void lisaaPizza(Pizza pizza) throws SQLException{
		// LIS�� UUSI PIZZA TIETOKANTAAN.
		
		// YHTEYS
		Connection conn = getConnection();
		
		// LIS�YS LAUSE
		String query = "INSERT INTO PIZZA(pizza_id, nimi, taytteet, kuvaus, listalla, hinta) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, pizza.getPizza_id());
		statement.setString(2, pizza.getNimi());
		statement.setString(3, pizza.getTaytteet());
		statement.setString(4, pizza.getKuvaus());
		statement.setBoolean(5, pizza.getListalla());
		statement.setDouble(6, pizza.getHinta());
		
		// EXECUTE
		int syotettiin = statement.executeUpdate();
		if(syotettiin > 0){
			System.out.println("uusi pizza: " + pizza.getNimi() + " lis�ttiin tietokantaan...");
		}
		
	}
	
	public void poistaPizza(String id) throws SQLException{
		// POISTA KYSEINEN PIZZA TIETOKANNASTA
		
		// YHTEYS
		Connection conn = getConnection();
		
		// POISTO LAUSE
		String query = "DELETE FROM PIZZA WHERE pizza_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);
		
		// EXECUTE
		int poistettiin = statement.executeUpdate();
		if( poistettiin > 0){
			System.out.println("pizza " + id + " poistettiin tietokannasta...");
		}
	}
	
	public void piilotaListalla(String id) throws SQLException{
		// PIILOTA KYSEINEN PIZZA LISTALLA
		
		// YHTEYS
		Connection conn = getConnection();
		
		// PIILOTA LAUSE
		String query = "UPDATE PIZZA SET listalla=false WHERE pizza_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);
		
		//EXECUTE
		int piilotettiin = statement.executeUpdate();
		if(piilotettiin > 0){
			System.out.println("pizza " + id + " piilotettiin listalta");
		}
	}
	
	public void naytaListalla(String id) throws SQLException{
		// N�YT� KYSEINEN PIZZA LISTALLA
		
		// YHTEYS
		Connection conn = getConnection();
		
		// NAYTA LAUSE
		String query = "UPDATE PIZZA SET listalla=true WHERE pizza_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);
		
		// EXECUTE
		int asetettiin_nakyviin = statement.executeUpdate();
		if(asetettiin_nakyviin > 0){
			System.out.println("pizza " + id + " asetettiin n�kyviin listalle");
		}
	}
	
	public void muokkaaPizzaa(Pizza pizza) throws SQLException{
		// TALLENNA UUDET TIEDOT
		
		//YHTEYS
		Connection conn = getConnection();
		
		//PAIVITA LAUSE
		String query = "UPDATE PIZZA SET nimi=?, taytteet=?, kuvaus=?, listalla=?, hinta=? WHERE pizza_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, pizza.getNimi());
		statement.setString(2, pizza.getTaytteet());
		statement.setString(3, pizza.getKuvaus());
		statement.setBoolean(4, pizza.getListalla());
		statement.setDouble(5, pizza.getHinta());
		statement.setString(6, pizza.getPizza_id());
		
		//EXECUTE
		int paivitettiin = statement.executeUpdate();
		if(paivitettiin > 0){
			System.out.println("Paivitettiin: " + paivitettiin + " attribuuttia");
		}
	}
	
	
}
