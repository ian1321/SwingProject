package swing;

public class MemberDTO {
	//transfer할 값들
	private String id; //id varchar(20)
	private int pw; //pw int(20)
	private String name; //name varchar(20)
	private String tel; //tel varchar(20)
	public static String SessionId; //로그인한 아이디 변수값 static에 저장
	
	//toString override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", tel=" + tel + "]";
	}

	//constructor 생성자 생성
	public MemberDTO(String id, int pw, String name, String tel) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
	}
	
	//constructor override
	public MemberDTO() {
		super();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getPw() {
		return pw;
	}
	public void setPw(int pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
