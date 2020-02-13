package masil.product;

import java.sql.Date;

public class Pro_writeVO {
	
	/* 
	 * code : 여행 상품(패키지) 코드
	 * sub_code : 여행 상품(패키지) 세부코드
	 * title : 상품 제목
	 * start_date : 출발일자
	 * end_date : 도착일자
	 * max_num : 최대 참여 인원
	 * curr_num : 현재 참여 인원
	 * price : 가격
	 * visible : 상품 삭제시 삭제 x -> 보이지 않게 처리 (관리목적), default 'N'
	*/
	private String code;
	private String sub_code;
	private String title;
	private Date start_date;
	private Date end_date;
	private int max_num;
	private int curr_num;
	private int price;
	private String visible;
	
	//기본 생성자
	public Pro_writeVO() {}
	
	//생성자 오버로딩
	public Pro_writeVO(String sub_code, String title, Date start_date, Date end_date, 
			int max_num, int curr_num, int price, String visible) {
		super();
		this.sub_code = sub_code;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.max_num = max_num;
		this.curr_num = curr_num;
		this.price = price;
		this.visible = visible;
	}

	//toString() Override
	@Override
	public String toString() {
		return "Pro_detailVO class [code: "+code+", sub_code: "+sub_code
				+", title: "+title+", start_date: "+start_date
				+", end_date: "+end_date+", max_num: "+max_num
				+", curr_num: "+curr_num+", price: "+price+", visible : "+visible+"]";
	}//toString() Override

	/* getter & setter */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSub_code() {
		return sub_code;
	}

	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}
	
	
}
