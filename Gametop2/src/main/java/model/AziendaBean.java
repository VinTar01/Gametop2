package model;

import java.util.List;

public class AziendaBean {
	private int id;
	private String name;
	private List<ProdottoBean> products;
	
	public AziendaBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProdottoBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProdottoBean> products) {
		this.products = products;
	}


}

