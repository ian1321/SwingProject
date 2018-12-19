package swing;

import java.util.ArrayList;

//CRUD인터페이스
public interface Crud {

	Boolean select(String id, String pw) throws Exception;

	void create(MemberDTO dto) throws Exception;

	void update(MemberDTO dto) throws Exception;

	void delete(String id) throws Exception;
}