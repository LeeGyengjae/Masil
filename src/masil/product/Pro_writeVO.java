package masil.product;

public class Pro_writeVO {

	/*
	 * code : 여행 상품(패키지) 코드 sub_code : 여행 상품(패키지) 세부코드 title : 상품 제목
	 * start_String : 출발일자 end_String : 도착일자 max_num : 최대 참여 인원 curr_num : 현재 참여
	 * 인원 price : 가격 visible : 상품 삭제시 삭제 x -> 보이지 않게 처리 (관리목적), default 'Y' /
	 * N->목록에서 숨김
	 */
	private String code;
	private String sub_code;
	private String title;
	// private String start_String;
	// private String end_String;
	private String start_date;
	private String end_date;
	private int max_num;
	private int curr_num;
	private int price;
	private String visible;

	// 기본 생성자
	public Pro_writeVO() {
	}

	// 생성자 오버로딩
	public Pro_writeVO(String sub_code, String title, String start_date, String end_date, int max_num, int curr_num,
			int price, String visible) {
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

	// 생성자 오버로딩
	public Pro_writeVO(String sub_code, String title, String start_date, String end_date, int max_num, int price) {
		super();
		this.sub_code = sub_code;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.max_num = max_num;
		this.price = price;
	}

	// toString() Override
	@Override
	public String toString() {
		return "Pro_detailVO class [code: " + code + ", sub_code: " + sub_code + ", title: " + title + ", start_date: "
				+ start_date + ", end_date: " + end_date + ", max_num: " + max_num + ", curr_num: " + curr_num
				+ ", price: " + price + ", visible : " + visible + "]";
	}// toString() Override

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

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	
}
