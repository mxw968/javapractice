package mypackage;

import mypackage.MyDate;

public class Person implements Comparable<Person>
{
	public String name;
	public MyDate birthday;
	public String sex,province,city;
	public static int count=0;
	
	public Person(String name,MyDate birthday,String sex,String province,String city)
	{
		this.set(name,birthday,sex,province,city);
		count++;
	}
	
	public Person(String name,MyDate birthday)
	{
		this(name,birthday,"","","");
	}
	
	public Person()
	{
		this("",null);
	}
	
	public Person(Person p)
	{
		this(p.name,new MyDate(p.birthday),p.sex,p.province,p.city);
	}
	
    public void finalize()
    {
    	System.out.println("释放对象("+this.toString()+")");
    	Person.count--;
    }

    public static void howMany()
    {
    	System.out.print(Person.count+"个Person对象，");
    }
    
    public void set(String name,MyDate birthday,String sex,String province,String city)
    {
    	this.name=name==null?"":name;
    	this.birthday=birthday;
    	this.sex=sex==null?"":sex;
    	this.province=province==null?"":province;
    	this.city=city==null?"":city;
    }
	
    public String toString()
    {
    	return name+","+(birthday==null?"":birthday.toString())+","+sex+","+province+","+city;
    }
    
    public int compareTo(Person p)
    {
    	return this.name.compareTo(p.name);
    }
    
	public static void main(String[] args) 
	{
		Person p1=new Person("李小明",new MyDate(1994,3,15));
		Person p2=new Person(p1);
		Person.howMany();
		System.out.println("p1: "+p1+"; p2: "+p2+"\np1==p2? "+(p1==p2)+"; p1.name==p2.name? "+(p1.name==p2.name)+", p1.birthday==p2.birthday? "+(p1.birthday==p2.birthday));
		p2.name="张"+p2.name.substring(1);
		MyDate d=p2.birthday;
		d.set(d.getYear()+2,d.getMonth(),d.getDay());
		System.out.println("p1: "+p1+"; p2: "+p2);
		p1.finalize();
		Person.howMany();
	}

}
