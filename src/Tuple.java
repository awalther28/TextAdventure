
/**
 * @author Allison Walther
 * CSC 300 Project 1.2
 * October 16, 2016
 * 
 */

public class Tuple {

	int first;
	int second;
	
	public Tuple(int first, int second)
	{
		this.first = first;
		this.second = second;
	}
	
	public void print()
	{
		System.out.println(this.first + ", " + this.second);
	}
	
	public int getFirst()
	{
		return this.first;
	}
	
	public int getSecond()
	{
		return this.second;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple other = (Tuple) obj;
		if (first != other.first)
			return false;
		if (second != other.second)
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return this.first+","+this.second;
	}
	
}