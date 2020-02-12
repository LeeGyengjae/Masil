package masil.user;

public class UserVO {
	
	/*
	 * id : ���̵� 			 
	 * pwd : ��й�ȣ
	 * name : �̸� 	
	 * pname : ���� �̸�(������)	 
	 * pnum : ���ǹ�ȣ 
	 * jumin : �ֹι�ȣ ���ڸ�	 
	 * gender : ����
	 */
	private String id;			
	private String pwd;	
	private String name;
	private String pname;
	private String pnum;
	private String jumin1;
	private String jumin2;
	private String gender;
	
	//�⺻ ������
	public UserVO() {}
	
	public UserVO(String id , String pwd , String name, String pname , String pnum , String jumin1, String jumin2 , String gender) {
		
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.pname = pname;
		this.pnum = pnum;
		this.jumin1 = jumin1;
		this.jumin2 = jumin2;
		this.pname = gender;
		
	}
	//toString() Override
	@Override
	public String toString() {
		return "UserVO class [id: "+id+", padssword: "+pwd
				+", name: "+name+", pname: "+pname+", pnum: "+pnum
				+", jumin1: "+jumin1+", jumin2:"+jumin2+", gender: "+gender+"]";
	}//toString() Override
	
	/* getter & setter */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpwd() {
		return pwd;
	}
	public void setpwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getJumin1() {
		return jumin1;
	}
	public void setJumin1(String jumin1) {
		this.jumin1 = jumin1;
	}
	public String getJumin2() {
		return jumin2;
	}
	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
