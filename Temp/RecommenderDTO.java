package swing;

public class RecommenderDTO {
	/* 변수 */
	String fMenu; // varchar(20)
	String cook; // varchar(20)
	boolean sauce; // tinyint(1)
	String taste; // varchar(20)
	double score; // double(5,1)
	int count; // int(10)

	/* getter/setter설정 */
	public String getFMenu() {
		return fMenu;
	}

	public void setFMenu(String fMenu) {
		this.fMenu = fMenu;
	}

	public String getCook() {
		return cook;
	}

	public void setCook(String cook) {
		this.cook = cook;
	}

	public boolean isSauce() {
		return sauce;
	}

	public void setSauce(boolean sauce) {
		this.sauce = sauce;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
