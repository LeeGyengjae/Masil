package masil.product;

public class ProductVO {
	
	/*
	 * code : ���� ��ǰ(��Ű��) �ڵ�
	 * continent : ���� ���  
	 * area : ���� ����
	 * period : ���� �Ⱓ
	 * course : ���� �ڽ� ex) ����-����ī-����-ȫ��-��õ-...
	 * comment : ��ǰ��������(������, ������)
	 */
	private String code;
	private String continent;
	private String area;
	private String period;
	private String course;
	private String comment;

	//�⺻ ������
	public ProductVO() {}
	
	//������ �����ε�
	public ProductVO(String code, String continent, String area, String period, String course, String comment) {
		super();
		this.code = code;
		this.continent = continent;
		this.area = area;
		this.period = period;
		this.course = course;
		this.comment = comment;
	}
	
	//������ �����ε�
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
