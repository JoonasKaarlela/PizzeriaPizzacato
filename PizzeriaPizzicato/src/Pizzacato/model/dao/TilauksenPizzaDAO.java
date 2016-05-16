package Pizzacato.model.dao;
import Pizzacato.model.Mauste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pizzacato.model.Pizza;
import Pizzacato.model.Tayte;
import Pizzacato.model.Utils;

public class TilauksenPizzaDAO extends DataAccessObject {

	public boolean luoTilauksenPizza(Pizza pizza, String tilauksen_id, int lkm) throws SQLException{
		
		Connection conn = getConnection();
		
		String id = new Utils().generate(5);
		String query = "INSERT INTO TILAUKSENPIZZA(tilauksenpizza_id, tilaus_id, pizza_id, maara) VALUES(?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, id);
		statement.setString(2, tilauksen_id);
		statement.setString(3, pizza.getPizza_id());
		statement.setInt(4, lkm);
	
		int syotettiin = statement.executeUpdate();
		if(syotettiin > 0){
			System.out.println("Tilauksenpizza " + id + " tallennettiin tilaukselle " + tilauksen_id);
			
			for(Mauste mauste : pizza.getMausteet()){
				new PizzanMausteDAO().lisaaPizzanMauste(pizza, mauste);
			}
			
		}else{
			conn.close();
			return false;
		}
		conn.close();
		return true;
	}
	
	public ArrayList<Pizza> HaeTilauksenPizzat(String tilaus_id) throws SQLException{
		Connection conn = getConnection();
		
		ArrayList<Pizza> pizzat = new ArrayList<>();
		
		String query = "SELECT * FROM PIZZA INNER JOIN TILAUKSENPIZZA ON PIZZA.pizza_id=TILAUKSENPIZZA.pizza_id WHERE TILAUKSENPIZZA.tilaus_id=?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tilaus_id);
		ResultSet results = statement.executeQuery();
		
		while(results.next()){
			String pizza_id = results.getString(1);
			String nimi = results.getString(2);
			ArrayList<Tayte> taytteet = new PizzanTayteDAO().haePizzanTaytteet(pizza_id);
			ArrayList<Mauste> mausteet = new PizzanMausteDAO().haePizzanMausteet(pizza_id);
			String kuvaus = results.getString(3);
			boolean listalla = results.getBoolean(4) ;
			double hinta = results.getDouble(5);
			String kuva = "pizza1.png";
			Pizza pizza = new Pizza(pizza_id, nimi, taytteet, mausteet, kuvaus, listalla, hinta, kuva);
			pizzat.add(pizza);
		}
		
		conn.close();
		return pizzat;
	}
	
}
