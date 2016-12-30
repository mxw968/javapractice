package mypackage;

public class Student extends Person{

	/*private String seciality;
	
	public static void main(String[] args) 
	{
		Person p1=new Person("��С��",new MyDate(1992,3,15));
		Student s1=new Student();
		Student.howMany();
		System.out.println("p1: "+p1.toString()+"; s1: "+s1.toString());
		s1.set("����", new MyDate(1987,2,27),"","","");
		s1.finalize();
		Student.howMany();
	}*/
	
	public String department,speciality,number;
	public boolean member;
	private static int count=0;
	
	public Student(String name,MyDate birthday,String sex,String province,String city,
			String department,String speciality,String number,boolean member)
	{
		super(name,birthday,sex,province,city);
		this.set(department, speciality,number,member);
		count++;
	}
	
	public Student()
	{
		this("",new MyDate(),"","","","","","",false);
	}
	
	public Student(Person p,String department,String speciality,String number,boolean member)
	{
		this(p.name,new MyDate(p.birthday),p.sex,p.province,p.city,department,speciality,number,member);
	}
	
	public Student(Student s)
	{
		this(s.name,new MyDate(s.birthday),s.sex,s.province,s.city,s.department,s.speciality,s.number,s.member);
	}
	
	public void finalize()
	{
		super.finalize();
		Student.count--;
	}
	
    public static void howMany()
    {
    	Person.howMany();
    	System.out.println(Student.count+"��Student����");
    }
    
    public void set(String department,String speciality,String number,boolean memeber)
    {
    	this.department=department==null?"":department;
    	this.speciality=speciality==null?"":speciality;
    	this.number=number==null?"":number;
    	this.member=member;
    }
    
    public String toString()
    {
    	return super.toString()+","+department+","+speciality+","+number+(member?",��Ա":"");
    }
    
    public static void main(String[] args)
    {
    	Person p1=new Person("��С��",new MyDate(1992,3,15),"��","����ʡ","��ɳ��");
        Student s1=new Student(p1,"�����ϵ","�������ѧ�뼼��רҵ","001",true);
        Student s2=new Student(s1);
        s2.set("��С��",new MyDate(1992,4,3),"Ů","����ʡ","�人��");
        s2.set("���ù���ϵ","��Ϣ����רҵ","003",true);
        Student.howMany();
        System.out.println("p1: "+p1.toString()+"\ns1"+s1.toString()+"\ns2"+s2);
        s2.finalize();
        Student.howMany();
    }

}
