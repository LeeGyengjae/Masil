package masil.review;

import java.util.List;
import java.util.Map;

// View -> Controller -> [Service] -> DAO
public class ReviewService {

	ReviewDAO reviewDAO;
	
	public ReviewService() {
		reviewDAO = new ReviewDAO();
	}

	public List<Map<String,String>> reviewList(String code) {
		System.out.println("review service page");
		List<Map<String,String>> reviewList = reviewDAO.selectReview(code);
		return reviewList;
	}

	public int insertReview(ReviewVO reviewVO) {
		int re = reviewDAO.insertReview(reviewVO);
		return re;
	}
	
	public int updateReview(ReviewVO reviewVO) {
		int re = reviewDAO.updateReivew(reviewVO);
		return re;
	}
	
	public int deleteReview(ReviewVO reviewVO) {
		int re = reviewDAO.deleteReview(reviewVO);
		return re;
	}
	
	
	
	
	
}//ReviewService class
