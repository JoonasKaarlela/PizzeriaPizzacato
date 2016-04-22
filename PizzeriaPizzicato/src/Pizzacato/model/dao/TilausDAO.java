package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		Date tilausaika = null;
		String tila = "";
		
		double hinta = 0;
		for(String key : ostoskori.keySet()){
			for(Pizza pizza : ostoskori.get(key)){
				hinta += pizza.getHinta();
			}
		}
			
		String query = "INSERT INTO TILAUS(tilaus_id, tilausaika, hinta, tila, kayttaja_id) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tilaus_id);
		statement.setDate(2, tilausaika);
		statement.setDouble(3, hinta);
		statement.setString(4, tila);
		statement.setString(5, kayttaja.getKayttaja_id());
			
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

	public void naytaTilaus(String id) {
		// TODO Auto-generated method stub
	}
	
	
	public ArrayList<Tilaus> haeTilaukset(Kayttaja kayttaja){
		
		ArrayList<Tilaus> tilaukset = new ArrayList<>();
		
		Connection conn = getConnection();
		
		
		
		
		return tilaukset;
		
	}
	
	
}
