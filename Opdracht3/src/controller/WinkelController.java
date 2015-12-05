package controller;

import java.util.List;

import common.enums.EnumTypeCd;
import common.enums.EnumTypeDvd;
import common.enums.EnumTypeGame;
import model.*;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;
//toegevoegd Geert 5 December
//bron http://www.codejava.net/frameworks/spring/14-tips-for-writing-spring-mvc-controller
//Gebruikte keuze: Controller interface en RequestMapping interface aangemaakt
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * gebruikt model: traditionele Model-View-Controller(MVC) model
 * @author 
 *
 */

public class WinkelController implements Controller{

	private Shop currentShop;
	
	public WinkelController() {
		//TODO :: eerste shop uit db gaan halen en ander ctor aanroepen
	}
	
	public WinkelController(Shop shop) {
		this.currentShop = shop;
	}
	
	public void AddItem(Item item) {
		
	}
	
	public List<Item> getAllItemsSorted() {
		//TODO :: lijst van alle items geordend per type en binnen de type alfabetisch geordend
		return null;
	}
	
	//Geert ik heb hier uw search even opgedeeld in 3 verschillende functies
	//searchCd
	//searchDvd
	//searchGames
	//Indien je wilt bouw ik een generic interface over de types heen als je tog 1 functie wilt behouden
	//Maar dan dien je in die ene functie overweg te kunnen met generics.
	
	public List<Cd> searchCd(EnumTypeCd type, String searchString) {
		//TODO:: 5.	ik wil aan de hand van een deel van de titel van een item alle 
		//items van een bepaald type kunnen  opvragen 
		//(als tekst op de console) die hieraan voldoen. (vb alle films met test in de titel)
		return null;
	}
	
	public List<Dvd> searchDvd(EnumTypeDvd type, String searchString) {
		//TODO:: 5.	ik wil aan de hand van een deel van de titel van een item alle 
		//items van een bepaald type kunnen  opvragen 
		//(als tekst op de console) die hieraan voldoen. (vb alle films met test in de titel)
		return null;
	}
	
	public List<Game> searchGames(EnumTypeGame type, String searchString) {
		//TODO:: 5.	ik wil aan de hand van een deel van de titel van een item alle 
		//items van een bepaald type kunnen  opvragen 
		//(als tekst op de console) die hieraan voldoen. (vb alle films met test in de titel)
		return null;
	}
}
