package swing;

public class BoardDTO {
	//변수설정
	private int number; //auto_increment로 들어갈 글번호
	private String id; //Id
	private String title; //제목
	private String content; //내용
	public static String Upnumber;
	
	//Constructor생성자
	public BoardDTO() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
