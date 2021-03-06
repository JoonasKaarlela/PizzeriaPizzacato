package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Pizzacato.model.BCrypt;
import Pizzacato.model.Kayttaja;
import Pizzacato.model.Utils;

public class KayttajaDAO extends DataAccessObject {
	
	/*

*/

	public Kayttaja haeKayttaja(String kayttajatunnus, String salasana) throws SQLException{
		
		// YHTEYS TIETOKANTAAN
		Connection conn = getConnection();
		
		// HAKU LAUSE
		String query = "SELECT * FROM KAYTTAJA WHERE kayttajatunnus=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, kayttajatunnus);
		ResultSet results = statement.executeQuery();
		
		Kayttaja kayttaja = null;
		if(!results.next()){
			System.out.println("KAYTTAJ�� EI L�YTYNYT");
		}else if(BCrypt.checkpw(salasana, results.getString(3))){
			String id = results.getString(1);
			String kayttaja_tunnus = results.getString(2);
			String kayttaja_salasana = results.getString(3);
			String osoite = results.getString(4);
			String sposti = results.getString(5);
			String puh = results.getString(6);
			boolean omistaja = results.getBoolean(7);
			boolean kokki = results.getBoolean(8);
			boolean kuljettaja = results.getBoolean(9);
			
			kayttaja = new Kayttaja(id, kayttaja_tunnus, kayttaja_salasana, osoite, sposti, puh, omistaja, kokki, kuljettaja);
		}
		
		conn.close();
		return kayttaja;
		
	}
	
	public Kayttaja rekisteroidy(String kayttajatunnus, String salasana, String osoite, String sahkoposti, String puh) throws SQLException{
		
		Connection conn = getConnection();
		
		Kayttaja kayttaja = null;
		String id = new Utils().generate(5);
		
		//Tsekkaa onko k�ytt�j� jo olemassa
		String SELECT = "SELECT kayttajatunnus FROM KAYTTAJA WHERE kayttajatunnus=?";
		PreparedStatement select = conn.prepareStatement(SELECT);
		select.setString(1, kayttajatunnus);
		ResultSet results = select.executeQuery();
		
		if(results.next()){
			System.out.println("K�ytt�j� on jo olemassa!");
			return kayttaja;
		}
		
			String hashed = BCrypt.hashpw(salasana, BCrypt.gensalt());
			String INSERT = "INSERT INTO KAYTTAJA(kayttaja_id, kayttajatunnus, salasana, osoite, sahkoposti, puhelinnumero, omistaja, kokki, kuljettaja) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = conn.prepareStatement(INSERT);
			insert.setString(1, id);
			insert.setString(2, kayttajatunnus);
			insert.setString(3, hashed);
			insert.setString(4, osoite);
			insert.setString(5, sahkoposti);
			insert.setString(6, puh);
			insert.setBoolean(7, false);
			insert.setBoolean(8, false);
			insert.setBoolean(9, false);
			
			int updated = insert.executeUpdate();
			if(updated < 1){
				System.out.println("Sinua ei voitu kirjata");
				return kayttaja;
			}else{
				System.out.println("OK!");
				kayttaja = new Kayttaja(id, kayttajatunnus, salasana, osoite, sahkoposti, puh, false);
			}
		conn.close();
		return kayttaja;
	}
	
	
	
}
