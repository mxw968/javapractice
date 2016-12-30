package mypackage;

import java.util.Comparator;

class CompareBirthday implements Comparator<Person>
{
	public int compare(Person p1,Person p2)
	{
		return p1.birthday.compareTo(p2.birthday);
	}
}