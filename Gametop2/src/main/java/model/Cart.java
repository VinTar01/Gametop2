package model;

import java.util.*;


public class Cart {

	private List<cartItem> items;
	
	public Cart(List<cartItem> items) {
		this.items=items;
		
	}
	
	public double total() {
		double total=0.0;
		for(cartItem item : items) {
			total+= item.total();
		}
		return total;
	}

	public List<cartItem> getItems() {
		return items;
	}

	public void setItems(List<cartItem> items) {
		this.items = items;
	}
	
	public boolean addProduct(ProdottoBean product, int quantity) {
		return items.add(new cartItem(product, quantity));
	}
		
}
