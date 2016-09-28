
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
}