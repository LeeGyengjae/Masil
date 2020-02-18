package masil.product;

public class ProductVO {
	
	/*
	 * code : 여행 상품(패키지) 코드
	 * continent : 여행 대륙  
	 * area : 여행 지역
	 * period : 여행 기간
	 * course : 여행 코스 ex) 서울-오사카-도쿄-홍콩-인천-...
	 * comment : 상품개괄설명(고객노출, 관리용)
	 */
	private String code;
	private String continent;
	private String area;
	private String period;
	private String course;
	private String comment;

	//기본 생성자
	public ProductVO() {}
	
	//생성자 오버로딩
	public ProductVO(String code, String continent, String area, String period, String course, String comment) {
		super();
		this.code = code;
		this.continent = continent;
		this.area = area;
		this.period = period;
		this.course = course;
		this.comment = comment;
	}
	
	//생성자 오버로딩
	public ProductVO(String code, String continent, String period, String course, String comment) {
		super();
		this.code = code;
		this.continent = continent;
		this.period = period;
		this.course = course;
		this.comment = comment;
	}

	//toString() Override
	@Override
	public String toString() {
		return "ProductVO class [code: "+code+", continent: "+continent
				+", area: "+area+", period: "+period
				+", course: "+course+", comment: "+comment+"]";
	}//toString() Override

	/* getter & setter */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
