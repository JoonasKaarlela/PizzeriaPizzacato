package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Pizzacato.model.Kayttaja;

public class KayttajaDAO extends DataAccessObject {
	

	public Kayttaja haeKayttaja(String kayttajatunnus, String salasana) throws SQLException{
		
		// YHTEYS TIETOKANTAAN
		Connection conn = getConnection();
		
		// HAKU LAUSE
		String query = "SELECT * FROM KAYTTAJA WHERE kayttajatunnus=\"" + kayttajatunnus + "\" AND salasana=\"" + salasana + "\"";
		Statement statement = conn.createStatement();

		ResultSet results = statement.executeQuery(query);
		
		Kayttaja kayttaja = null;
		if(!results.next()){
			System.out.println("KAYTTAJÄÄ EI LÖYTYNYT");
		} else {
			String id = results.getString(1);
			String kayttaja_tunnus = results.getString(2);
			String kayttaja_salasana = results.getString(3);
			String osoite = results.getString(4);
			String sposti = results.getString(5);
			Boolean omistaja = results.getBoolean(6);
			
			kayttaja = new Kayttaja(id, kayttaja_tunnus, kayttaja_salasana, osoite, sposti, omistaja);
		}
		
		conn.close();
		return kayttaja;
		
	}
	
	
	
}
