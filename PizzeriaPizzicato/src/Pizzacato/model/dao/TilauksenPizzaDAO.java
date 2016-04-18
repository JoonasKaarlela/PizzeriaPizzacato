package Pizzacato.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Pizzacato.model.Pizza;
import Pizzacato.model.Utils;

public class TilauksenPizzaDAO extends DataAccessObject {

	public void luoTilauksenPizza(Pizza pizza, String tilauksen_id, int lkm) throws SQLException{
		
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
		}
		
		conn.close();
	}
	
}
