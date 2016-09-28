
/**
 * @author Allison Walther
 * CSC 300 Project 1
 * September 29, 2016
 * 
 */

public class Item extends MazeObject {

	public Item(String type) {
		super(type);
	}
	
	@Override
	public String toString()
	{
		return this.type;
	}

//	public static void main(String[] args)
//	{
//		Item i = new Item("dead fish");
//		Item j = new Item("seaweed");
//		Item ii = new Item("dead fish");
//		System.out.println(i);
//		System.out.println(j);
//		System.out.println(ii);
//		System.out.println(i.equals(ii));
//		System.out.println(i.equals(j));
//	}
}
