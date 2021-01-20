import java.awt.Color;

public class Product {
	private Color color;
	private int id;
	public Product(Color color,int id) {
		this.color = color;
		this.id=id;
	}
	public int getId() {
		return this.id;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
