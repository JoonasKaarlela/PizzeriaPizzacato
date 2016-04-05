package Pizzacato.model.dao;

import Pizzacato.model.Pizza;
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
							+ " AND PIZZANTAYTE.pizza_id=" + pizza_id;
		
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);
		
		while(results.next()){
			String id  = results.getString(1);
			String nimi = results.getString(2);
			String alkupera = results.getString(3);
			String kuvaus = results.getString(4);
			double hinta = results.getDouble(5);
			Tayte tayte = new Tayte(id, nimi, alkupera, kuvaus, hinta);
			taytteet.add(tayte);
		}
		
		return taytteet;
	}
		
		
		public void lisaaPizzanTayte(Pizza pizza, Tayte tayte) throws SQLException{
			// LISAA UUSI TAYTE TIETOKANTAAN.
			
			// YHTEYS
			Connection conn = getConnection();
			
			// LISAYS LAUSE
			String query = "INSERT INTO PIZZANTAYTE(id, tayte_id, pizzan_id) VALUES(?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, ""+Math.random()*1000000);
			statement.setString(2, tayte.getTayte_id());
			statement.setString(3, pizza.getPizza_id());

			// EXECUTE
			int syotettiin = statement.executeUpdate();
			if(syotettiin > 0){
				System.out.println("uusi täyte " + tayte.getNimi() + " lisattiin tietokantaan...");
			}
		}
			
			public void poistaPizzanTayte(Pizza pizza, Tayte tayte) throws SQLException{
				// POISTA KYSEINEN TAYTE TIETOKANNASTA
				
				// YHTEYS
				Connection conn = getConnection();
				
				// POISTO LAUSE
				String query = "DELETE FROM PIZZANTAYTE WHERE pizza_id=? AND tayte_id=?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1, pizza.getPizza_id());
				statement.setString(2, tayte.getTayte_id());
				
				// EXECUTE
				int poistettiin = statement.executeUpdate();
				if( poistettiin > 0){
					System.out.println("tayte "  + tayte.getNimi() + " poistettiin pizzalta " + pizza.getNimi() );
				}
			}
			
			public void muokkaaPizzanTaytetta(Pizza pizza, Tayte tayte) throws SQLException{
				// TALLENNA UUDET TIEDOT
				
				//YHTEYS
				Connection conn = getConnection();
				
				//PAIVITA LAUSE
				String query = "UPDATE TAYTE SET nimi=? alkupera=? kuvaus=? hinta=? WHERE tayte_id=?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1, tayte.getNimi());
				statement.setString(2,  tayte.getAlkupera());
				statement.setString(3, tayte.getKuvaus());
				statement.setDouble(4, tayte.getHinta());
				
				//EXECUTE
				int paivitettiin = statement.executeUpdate();
				if(paivitettiin > 0){
					System.out.println("PizzanTayte paivitettiin: " + paivitettiin + " attribuuttia päivitettiin");
				}
			}
			


	
}
