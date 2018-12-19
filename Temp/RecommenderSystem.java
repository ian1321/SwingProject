package swing;

import java.util.ArrayList;
import swing2.FoodListDAO;
import swing2.FoodListDTO;

public class RecommenderSystem {

	/* Content-Based - 타입이 비슷한 상품 추천하기 */
	public ArrayList<RecommenderDTO> recommendType(String fMenu) throws Exception {
		/* 변수 */
		/* 반환할 ArrayList */
		ArrayList<RecommenderDTO> returnArr = new ArrayList<RecommenderDTO>();
		/* 값넣을 ArrayList */
		ArrayList<RecommenderDTO> rArr = new ArrayList<RecommenderDTO>();
		RecommenderDAO dao = new RecommenderDAO();
		/* 파라미터의 레코드값 rArr.get(0)에 삽입 */
		rArr = dao.selectColumn("fMenu", fMenu);
		String cook = rArr.get(0).getCook();
		String taste = rArr.get(0).getTaste();
		/* 파라미터의 cook값과 같은 레코드들 가져오기 */
		rArr = dao.selectColumn("cook", cook);
		for (int i = 0; i < rArr.size(); i++) {
			returnArr.add(rArr.get(i));
		}

		/* 파라미터의 taste값과 같은 레코드들 가져오기 */
		rArr = dao.selectColumn("taste", taste);
		for (int i = 0; i < rArr.size(); i++) {
			returnArr.add(rArr.get(i));
		}
		return returnArr;
	}

	/* Collaborative Filtering - 타입이 비슷한 상품 추천하기 */
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
		double comp2 = 0;

		fArr = new FoodListDAO().selectColumn("sort", fDto.getSort());
		for (int i = 0; i < fArr.size(); i++) {
			fDto = new FoodListDTO();
			rDto = new RecommenderDTO();
			fDto = fArr.get(i);
			rArr = new ArrayList<RecommenderDTO>();
			rArr = new RecommenderDAO().selectColumn("fMenu", fDto.getMenu());
			rDto = rArr.get(0);
			if (comp > rDto.getScore() && rDto.getScore() > comp2) {
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
