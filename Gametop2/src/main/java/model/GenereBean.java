package model;

import java.util.List;


public class GenereBean {

	private int id;
	private String title;
	private List<ProdottoBean> products;
	
	public GenereBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ProdottoBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProdottoBean> products) {
		this.products = products;
	}

	
	
	
	
}

