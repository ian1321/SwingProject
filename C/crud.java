package swing;

import java.util.ArrayList;

public interface crud {

	Boolean select(String id, String pw) throws Exception;
	void create(MemberDTO dto) throws Exception;
	void update(MemberDTO dto) throws Exception;
	void delete(MemberDTO dto) throws Exception;
}