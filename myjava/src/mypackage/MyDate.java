package mypackage;

/*public class MyDate 
{
	int year,month,day;
	
	void set(int y,int m,int d)
	{
		year=y;
		month=m;
		day=d;
	}
	
	void set(MyDate d)
	{
		set(d.year,d.month,d.day);
	}
	
	public String toString()
	{
		return year+"年"+month+"月"+day+"日";
	}
	
	public static void main(String[] args) 
	{
		MyDate d1=new MyDate();
		d1.set(2012,1,1);
		MyDate d2=d1;
		System.out.println("d1: "+d1.toString()+",d2: "+d2.toString());
		d2.month=10;
		System.out.println("d1: "+d1+",d2: "+d2);
		d2=new MyDate();
		d2.set(d1);
		System.out.println("d1: "+d1+",d2: "+d2);
	}

}*/

public class MyDate implements Comparable<MyDate>
{
	private int year,month,day;
	private static int thisYear;
	
	static
	{
		thisYear=2012;
	}
	
	public MyDate(int year,int month,int day)
	{
		this.set(year,month,day);
	}
	
	public MyDate()
	{
		this(1970,1,1);
	}
	
	public MyDate(MyDate d)
	{
		this.set(d);
	}
	
	public void set(int year,int month,int day)
	{
		this.year=year;
		this.month=(month>=1&&month<=12)?month:1;
		this.day=(day>=1&&day<=31)?day:1;
	}
	
	public void set(MyDate d)
	{
		set(d.year,d.month,d.day);
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public int getMonth()
	{
		return this.month;
	}
	
	public int getDay()
	{
		return this.day;
	}
	
	public String toString()
	{
		return year+"年"+String.format("%02d",month)+"月"+String.format("%02d", day)+"日";
	}
	
	public static int getThisYear()
	{
		return thisYear;
	}
	
	public static boolean isLeapYear(int year)
	{
		return year%400==0||year%100!=0&&year%4==0;
	}
	
	public boolean isLeapYear()
	{
		return isLeapYear(this.year);
	}
	
	public boolean equals(MyDate d)
	{
		return this==d||d!=null&&this.year==d.year&&this.month==d.month&&this.day==d.day;
	}
	
	public static int daysOfMonth(int year,int month)
	{
		switch(month)
		{
		case 1:case 3:case 5:case 7:case 8:case 10:case 12:return 31;
		case 4:case 6:case 9:case 11:return 30;
		case 2:return MyDate.isLeapYear(year)?29:28;
		default: return 0;
		}
	}
	public int daysOfMonth()
	{
		return daysOfMonth(this.year,this.month);
	}
	
	public void tomorrow()
	{
		this.day++;
		if(this.day>this.daysOfMonth())
		{
			this.day=1;
			this.month++;
			if(this.month>12)
			{
				this.month=1;
				this.year++;
			}
		}
	}
	
	public MyDate yesterday()
	{
		MyDate date=new MyDate(this);
		date.day--;
		if(this.day==0)
		{
			date.month--;
			if(date.month==0)
			{
				date.month=12;
				date.year--;
			}
			date.day=daysOfMonth(date.year,date.month);
		}
		return date;	
	}
	
	public int compareTo(MyDate d)
	{
		if(this.year == d.year && this.month == d.month && this.day == d.day)
			return 0;
		return (this.year > d.year || this.year == d.year && this.month > d.month || this.year == d.year && this.month == d.month && this.day > d.day ) ? 1:-1;
	}
	/*public static void main(String[] args)
	{
		System.out.println("今年是"+MyDate.getThisYear()+",闰年？"+MyDate.isLeapYear(MyDate.getThisYear()));
		MyDate d1=new MyDate(2012,12,31); 
		MyDate d2=new MyDate(d1);
		System.out.println("d1: "+d1+"d2: "+d2+",d1==d2?"+(d1==d2)+",d1.equals(d2)?"+d1.equals(d2));
		System.out.print(d1+" 明天是 ");
		d1.tomorrow();
		System.out.println(d1+"\n"+d1+"的昨天是  "+(d2=d1.yesterday()));
	}*/

}

