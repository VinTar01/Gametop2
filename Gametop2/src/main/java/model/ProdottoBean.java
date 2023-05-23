package model;

import java.util.List;

public class ProdottoBean {

	private int id;
	private double prezzo;
	private String titolo, cover;
	private AziendaBean company;
	private GenereBean category;
	private List<OrdineBean> purchases;  //per n ad n con Ordine
	
	

	public ProdottoBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public AziendaBean getCompany() {
		return company;
	}

	public void setCompany(AziendaBean company) {
		this.company = company;
	}

	public GenereBean getCategory() {
		return category;
	}

	public void setCategory(GenereBean category) {
		this.category = category;
	}
	
	
	public List<OrdineBean> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<OrdineBean> purchases) {
		this.purchases = purchases;
	}


}


