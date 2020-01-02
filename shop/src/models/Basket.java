package models;

public class Basket {
	private int id, good, quantity, price;
	private String sku, name;
	
	public Basket(int id, int good, String sku, String name, int quantity, int price) {
		this.id = id;
		this.good = good;
		this.sku = sku;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	
	public int getGood() {
		return good;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}

	public String getSku() {
		return sku;
	}

	public String getName() {
		return name;
	}
}
