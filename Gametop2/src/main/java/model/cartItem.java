package model;

public class cartItem {
	private final ProdottoBean product;
	private final int quantity;

	public cartItem(ProdottoBean product, int quantity) {
		this.product=product;
		this.quantity=quantity;
	}

	public ProdottoBean getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public double total() {
		return product.getPrezzo() * quantity;
		}
	
}
