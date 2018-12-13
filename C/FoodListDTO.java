package swing;

public class FoodListDTO {
	String location;
	String sort;
	String rest;
	String menu;
	int price;


	@Override
	public String toString() {
		return "FoodListDTO [location=" + location + ", sort=" + sort + ", rest=" + rest + ", menu=" + menu + ", price="
				+ price + "]";
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getRest() {
		return rest;
	}

	public void setRest(String rest) {
		this.rest = rest;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
