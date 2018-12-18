package swing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.text.html.StyleSheet.ListPainter;

public class FileWriteOrder {

	FoodListDTO dto;
	ArrayList<FoodListDTO> arr;

	public FileWriteOrder(String menu, String id) throws Exception {
		
		FileWriter pw = new FileWriter(id + ".txt", true);
		
		arr = new FoodListDAO().selectColumn("menu", menu);
		dto = arr.get(0);
		
		Calendar cal= Calendar.getInstance();
		
		String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
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
