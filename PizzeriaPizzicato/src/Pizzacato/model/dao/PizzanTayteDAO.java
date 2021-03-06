package Pizzacato.model.dao;

import Pizzacato.model.Pizza;
import Pizzacato.model.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pizzacato.model.Tayte;

public class PizzanTayteDAO extends DataAccessObject {

	public ArrayList<Tayte> haePizzanTaytteet(String pizza_id) throws SQLException{
		ArrayList<Tayte> taytteet = new ArrayList<>();
		
		Connection conn = getConnection();

		String query = "SELECT * FROM TAYTE"
							+ " INNER JOIN PIZZANTAYTE ON TAYTE.tayte_id=PIZZANTAYTE.tayte_id"
							+ " AND PIZZANTAYTE.pizza_id=?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, pizza_id);
		ResultSet results = statement.executeQuery();
		
		while(results.next()){
			String id  = results.getString(1);
			String nimi = results.getString(2);
			String alkupera = results.getString(3);
			String kuvaus = results.getString(4);
			double hinta = results.getDouble(5);
			Tayte tayte = new Tayte(id, nimi, alkupera, kuvaus, hinta);
			taytteet.add(tayte);
		}
		
		conn.close();
		return taytteet;
	}
		
		
	public boolean lisaaPizzanTayte(Pizza pizza, Tayte tayte) throws SQLException{
		// LISAA UUSI TAYTE TIETOKANTAAN.
		
		// YHTEYS
		Connection conn = getConnection();
		
		// Tsekkaa onko t�yte jo pizzalla.
		String SELECT = "SELECT * FROM PIZZANTAYTE WHERE pizza_id=? AND tayte_id=?";
		PreparedStatement SELECT_statement = conn.prepareStatement(SELECT);
		SELECT_statement.setString(1, pizza.getPizza_id());
		SELECT_statement.setString(2, tayte.getTayte_id());
		ResultSet results = SELECT_statement.executeQuery();
		
		System.out.println("katsotaanko voidaanko lis�t�...");
		
		if(!results.next()){
			System.out.println("T�yte voidaan lis�t�");
			String INSERT = "INSERT INTO PIZZANTAYTE(id, pizza_id, tayte_id) VALUES(?, ?, ?)";
			PreparedStatement INSERT_statement = conn.prepareStatement(INSERT);
			INSERT_statement.setString(1, new Utils().generate(5));
			INSERT_statement.setString(2, pizza.getPizza_id());
			INSERT_statement.setString(3, tayte.getTayte_id());

			// EXECUTE
			int lisattiin = INSERT_statement.executeUpdate();
			if(lisattiin > 0){
				System.out.println("uusi t�yte " + tayte.getNimi() + " lis�ttiin");
			}
		}else{
			conn.close();
			return false;
		}
		
		conn.close();
		return true;
	}
			
	public boolean poistaPizzanTayte(Pizza pizza, Tayte tayte) throws SQLException{
		// POISTA KYSEINEN TAYTE TIETOKANNASTA
				
		// YHTEYS
		Connection conn = getConnection();
		
		// POISTO LAUSE
		System.out.println("poistetaan pizzant�yte");
		String query = "DELETE FROM PIZZANTAYTE WHERE pizza_id=? AND tayte_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, pizza.getPizza_id());
		statement.setString(2, tayte.getTayte_id());
			
		// EXECUTE
		int poistettiin = statement.executeUpdate();
		if( poistettiin > 0){
			System.out.println("tayte "  + tayte.getNimi() + " poistettiin pizzalta " + pizza.getNimi() );
		}else{
			conn.close();
			return false;
		}
		conn.close();
		return true;
	}
			
			

			


	
}
