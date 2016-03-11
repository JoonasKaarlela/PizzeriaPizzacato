package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pizzacato.model.Tayte;

public class TayteDAO extends DataAccessObject{
	
	public ArrayList<Tayte> haeTaytteet() throws SQLException{
		//HAETAAN KAIKKI TAYTTEET
		
		// YHTEYS
		Connection conn = getConnection();
		
		// TYHJA LISTA TAYTTEILLE
		ArrayList<Tayte> Taytteet = new ArrayList<>();
		
		// HAKU LAUSE
		String query = "SELECT * FROM TAYTE";
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);
		
		// ITEROI TULOKSET LAPI => LUO UUSI TAYTE OLIO => LISAA LISTAAN...
		while(results.next()){
			String tayte_id = results.getString(1);
			String ravintosisalto = results.getString(2);
			Tayte Tayte = new Tayte(tayte_id, ravintosisalto);
			Taytteet.add(Tayte);
		}
		
		// PALAUTA TAYTTEET
		return Taytteet;
	}
	
	public void lisaaTayte(Tayte tayte) throws SQLException{
		// LISAA UUSI TAYTE TIETOKANTAAN.
		
		// YHTEYS
		Connection conn = getConnection();
		
		// LISAYS LAUSE
		String query = "INSERT INTO TAYTE(tayte_id,ravintosisalto) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tayte.getTayte_id());
		statement.setString(2, tayte.getRavintosisalto());

		
		// EXECUTE
		int syotettiin = statement.executeUpdate();
		if(syotettiin > 0){
			System.out.println("uusi tayte: " + tayte.getTayte_id() + " lisattiin tietokantaan...");
		}
	}
		
		public void poistaTayte(String tayte_id) throws SQLException{
			// POISTA KYSEINEN TAYTE TIETOKANNASTA
			
			// YHTEYS
			Connection conn = getConnection();
			
			// POISTO LAUSE
			String query = "DELETE FROM TAYTE WHERE tayte_id=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tayte_id);
			
			// EXECUTE
			int poistettiin = statement.executeUpdate();
			if( poistettiin > 0){
				System.out.println("tayte " + tayte_id + " poistettiin tietokannasta...");
			}
		}
		
		public void muokkaaTaytetta(Tayte tayte) throws SQLException{
			// TALLENNA UUDET TIEDOT
			
			//YHTEYS
			Connection conn = getConnection();
			
			//PAIVITA LAUSE
			String query = "UPDATE TAYTE SET tayte_id=?, ravintosisalto=? WHERE tayte_id=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tayte.getTayte_id());
			statement.setString(2, tayte.getRavintosisalto());
			
			//EXECUTE
			int paivitettiin = statement.executeUpdate();
			if(paivitettiin > 0){
				System.out.println("Paivitettiin: " + paivitettiin + " attribuuttia");
			}
		}
		
		
	}

