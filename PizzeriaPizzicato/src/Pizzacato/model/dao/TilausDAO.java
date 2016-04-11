package Pizzacato.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import Pizzacato.model.Pizza;

public class TilausDAO extends DataAccessObject {
	
	public void asetaTilaus(ArrayList<Pizza> pizzat){
		
		/*	TODO:
		 *   
		 *  1. LUO UUSI TILAUS KANTAAN
		 *  
		 *  2. LUO UUSI TILAUKSENPIZZA JOKAISESTA pizzat LISTAN pizza oliosta JUURI LUODUN TILAUKSEN tilauksen_id:llä
		 *  
		 */
		
	}
	
	public void haeTilauksenPizzat(String id){
		// TODO: HAE TILAUKSEN PIZZAT JOISSA tilauksen_id=id
	}
	
}
