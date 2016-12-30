package mypackage;

class EqualBirthday  implements Equalable<Person>
{
	public boolean equals(Person p1,Person p2)
	{
		return p1.birthday.equals(p2.birthday);
	}
}