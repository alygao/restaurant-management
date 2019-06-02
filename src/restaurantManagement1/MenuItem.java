package restaurantManagement1;

import java.io.Serializable;

import javax.swing.Icon;

public class MenuItem implements Serializable {
	
	private String name;
	private double price;
	private String description;
	private Icon image;
	private String category;
	
	
	public MenuItem(String name, double price, String description, Icon image, String category) {
		this.name = name.toUpperCase();
		this.price = price;
		this.description = description;
		this.image = image;
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.toUpperCase();
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Icon getImage() {
		return image;
	}
	public void setImage(Icon image) {
		this.image = image;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
}
