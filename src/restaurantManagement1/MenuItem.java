package restaurantManagement1;
import javax.swing.ImageIcon;

public class MenuItem {
	
	private String name;
	private double price;
	private String description;
	private ImageIcon image;
	private String category;
	
	
	public MenuItem(String name, double price, String description, ImageIcon image, String category) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
}
