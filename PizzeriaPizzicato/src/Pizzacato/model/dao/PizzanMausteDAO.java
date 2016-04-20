package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pizzacato.model.Pizza;

public class PizzanMausteDAO extends DataAccessObject {

	public Arraylist<Mauste> haePizzanMausteet(String pizza_id)
			throws SQLException {
		ArrayList<Mauste> mausteet = new ArrayList<>();

		Connection conn = getConnection();

		String query = "SELECT * FROM MAUSTE"
				+ " INNER JOIN PIZZANMAUSTE ON MAUSTE.mauste.id=PIZZANMAUSTE.mauste_id"
				+ " AND PIZZANMAUSTE.mauste.id=" + pizza_id;

		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);

		while (results.next()) {
			String id = results.getString(1);
			String nimi = results.getString(2);
			Mauste mauste = new Mauste(id, nimi, hinta);

		}

		conn.close();
		return mausteet;
	}

	public void lisaaPizzanMauste(Pizza pizza, Mauste mauste)
			throws SQLExeption {

		Connection conn = getConnection();

		String query = "INSERT INTO PIZZANMAUSTE(id, pizza_id, mauste_id) VALUES(?, ?, ?,)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, new Utils().generate(5));
		statement.setString(2, pizza.getPizza_id());
		statement.setString(3, mauste.getMauste_id());

		int syotettiin = statement.executeUpdate();
		if (syotettiin > 0) {
			System.out.println("uusi mauste " + mauste.getNimi()
					+ " lisättiin tietokantaan...");
		}
		conn.close();
	}

	public void poistaPizzanMauste(Pizza pizza, Mauste mauste)
			throws SQLException {

		Connection conn = getConnection();

		String query = "DELETE FROM PIZZANMAUSTE WHERE pizza_id=? AND mauste_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, pizza.getPizza_id());
		statement.setString(2, mauste.getMauste_id());

		int poistettiin = statement.executeUpdate();
		if (poistettiin > 0) {
			System.out.println("mauste " + mauste.getNimi()
					+ " poistettiin pizzalta" + pizza.getNimi());
		}
		conn.close();
	}
}
