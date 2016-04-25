package Pizzacato.model.dao;

import Pizzacato.model.Mauste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MausteDAO extends DataAccessObject {

	// HAETAAN KAIKKI MAUSTEET
	public ArrayList<Mauste> haeMausteet() throws SQLException {

		// YHTEYS
		Connection conn = getConnection();

		// TYHJA LISTA MAUSTEILLE
		ArrayList<Mauste> mausteet = new ArrayList<>();

		// HAKU LAUSE
		String query = "SELECT * FROM MAUSTE";
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);

		// ITEROI TULOKSET LAPI => LUO UUSI MAUSTE OLIO => LISAA LISTAAN...
		while (results.next()) {
			String mauste_id = results.getString(1);
			String nimi = results.getString(2);
			double hinta = results.getDouble(3);
			Mauste mauste = new Mauste(mauste_id, nimi, hinta);
			mausteet.add(mauste);
		}

		// PALAUTA MAUSTEET
		conn.close();
		return mausteet;
	}
	
	
	public Mauste haeMauste(String id) throws SQLException{
		
		Connection conn = getConnection();
		
		Mauste mauste = null;
		String SELECT = "SELECT * FROM MAUSTE WHERE mauste_id=?";
		PreparedStatement statement = conn.prepareStatement(SELECT);
		statement.setString(1, id);
		ResultSet results = statement.executeQuery(); 
				
		if(results.next()){
			String mauste_id = results.getString(1);
			String nimi = results.getString(2);
			double hinta = results.getDouble(3);
			mauste = new Mauste(mauste_id, nimi, hinta);
		}
		
		return mauste;
	}

	// LISAA UUSI MAUSTE TIETOKANTAAN.
	public void lisaaMauste(Mauste mauste) throws SQLException {

		// YHTEYS
		Connection conn = getConnection();

		// LISAYS LAUSE
		String query = "INSERT INTO MAUSTE(mauste_id, nimi) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, mauste.getMauste_id());
		statement.setString(2, mauste.getNimi());
		;
		statement.setDouble(5, mauste.getHinta());

		// EXECUTE
		int syotettiin = statement.executeUpdate();
		if (syotettiin > 0) {
			System.out.println("uusi mauste: " + mauste.getMauste_id()
					+ " lisattiin tietokantaan...");
		}
		conn.close();
	}

	// POISTA KYSEINEN MAUSTE TIETOKANNASTA
	public void poistaMauste(String mauste_id) throws SQLException {

		// YHTEYS
		Connection conn = getConnection();

		// POISTO LAUSE
		String query = "DELETE FROM MAUSTE WHERE tayte_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, mauste_id);

		// EXECUTE
		int poistettiin = statement.executeUpdate();
		if (poistettiin > 0) {
			System.out.println("mauste " + mauste_id
					+ " poistettiin tietokannasta...");
		}
		conn.close();
	}

	// TALLENNA UUDET TIEDOT
	public void muokkaaMaustetta(Mauste mauste) throws SQLException {

		// YHTEYS
		Connection conn = getConnection();

		// PAIVITA LAUSE
		String query = "UPDATE MAUSTE SET nimi=?, hinta=? WHERE mauste_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, mauste.getNimi());
		;
		statement.setDouble(4, mauste.getHinta());
		statement.setString(5, mauste.getMauste_id());

		// EXECUTE
		int paivitettiin = statement.executeUpdate();
		if (paivitettiin > 0) {
			System.out.println("Paivitettiin: " + paivitettiin
					+ " attribuuttia");
		}
		conn.close();
	}

}
