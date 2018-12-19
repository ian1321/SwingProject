package swing;

public class ChartElement {
	/*변수 */
	public String name; /*항목이름*/
	public int value; /*값*/

	/*생성자 값대입*/
	public ChartElement(String name, int value) {
		this.name = name;
		this.value = value;
	}

	/*getter/setter*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
