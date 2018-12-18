package swing;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReadOrder {
	
	
	
	
	public ArrayList<FoodListDTO> history(String id) throws Exception {
		File file = new File(id + ".txt");
		Scanner sc= new Scanner(file);
		FoodListDTO dto = new FoodListDTO();
		ArrayList<FoodListDTO> arr = new ArrayList<FoodListDTO>();
		FoodListDTO dto2 = new FoodListDTO();
		while (sc.hasNextLine()) {
			if(sc.nextLine().equals("----------영수증----------")) {
				dto = new FoodListDTO();
				dto.setRest(sc.nextLine().substring(5));
				dto.setMenu(sc.nextLine().substring(4));
				dto.setPrice(Integer.parseInt(sc.nextLine().substring(4)));
				dto2=new FoodListDAO().selectColumn("menu",dto.getMenu()).get(0);
				dto.setSort(dto2.getSort());
				arr.add(dto);
			}
		}
		return arr;
	}
	
	public ArrayList<Date> date(String id) throws Exception {
		File file = new File(id + ".txt");
		Scanner sc= new Scanner(file);
		Date date = null;
		ArrayList<Date> dateList = new ArrayList<Date>();
		while (sc.hasNextLine()) {
			if(sc.nextLine().equals("----------영수증----------")) {
				sc.nextLine();
				sc.nextLine();
				sc.nextLine();
				date = date.valueOf(sc.nextLine().substring(6));
				dateList.add(date);
		}
		}
		
		return dateList;
	}
	
}
