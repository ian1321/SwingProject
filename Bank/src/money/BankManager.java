package money;

import java.util.ArrayList;
import java.util.Scanner;

public class BankManager {
	public BankManager() throws Exception {
		/*값 넣어줄 변수 선언*/
		Scanner sc = new Scanner(System.in);
		ArrayList<BankDTO> arr;
		BankDTO dto;
		String id;

		/*시스템 반복*/
		while (true) {
			System.out.println("");
			System.out.println("--------------------------------");
			System.out.println("은행 고객 관리 프로그램");
			System.out.println("원하는 메뉴를 선택하세요");
			System.out.print("1. 신규 고객 \n2. 고객 정보 수정 \n3. 고객 탈퇴 \n4. 고객 정보 검색\n5. 전체 고객 리스트 \n6. 종료 \n>>");
			switch (sc.nextInt()) {
			/*신규고객등록*/
			case 1:
				dto = new BankDTO();
				System.out.println("신규고객을 입력합니다");
				System.out.println("아이디");
				dto.setId(sc.next());
				System.out.println("이름");
				dto.setName(sc.next());
				System.out.println("나이");
				dto.setAge(sc.nextInt());
				System.out.println("전화번호");
				dto.setTel(sc.next());
				System.out.println("입력을 완료했습니다. 처음화면으로 돌아갑니다");
				new BankDAO().create(dto);
				break;
			/*고객정보수정*/
			case 2:
				System.out.println("고객 정보를 수정합니다");
				System.out.println("수정하고자 하는 id를 입력해주세요");
				id = sc.next();
				System.out.println("변경하고자 하는 id의 전화번호를 입력해주세요");
				new BankDAO().update(id, sc.next());
				System.out.println("변경되었습니다. 처음화면으로 돌아갑니다");
				break;
			/*고객탈퇴*/
			case 3:
				System.out.println("고객 id를 탈퇴합니다");
				System.out.println("탈퇴하고자 하는 id를 입력해주세요");
				id = sc.next();
				new BankDAO().delete(id);
				System.out.println("탈퇴가 완료되었습니다. 처음화면으로 돌아갑니다");
				break;
			/*고객정보검색*/
			case 4:
				System.out.println("고객 정보를 검색합니다");
				System.out.println("검색하고자 하는 id를 입력해주세요");
				id = sc.next();
				dto = new BankDTO();
				dto = new BankDAO().select(id);
				System.out.println("고객정보");
				System.out.println("아이디 : " + dto.getId());
				System.out.println("이름 : " + dto.getName());
				System.out.println("나이 : " + dto.getAge());
				System.out.println("전화번호 : " + dto.getTel());
				System.out.println("검색을 완료했습니다. 처음화면으로 돌아갑니다");
				break;
			/*전체고객리스트*/
			case 5:
				System.out.println("전체 고객 리스트");
				arr = new ArrayList<BankDTO>();
				arr = new BankDAO().selectAll();
				for (int i = 0; i < arr.size(); i++) {
					dto = arr.get(i);
					System.out.println("아이디 : " + dto.getId());
					System.out.println("이름 : " + dto.getName());
					System.out.println("나이 : " + dto.getAge());
					System.out.println("전화번호 : " + dto.getTel());
				}
				break;
			/*시스템종료*/
			case 6:
				System.out.println("시스템을 종료합니다");
				System.exit(0);
			}
		}
	}
	
	/*메인*/
	public static void main(String[] args) throws Exception {
		new BankManager();
	}
}
