package masil.review;

import java.util.List;
import java.util.Map;

// View -> Controller -> [Service] -> DAO
public class ReviewService {

	ReviewDAO reviewDAO;
	
	public ReviewService() {
		reviewDAO = new ReviewDAO();
	}

	public List<Map<String,String>> reviewList(String code, int pageNum) {
		List<Map<String,String>> reviewList = reviewDAO.selectReview(code, pageNum);
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
	
	public String insertReviewAuth(String id, String sub_code) {
		String user_endDate = reviewDAO.insertReviewAuth(id, sub_code);
		return user_endDate;
	}
	
	public List<ReviewVO> selReview(String id, String code){
		List<ReviewVO> revList = reviewDAO.selReview(id);
		return revList;
	}
	
	
	
	
}//ReviewService class
