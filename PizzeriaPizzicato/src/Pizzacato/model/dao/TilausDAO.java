package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Pizzacato.model.Kayttaja;
import Pizzacato.model.Tilaus;
import Pizzacato.model.Utils;
import Pizzacato.model.Pizza;

public class TilausDAO extends DataAccessObject {
	
	public void asetaTilaus(HashMap<String, ArrayList<Pizza>> ostoskori, Kayttaja kayttaja) throws SQLException{
		
		Connection conn = getConnection();
		
		// ASETA TILAUS
		String tilaus_id = new Utils().generate(5);
		String tilausaika = new Utils().getDate();
		String tila = "vastaanotettu";
		
		String kayttaja_id = null;
		if(kayttaja != null){
			kayttaja_id = kayttaja.getKayttaja_id();
		}
		
		double hinta = 0;
		for(String key : ostoskori.keySet()){
			for(Pizza pizza : ostoskori.get(key)){
				hinta += pizza.getHinta();
			}
		}
			
		String query = "INSERT INTO TILAUS(tilaus_id, tilausaika, hinta, tila, kayttaja_id) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tilaus_id);
		statement.setString(2, tilausaika);
		statement.setDouble(3, hinta);
		statement.setString(4, tila);
		statement.setString(5, kayttaja_id);
			
		int syotettiin = statement.executeUpdate();
		if(syotettiin > 0){
			System.out.println("Tilaus " + tilaus_id + " asetettiin!");
		}
		
		// LUO TILAUKSEN PIZZAT
		TilauksenPizzaDAO tilauksenpizzadao = new TilauksenPizzaDAO();
		for(String key : ostoskori.keySet()){
			for(Pizza pizza : ostoskori.get(key)){
				tilauksenpizzadao.luoTilauksenPizza(pizza, tilaus_id, ostoskori.get(key).size());
				break;
			}
		}
		
		conn.close();
	}

	
	public ArrayList<Tilaus> haeTilaukset(Kayttaja kayttaja) throws SQLException{
		
		ArrayList<Tilaus> tilaukset = new ArrayList<>();
		
		Connection conn = getConnection();
		
		String SELECT = "SELECT * FROM TILAUS WHERE kayttaja_id=?";
		PreparedStatement statement = conn.prepareStatement(SELECT);
		statement.setString(1, kayttaja.getKayttaja_id());
		ResultSet results = statement.executeQuery();
		
		while(results.next()){
			String tilaus_id = results.getString(1);
			String tilaus_aika = results.getString(2);
			double hinta = results.getDouble(3);
			String tila = results.getString(4);

			Tilaus tilaus = new Tilaus(tilaus_id, kayttaja, tilaus_aika, hinta, tila);
			tilaukset.add(tilaus);
		}
		
		conn.close();
		return tilaukset;
		
	}
	
	
	public ArrayList<Tilaus> haeTilaukset() throws SQLException{
		
		ArrayList<Tilaus> tilaukset = new ArrayList<>();
		
		Connection conn = getConnection();
		
		String SELECT = "SELECT * FROM TILAUS";
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(SELECT);
		
		while(results.next()){
			String tilaus_id = results.getString(1);
			String tilausaika = results.getString(2);
			double hinta = results.getDouble(3);
			String tila = results.getString(4);
			String kayttaja_id = results.getString(5);
			
			Tilaus tilaus = null;
			if(kayttaja_id == null){
				tilaus = new Tilaus(tilaus_id, tilausaika, hinta, tila);
			}else{
				String KAYTTAJA = "SELECT * FROM KAYTTAJA WHERE kayttaja_id=?";
				PreparedStatement stmt = conn.prepareStatement(KAYTTAJA);
				stmt.setString(1, kayttaja_id);
				ResultSet kayttaja_results = stmt.executeQuery();
				
				if(kayttaja_results.next()){
					String kayttajatunnus = kayttaja_results.getString(2);
					String salasana = kayttaja_results.getString(3);
					String osoite = kayttaja_results.getString(4);
					String sahkoposti = kayttaja_results.getString(5); 
					String puh = kayttaja_results.getString(6);
					boolean omistaja = kayttaja_results.getBoolean(7);
					Kayttaja kayttaja = new Kayttaja(kayttaja_id, kayttajatunnus, salasana, osoite, sahkoposti, puh, omistaja);
					
					tilaus = new Tilaus(tilaus_id, kayttaja, tilausaika, hinta, tila);
	
				}
			}
			tilaukset.add(tilaus);
		}
		
		conn.close();
		return tilaukset;

	}
	
	public void poistaTilaus(String id) throws SQLException{
		
		Connection conn = getConnection();
			
		// TODO: poista TilauksenPizzat
		String query = "DELETE FROM TILAUKSENPIZZA WHERE tilaus_id=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, id);
		int removed = stmt.executeUpdate();
		
		if(removed > 0){
			String DELETE = "DELETE FROM TILAUS WHERE tilaus_id=?";
			PreparedStatement statement = conn.prepareStatement(DELETE);
			statement.setString(1, id);
			
			int poistettiin = statement.executeUpdate();
			if(poistettiin > 1){
				System.out.println("Tilaus " + id + " ja sen pizzat poistettiin");
			}
		}

		conn.close();
		
	}
	
	public void muokkaaTilausta(String id, String tila) throws SQLException{
		
		Connection conn = getConnection();
		
		String UPDATE = "UPDATE TILAUS SET tila=? WHERE tilaus_id=?";
		PreparedStatement statement = conn.prepareStatement(UPDATE);
		statement.setString(1, tila);
		statement.setString(2, id);
		int paivitettiin = statement.executeUpdate();
		
		if(paivitettiin > 0){
			System.out.println("Tilauksen " + id + " on nyt tilassa " + tila);
		}
		
	}
	
	
	
}
