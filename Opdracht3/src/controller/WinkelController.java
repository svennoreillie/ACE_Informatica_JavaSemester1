package controller;

import java.util.List;

import model.*;

public class WinkelController {

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
	
	public List<Item> searchItems(ItemType type, String searchString) {
		//TODO:: 5.	ik wil aan de hand van een deel van de titel van een item alle 
		//items van een bepaald type kunnen  opvragen 
		//(als tekst op de console) die hieraan voldoen. (vb alle films met “love” in de titel)
		return null;
	}
}
