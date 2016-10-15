import java.util.ArrayList;


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
		}
		return acc;
	}
}
