package mypackage;

public class ChineseWeek {

	public static void main(String[] args) {
		int year=2012,month=12,day=31;
		boolean leap=year%400==0||year%100!=0&&year%4==0;
		int total=year-1980+(year-1980+3)/4;
		for(int i=month-1;i>0;i--)
			switch(i)
			{
			case 1:case 3:case 5:case 7:case 8:case 10:case 12:total+=31;break;
			case 4:case 6:case 9:case 11:total+=30;break;
			case 2:total+=leap?29:28;
			}
		total+=day;
		int week=1;
		week=(week+total)%7;
		System.out.print(year+"年"+month+"月"+day+"日   星期");
		switch(week)
		{
		case 0:System.out.print("日");break;
		case 1:System.out.print("一");break;
		case 2:System.out.print("二");break;
		case 3:System.out.print("三");break;
		case 4:System.out.print("四");break;
		case 5:System.out.print("五");break;
		case 6:System.out.print("六");break;
		}
		
	}

}
