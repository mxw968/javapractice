package mypackage;

import mypackage.MyDate;

class MyDate_ex {

	public static void main(String[] args)
	{
		System.out.println("������"+MyDate.getThisYear()+",���ꣿ"+MyDate.isLeapYear(MyDate.getThisYear()));
		MyDate d1=new MyDate(2012,12,31);
		MyDate d2=new MyDate(d1);
		System.out.println("d1: "+d1+"d2: "+d2+",d1==d2?"+(d1==d2)+",d1.equals(d2)?"+d1.equals(d2));
		System.out.print(d1+" ������ ");
		d1.tomorrow();
		System.out.println(d1+"\n"+d1+"��������  "+(d2=d1.yesterday()));
	}
}
