package swing;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import swing2.FoodListDAO;
import swing2.FoodListDTO;

public class FileWriteOrder {
	/*변수*/
	FoodListDTO dto = new FoodListDTO();
	ArrayList<FoodListDTO> arr;

	public FileWriteOrder(String menu, String id) throws Exception {
		/*연속해서 파일쓰기*/
		FileWriter pw = new FileWriter(id + ".txt", true);
		/*입력받은 메뉴*/
		arr = new FoodListDAO().selectColumn("menu", menu);
		dto = arr.get(0);
		/*날짜입력*/
		Calendar cal = Calendar.getInstance();

		String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH);
		pw.write("----------영수증----------\r\n");
		pw.write("음식점 :" + dto.getRest() + "\r\n");
		pw.write("메뉴 :" + dto.getMenu() + "\r\n");
		pw.write("가격 :" + dto.getPrice() + "\r\n");
		pw.write("주문날짜 :" + date + "\r\n");
		pw.write("\r\n");
		pw.flush();
		pw.close();
	}
}
