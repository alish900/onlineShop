package models;

public class Good {
	private int id, weight, price;
	private String sku, name, description;
	private String image = "images/";
	
	public Good(int id, String sku, String name, int weight, int price, String image, String description) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.weight = weight;
		this.price = price;
		this.image += image;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public int getWeight() {
		return weight;
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

	public String getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return name + ", арт. " + sku + ", вес " + weight + "г, цена " + price + "₽";
	}
	
}
