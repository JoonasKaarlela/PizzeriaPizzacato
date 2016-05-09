package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pizzacato.model.Tayte;

public class TayteDAO extends DataAccessObject{
	
	//HAETAAN KAIKKI TAYTTEET
	public ArrayList<Tayte> haeTaytteet() throws SQLException{
		
		// YHTEYS
		Connection conn = getConnection();
		
		// TYHJA LISTA TAYTTEILLE
		ArrayList<Tayte> taytteet = new ArrayList<>();
		
		// HAKU LAUSE
		String query = "SELECT * FROM TAYTE";
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);
		
		// ITEROI TULOKSET LAPI => LUO UUSI TAYTE OLIO => LISAA LISTAAN...
		while(results.next()){
			String tayte_id = results.getString(1);
			String nimi = results.getString(2);
			String alkupera = results.getString(3);
			String kuvaus = results.getString(4);
			double hinta = results.getDouble(5);
			Tayte tayte = new Tayte(tayte_id, nimi, alkupera, kuvaus, hinta);
			taytteet.add(tayte);
		}
		
		// PALAUTA TAYTTEET
		conn.close();
		return taytteet;
	}
	
	// LISAA UUSI TAYTE TIETOKANTAAN.
	public boolean lisaaTayte(Tayte tayte) throws SQLException{
		
		// YHTEYS
		Connection conn = getConnection();
		
		String SELECT = "SELECT * FROM TAYTE WHERE nimi=?";
		PreparedStatement stmt = conn.prepareStatement(SELECT);
		stmt.setString(1, tayte.getNimi());
		ResultSet results = stmt.executeQuery();
		
		if(!results.next()){
			// LISAYS LAUSE
			String query = "INSERT INTO TAYTE(tayte_id, nimi, alkupera, kuvaus, hinta) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tayte.getTayte_id());
			statement.setString(2, tayte.getNimi());
			statement.setString(3, tayte.getAlkupera());
			statement.setString(4, tayte.getKuvaus());
			statement.setDouble(5, tayte.getHinta());

			// EXECUTE
			int syotettiin = statement.executeUpdate();
			if(syotettiin > 0){
				System.out.println("uusi tayte: " + tayte.getTayte_id() + " lisattiin tietokantaan...");
			}
		} else {
			conn.close();
			return false;
		}
		conn.close();
		return true;
	}
		
	public boolean poistaTayte(String tayte_id) throws SQLException{
			
			Connection conn = getConnection();
			
			String DELETE = "DELETE FROM PIZZANTAYTE WHERE tayte_id=?";
			PreparedStatement stmnt = conn.prepareStatement(DELETE);
			stmnt.setString(1, tayte_id);
			int removed = stmnt.executeUpdate();
			
			if(removed > 0){
				System.out.println("pizzantäytteet poistettiin pizzalta");
			}
			
			String query = "DELETE FROM TAYTE WHERE tayte_id=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tayte_id);
			
			int poistettiin = statement.executeUpdate();
			if(poistettiin > 0){
				System.out.println("tayte " + tayte_id + " poistettiin tietokannasta...");
			}else{
				conn.close();
				return false;
			}
			conn.close();
			return true;
		}
		
		// TALLENNA UUDET TIEDOT
		public boolean muokkaaTaytetta(Tayte tayte) throws SQLException{

			//YHTEYS
			Connection conn = getConnection();
			
			String SELECT = "SELECT * FROM TAYTE WHERE nimi=?";
			PreparedStatement stmt = conn.prepareStatement(SELECT);
			stmt.setString(1, tayte.getNimi());
			ResultSet results = stmt.executeQuery();
			
			if(!results.next()){

				String query = "UPDATE TAYTE SET nimi=?, alkupera=?, kuvaus=?, hinta=? WHERE tayte_id=?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1, tayte.getNimi());
				statement.setString(2, tayte.getAlkupera());
				statement.setString(3,  tayte.getKuvaus());
				statement.setDouble(4,  tayte.getHinta());
				statement.setString(5, tayte.getTayte_id());

				int paivitettiin = statement.executeUpdate();
				if(paivitettiin > 0){
					System.out.println("Paivitettiin: " + paivitettiin + " attribuuttia");
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

