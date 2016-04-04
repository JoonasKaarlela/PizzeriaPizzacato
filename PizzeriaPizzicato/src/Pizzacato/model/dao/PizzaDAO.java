package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pizzacato.model.Pizza;
import Pizzacato.model.Tayte;

public class PizzaDAO extends DataAccessObject{

	
	public ArrayList<Pizza> haePizzat() throws SQLException{
		//HAETAAN KAIKKI PIZZAT
		
		// YHTEYS
		Connection conn = getConnection();
		
		// TYHJï¿½ LISTA PIZZOILLE
		ArrayList<Pizza> pizzat = new ArrayList<>();
		
		// HAKU LAUSE
		String query = "SELECT * FROM PIZZA";
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);
		
		// ITEROI TULOKSET => LUO UUSI PIZZA OLIO =>  LISTAAN...
		while(results.next()){
			String id = results.getString(1);
			String nimi = results.getString(2);
			ArrayList<Tayte> taytteet = new PizzanTayteDAO().haePizzanTaytteet(id);
			String kuvaus = results.getString(3);
			boolean listalla = results.getBoolean(4);
			double hinta = results.getDouble(5);
			String kuva = results.getString(6);
			Pizza pizza = new Pizza(id, nimi, taytteet, kuvaus, listalla, hinta, kuva);
			pizzat.add(pizza);
		}
		
		// PALAUTA PIZZAT
		return pizzat;
	}
	

	public Pizza haePizza(String id) throws SQLException{
		Connection conn = getConnection();
		Pizza pizza = new Pizza();
		
		String query = "SELECT * FROM PIZZA WHERE pizza_id" + id;
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);
		
		while(results.next()){
			String pizza_id = results.getString(1);
			String nimi = results.getString(2);
			ArrayList<Tayte> taytteet = new PizzanTayteDAO().haePizzanTaytteet(pizza_id);
			String kuvaus = results.getString(3);
			boolean listalla = results.getBoolean(4);
			double hinta = results.getDouble(5);
			String kuva = results.getString(6);
			pizza = new Pizza(pizza_id, nimi, taytteet, kuvaus, listalla, hinta, kuva);
		}
		
		return pizza;
	}
	

	public void lisaaPizza(Pizza pizza) throws SQLException{
		// LISATTIIN UUSI PIZZA TIETOKANTAAN.
		
		// YHTEYS
		Connection conn = getConnection();
		
		// LISAYS LAUSE
		String query = "INSERT INTO PIZZA(pizza_id, nimi, kuvaus, listalla, hinta, kuva) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, pizza.getPizza_id());
		statement.setString(2, pizza.getNimi());
		statement.setString(3, pizza.getKuvaus());
		statement.setBoolean(4, pizza.getListalla());
		statement.setDouble(5, pizza.getHinta());
		statement.setString(6, pizza.getKuva());
		
		// EXECUTE
		int syotettiin = statement.executeUpdate();
		if(syotettiin > 0){
			System.out.println("uusi pizza: " + pizza.getNimi() + " lisattiin tietokantaan...");
		}
		
		//  LISAA PIZZANTÄYTE PÖYTÄÄN
			for(Tayte tayte : pizza.getTaytteet()){
				PizzanTayteDAO pizzantaytedao = new PizzanTayteDAO();
				pizzantaytedao.lisaaPizzanTayte(pizza, tayte);
			}
		System.out.println("pizza lisättiin!");
		
	}
	
	public void poistaPizza(String id) throws SQLException{
			
		// POISTA KYSEINEN PIZZA TIETOKANNASTA
		
		// YHTEYS
		Connection conn = getConnection();
		
		// POISTO LAUSE
		String query = "DELETE FROM PIZZA WHERE pizza_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);
		
		
		// TODO: POISTA MYÖS TÄYTTEET TÄLLE PIZZALLE!
		
		
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
		// Nï¿½YTï¿½ KYSEINEN PIZZA LISTALLA
		
		// YHTEYS
		Connection conn = getConnection();
		
		// NAYTA LAUSE
		String query = "UPDATE PIZZA SET listalla=true WHERE pizza_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);
		
		// EXECUTE
		int asetettiin_nakyviin = statement.executeUpdate();
		if(asetettiin_nakyviin > 0){
			System.out.println("pizza " + id + " asetettiin nï¿½kyviin listalle");
		}
	}
	
	public void muokkaaPizzaa(Pizza pizza) throws SQLException{
		// TALLENNA UUDET TIEDOT
		
		//YHTEYS
		Connection conn = getConnection();
		
		//PAIVITA LAUSE
		String query = "UPDATE PIZZA SET nimi=?, kuvaus=?, listalla=?, hinta=? WHERE pizza_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, pizza.getNimi());
		statement.setString(2, pizza.getKuvaus());
		statement.setBoolean(3, pizza.getListalla());
		statement.setDouble(4, pizza.getHinta());
		statement.setString(5, pizza.getPizza_id());
		
		// TODO: PÄIVITÄ MYÖS TÄYTTEET TÄLLE PIZZALLE!
		
		//EXECUTE
		int paivitettiin = statement.executeUpdate();
		if(paivitettiin > 0){
			System.out.println("Paivitettiin: " + paivitettiin + " attribuuttia");
		}
	}
	
	
}
