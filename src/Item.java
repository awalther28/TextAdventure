
/**
 * @author Allison Walther
 * CSC 300 Project 1.2
 * October 16, 2016
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
}
