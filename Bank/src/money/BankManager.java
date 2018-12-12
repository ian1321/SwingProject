package money;

import java.util.ArrayList;
import java.util.Scanner;

public class BankManager {
	public BankManager() throws Exception {
		/*�� �־��� ���� ����*/
		Scanner sc = new Scanner(System.in);
		ArrayList<BankDTO> arr;
		BankDTO dto;
		String id;

		/*�ý��� �ݺ�*/
		while (true) {
			System.out.println("");
			System.out.println("--------------------------------");
			System.out.println("���� �� ���� ���α׷�");
			System.out.println("���ϴ� �޴��� �����ϼ���");
			System.out.print("1. �ű� �� \n2. �� ���� ���� \n3. �� Ż�� \n4. �� ���� �˻�\n5. ��ü �� ����Ʈ \n6. ���� \n>>");
			switch (sc.nextInt()) {
			/*�ű԰����*/
			case 1:
				dto = new BankDTO();
				System.out.println("�ű԰��� �Է��մϴ�");
				System.out.println("���̵�");
				dto.setId(sc.next());
				System.out.println("�̸�");
				dto.setName(sc.next());
				System.out.println("����");
				dto.setAge(sc.nextInt());
				System.out.println("��ȭ��ȣ");
				dto.setTel(sc.next());
				System.out.println("�Է��� �Ϸ��߽��ϴ�. ó��ȭ������ ���ư��ϴ�");
				new BankDAO().create(dto);
				break;
			/*����������*/
			case 2:
				System.out.println("�� ������ �����մϴ�");
				System.out.println("�����ϰ��� �ϴ� id�� �Է����ּ���");
				id = sc.next();
				System.out.println("�����ϰ��� �ϴ� id�� ��ȭ��ȣ�� �Է����ּ���");
				new BankDAO().update(id, sc.next());
				System.out.println("����Ǿ����ϴ�. ó��ȭ������ ���ư��ϴ�");
				break;
			/*��Ż��*/
			case 3:
				System.out.println("�� id�� Ż���մϴ�");
				System.out.println("Ż���ϰ��� �ϴ� id�� �Է����ּ���");
				id = sc.next();
				new BankDAO().delete(id);
				System.out.println("Ż�� �Ϸ�Ǿ����ϴ�. ó��ȭ������ ���ư��ϴ�");
				break;
			/*�������˻�*/
			case 4:
				System.out.println("�� ������ �˻��մϴ�");
				System.out.println("�˻��ϰ��� �ϴ� id�� �Է����ּ���");
				id = sc.next();
				dto = new BankDTO();
				dto = new BankDAO().select(id);
				System.out.println("������");
				System.out.println("���̵� : " + dto.getId());
				System.out.println("�̸� : " + dto.getName());
				System.out.println("���� : " + dto.getAge());
				System.out.println("��ȭ��ȣ : " + dto.getTel());
				System.out.println("�˻��� �Ϸ��߽��ϴ�. ó��ȭ������ ���ư��ϴ�");
				break;
			/*��ü������Ʈ*/
			case 5:
				System.out.println("��ü �� ����Ʈ");
				arr = new ArrayList<BankDTO>();
				arr = new BankDAO().selectAll();
				for (int i = 0; i < arr.size(); i++) {
					dto = arr.get(i);
					System.out.println("���̵� : " + dto.getId());
					System.out.println("�̸� : " + dto.getName());
					System.out.println("���� : " + dto.getAge());
					System.out.println("��ȭ��ȣ : " + dto.getTel());
				}
				break;
			/*�ý�������*/
			case 6:
				System.out.println("�ý����� �����մϴ�");
				System.exit(0);
			}
		}
	}
	
	/*����*/
	public static void main(String[] args) throws Exception {
		new BankManager();
	}
}
