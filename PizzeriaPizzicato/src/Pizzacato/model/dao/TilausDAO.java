package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Pizzacato.model.Utils;
import Pizzacato.model.Pizza;

public class TilausDAO extends DataAccessObject {
	
	public void asetaTilaus(ArrayList<Pizza> pizzat) throws SQLException{
		
		Connection conn = getConnection();
		
		double summa = 0;
		for(Pizza pizza : pizzat){
			summa += pizza.getHinta();
		}
		
		String query = "INSERT INTO TILAUS(tilaus_id, tilausaika, hinta, tila) VALUES(?, ?, ?, ?)";
		String id = new Utils().generate(5);
		
		try{
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, id);
			statement.setString(2, new Date().toString());
			statement.setDouble(3, summa);
			statement.setString(4, "tilaus asetettu");
			int syotettiin = statement.executeUpdate();
			if(syotettiin > 0){
				System.out.println("Tilaus " + id + " asetettiin!");
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}

		TilauksenPizzaDAO tilauksenpizzadao = new TilauksenPizzaDAO();
		for(Pizza pizza : pizzat){
			tilauksenpizzadao.luoTilauksenPizza(pizza, id);
		}
		
		conn.close();
	}
	
	public void haeTilauksenPizzat(String id){
		// TODO: HAE TILAUKSEN PIZZAT JOISSA tilauksen_id=id
	}
	
}
