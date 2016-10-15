import java.util.ArrayList;
/**
 * @author Allison Walther
 * CSC 300 Project 1.2
 * October 16, 2016
 * 
 */

public class SpecialArrayList<T> extends ArrayList<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpecialArrayList()
	{
		super();
	}
	
	@Override 
	public String toString()
	{
		String acc = "";
		if (this.size() > 0)
		{
			acc += this.get(0).toString();
			for(int i = 1; i < this.size(); i++)
			{
				acc += ",";
				acc += this.get(i).toString();
			}
			return acc;
		}
		return "None";
	}
}
