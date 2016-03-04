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
		
		// TYHJÄ LISTA PIZZOILLE
		ArrayList<Pizza> pizzat = new ArrayList<>();
		
		// HAKU LAUSE
		String query = "SELECT * FROM pizza";
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);
		
		// ITEROI TULOKSET LÄPI => LUO UUSI PIZZA OLIO => LISÄÄ LISTAAN...
		while(results.next()){
			String id = results.getString(1);
			String nimi = results.getString(2);
			String tayte_id = results.getString(3);
			String kuvaus = results.getString(4);
			boolean listalla = results.getBoolean(5);
			double hinta = results.getDouble(6);
			Pizza pizza = new Pizza(id, nimi, tayte_id, kuvaus, listalla, hinta);
			pizzat.add(pizza);
		}
		
		// PALAUTA PIZZAT
		return pizzat;
	}
	

	public void lisaaPizza(Pizza pizza) throws SQLException{
		// LISÄÄ UUSI PIZZA TIETOKANTAAN.
		
		// YHTEYS
		Connection conn = getConnection();
		
		// LISÄYS LAUSE
		String query = "INSERT INTO pizza(pizzaId, nimi, tayteId, kuvaus, listalla, hinta) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, pizza.getPizzaId());
		statement.setString(2, pizza.getNimi());
		statement.setString(3, pizza.getTayteId());
		statement.setString(4, pizza.getKuvaus());
		statement.setBoolean(5, pizza.getListalla());
		statement.setDouble(6, pizza.getHinta());
		
		// EXECUTE
		int syotettiin = statement.executeUpdate();
		if(syotettiin > 0){
			System.out.println("uusi pizza: " + pizza.getNimi() + " lisättiin tietokantaan...");
		}
		
	}
	
	public void poistaPizza(String id) throws SQLException{
		// POISTA KYSEINEN PIZZA TIETOKANNASTA
		
		// YHTEYS
		Connection conn = getConnection();
		
		// POISTO LAUSE
		String query = "DELETE FROM pizza WHERE pizzaId=?";
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
		String query = "UPDATE pizza SET listalla=False WHERE pizzaId=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);
		
		//EXECUTE
		int piilotettiin = statement.executeUpdate();
		if(piilotettiin > 0){
			System.out.println("pizza " + id + " piilotettiin listalta");
		}
	}
	
	public void naytaListalla(String id) throws SQLException{
		// NÄYTÄ KYSEINEN PIZZA LISTALLA
		
		// YHTEYS
		Connection conn = getConnection();
		
		// NAYTA LAUSE
		String query = "UPDATE pizza SET listalla=True WHERE pizzaId=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);
		
		// EXECUTE
		int asetettiin_nakyviin = statement.executeUpdate();
		if(asetettiin_nakyviin > 0){
			System.out.println("pizza " + id + " asetettiin näkyviin listalle");
		}
	}
	
	
}
