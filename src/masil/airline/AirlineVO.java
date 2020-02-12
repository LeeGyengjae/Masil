package masil.airline;

import java.sql.Date;

public class AirlineVO {
	
	/*
	 * idx : airline ���̺� �ε��� ��ȣ
	 * aircode : ����� �ڵ�
	 * departure : ���� �����
	 * destination : ���� ������
	 * dep_time : ���� ��� �ð�
	 * arr_time : ���� ���� �ð�
	 * airname : �װ��� �̸�
	 * grade : �¼����
	 */
	private int idx;
	private String aircode;
	private String departure;
	private String destination;
	private Date dep_time;
	private Date arr_time;
	private String airname;
	private String grade;
	
	//�⺻ ������
	public AirlineVO() {}
	
	//toString() Override
	@Override
	public String toString() {
		return "AirlineVO class [idx: "+idx+", aircode: "+aircode
				+", departure: "+departure+", destination: "+destination+", dep_time: "+dep_time
				+", arr_time: "+arr_time+", airname: "+airname+", grade: "+grade+"]";
	}//toString() Override
	
	/* getter & setter */
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getAircode() {
		return aircode;
	}

	public void setAircode(String aircode) {
		this.aircode = aircode;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDep_time() {
		return dep_time;
	}

	public void setDep_time(Date dep_time) {
		this.dep_time = dep_time;
	}

	public Date getArr_time() {
		return arr_time;
	}

	public void setArr_time(Date arr_time) {
		this.arr_time = arr_time;
	}

	public String getAirname() {
		return airname;
	}

	public void setAirname(String airname) {
		this.airname = airname;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	
}
