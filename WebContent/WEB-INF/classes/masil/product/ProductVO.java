package masil.product;

import java.sql.Date;

public class ProductVO {
	
	/*
	 * code : 여행 상품(패키지) 코드
	 * region : 여행 지역 
	 * start_date : 패키지 시작 날짜
	 * end_date : 패키지 종료 날짜
	 * max_num : 패키지 참여 총 인원수
	 * curr_num : 패키지 현재 참여 인원수
	 */
	private String code;
	private String region;
	private Date start_date;
	private Date end_date;
	private int max_num;
	private int curr_num;
	
	//기본 생성자
	public ProductVO() {}
	
	//toString() Override
	@Override
	public String toString() {
		return "ProductVO class [code: "+code+", region: "+region
				+", start_date: "+start_date+", end_date: "+end_date
				+", max_num: "+max_num+", curr_num: "+curr_num+"]";
	}//toString() Override
	
	/* getter & setter */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getMax_num() {
		return max_num;
	}

	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}

	public int getCurr_num() {
		return curr_num;
	}

	public void setCurr_num(int curr_num) {
		this.curr_num = curr_num;
	}
	
	
}
