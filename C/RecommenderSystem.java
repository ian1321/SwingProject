package swing;

import java.util.ArrayList;

public class RecommenderSystem {
	/*
	 * RecommenderDAO rDao; RecommenderDTO rDto; ArrayList<RecommenderDTO> rArr;
	 * FoodListDAO fDao; FoodListDTO fDto; ArrayList<FoodListDTO> fArr;
	 */

	/*
	 * public void recommendCT(String fMenu) throws Exception { rDto = new
	 * RecommenderDTO(); rArr = new ArrayList<RecommenderDTO>(); rArr =
	 * rDao.selectColumn("fMenu", fMenu); rDto = rArr.get(0);
	 * 
	 * rDto.getFmenu(); rDto.getCook(); rDto.isSauce(); rDto.getScore();
	 * rDto.getCount(); }
	 */
	
	public ArrayList<RecommenderDTO> recommendType(String fMenu) throws Exception {
		String[] ret = new String[2];
		ArrayList<RecommenderDTO> returnArr = new ArrayList<RecommenderDTO>();
		ArrayList<RecommenderDTO> rArr = new ArrayList<RecommenderDTO>();
		RecommenderDTO rDto = new RecommenderDTO();
		
		RecommenderDAO dao = new RecommenderDAO();
		rArr=dao.selectColumn("fMenu", fMenu);
		String cook = rArr.get(0).getCook();
		Boolean sauce = rArr.get(0).isSauce();
		String taste = rArr.get(0).getTaste();
		
		rArr = new ArrayList<RecommenderDTO>();
		rArr = dao.selectColumn("cook", cook);
		
		for (int i = 0; i < rArr.size(); i++) {
			returnArr.add(rArr.get(i));
		}
		
		rArr = new ArrayList<RecommenderDTO>();
		rArr = dao.selectColumn("taste", taste);
		
		for (int i = 0; i < rArr.size(); i++) {
			returnArr.add(rArr.get(i));
		}
		
		return returnArr;
	}

	public String[] recommendScore(String fMenu) throws Exception {
		String[] ret = new String[2];
		ArrayList<RecommenderDTO> rArr = new ArrayList<RecommenderDTO>();
		ArrayList<FoodListDTO> fArr = new ArrayList<FoodListDTO>();
		FoodListDTO fDto = new FoodListDTO();
		RecommenderDTO rDto = new RecommenderDTO();
		fArr = new FoodListDAO().selectColumn("menu", fMenu);
		fDto = fArr.get(0);

		String menu = "";
		String menu2 = "";
		double comp = 0;
		double comp2 =0;
		
		fArr = new FoodListDAO().selectColumn("sort", fDto.getSort());
		for (int i = 0; i < fArr.size(); i++) {
			fDto = new FoodListDTO();
			rDto = new RecommenderDTO();
			fDto = fArr.get(i);
			rArr = new ArrayList<RecommenderDTO>();
			rArr = new RecommenderDAO().selectColumn("fMenu", fDto.getMenu());
			rDto = rArr.get(0);
			if ( comp > rDto.getScore() && rDto.getScore() > comp2) {
				comp2 = comp;
				menu2 = rDto.getFMenu();
			}
			if (rDto.getScore() > comp) {
				comp = rDto.getScore();
				menu = rDto.getFMenu();
			}
			
		}
		ret[0] = menu;
		ret[1] = menu2;
		return ret;
	}
}
