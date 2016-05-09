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
		conn.close();
		return pizzat;
	}
	

	public Pizza haePizza(String id) throws SQLException{
		Connection conn = getConnection();
		Pizza pizza = new Pizza();
		
		String query = "SELECT * FROM PIZZA WHERE pizza_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);
		ResultSet results = statement.executeQuery();
		
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
		
		conn.close();
		return pizza;
	}
	

	public boolean lisaaPizza(Pizza pizza) throws SQLException{
		// LISATTIIN UUSI PIZZA TIETOKANTAAN.
		
		// YHTEYS
		Connection conn = getConnection();
		
		
		// KATO ONKO JO LISÄTTY
		String SELECT = "SELECT * FROM PIZZA WHERE nimi=?";
		PreparedStatement stmnt = conn.prepareStatement(SELECT);
		stmnt.setString(1, pizza.getNimi());
		
		ResultSet results = stmnt.executeQuery();
		if(!results.next()){
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
			
			// LISAA PIZZANTÄYTE PÖYTÄÄN
			for(Tayte tayte : pizza.getTaytteet()){
				PizzanTayteDAO pizzantaytedao = new PizzanTayteDAO();
				pizzantaytedao.lisaaPizzanTayte(pizza, tayte);
			}
		}else{
			conn.close();
			return false;
		}

		conn.close();
		return true;
		
	}
	
	public boolean poistaPizza(String id) throws SQLException{
			
		// POISTA KYSEINEN PIZZA TIETOKANNASTA
		
		// YHTEYS
		Connection conn = getConnection();
		
		// POISTA PIZZANTAYTTEET
		Pizza pizza = haePizza(id);
		PizzanTayteDAO pizzantaytedao =  new PizzanTayteDAO();
		for(Tayte tayte : pizza.getTaytteet()){
			pizzantaytedao.poistaPizzanTayte(pizza, tayte);
		}
		
		// POISTA PIZZA
		String query = "DELETE FROM PIZZA WHERE pizza_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);
		
		// EXECUTE
		int pizzoja_poistettiin = statement.executeUpdate();
		if( pizzoja_poistettiin > 0){
			System.out.println("pizza " + id + " poistettiin tietokannasta...");
		}else{
			conn.close();
			return false;
		}
		conn.close();
		return true;
	}
	
	public boolean muokkaaPizzaa(Pizza pizza) throws SQLException{
		// TALLENNA UUDET TIEDOT
		
		System.out.println("MUOKATAAAN = " + pizza.getNimi());
		
		//YHTEYS
		Connection conn = getConnection();

		String SELECT = "SELECT * FROM PIZZA WHERE nimi=?";
		PreparedStatement stmnt = conn.prepareStatement(SELECT);
		stmnt.setString(1, pizza.getNimi());
				
		ResultSet results = stmnt.executeQuery();
		if(!results.next()){

			String query = "UPDATE PIZZA SET nimi=?, kuvaus=?, listalla=?, hinta=?, kuva=? WHERE pizza_id=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, pizza.getNimi());
			statement.setString(2, pizza.getKuvaus());
			statement.setBoolean(3, pizza.getListalla());
			statement.setDouble(4, pizza.getHinta());
			statement.setString(5, pizza.getKuva());
			statement.setString(6, pizza.getPizza_id());
			
			int paivitettiin = statement.executeUpdate();
			if(paivitettiin > 0){
				System.out.println("PÄIVITYS ONNISTUI!");
				
				PizzanTayteDAO pizzantaytedao = new PizzanTayteDAO();
				
				for(Tayte tayte : pizza.getTaytteet()){
					pizzantaytedao.poistaPizzanTayte(pizza, tayte);
				}
				
				for(Tayte tayte : pizza.getTaytteet()){
					pizzantaytedao.lisaaPizzanTayte(pizza, tayte);
				}
			}else{
				conn.close();
				return false;
			}
		}else{
			conn.close();
			return false;
		}
		
		conn.close();
		return true;
	}
	
	
}
