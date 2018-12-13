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
			fDto = fArr.get(i);
			rArr = new RecommenderDAO().selectColumn("fMenu", fDto.getMenu());
			rDto = rArr.get(0);
			if ( comp > rDto.getScore() && rDto.getScore() > comp2) {
				comp2 = comp;
				menu2 = menu;
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
