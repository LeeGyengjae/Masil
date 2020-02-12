package masil.review;

import java.sql.Date;

public class ReviewVO {
	
	/*
	 * idx : Review 테이블 인덱스 번호
	 * code : Product 테이블 상품코드
	 * id : 작성자 id (User table)
	 * content : 후기 내용
	 * rating : 후기 평점
	 * end_date : Product 테이블 종료날짜 (여행상품/패키지 종료 날짜)
	 */
	private int idx;
	private String code;
	private String id;
	private String content;
	private Date write_date;
	private int rating;
	private Date end_date;
	
	//기본 생성자
	public ReviewVO() {}
	
	//toString() Override
	@Override
	public String toString() {
		return "ReviewVO class [idx: "+idx+", code: "+code
				+", id: "+id+", content: "+content+", write_date: "+write_date
				+", rating: "+rating+", end_date: "+end_date+"]";
	}//toString() Override
	
	/* getter & setter */
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	
}
