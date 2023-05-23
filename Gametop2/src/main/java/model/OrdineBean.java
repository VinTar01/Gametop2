package model;

import java.time.LocalDate;
import java.util.List;


public class OrdineBean {
	private int id;
	private double totale;
	private UtenteBean utente;
	private Cart cart;
	private LocalDate localDate;
	private List<ProdottoBean> products;  //per n ad n con Prodotto
	
	public OrdineBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public UtenteBean getUtente() {
		return utente;
	}

	public void setUtente(UtenteBean utente) {
		this.utente = utente;
	}
	
	

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public List<ProdottoBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProdottoBean> products) {
		this.products = products;
	}


	
	
}


