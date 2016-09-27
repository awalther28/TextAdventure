import java.util.ArrayList;


public class ItemsFactory {
	
	public static ArrayList<MazeObject> getItems(String items)
	{
		String[] parse = items.split(":");
		String[] parseItems = parse[0].split(",");
		
		ArrayList<MazeObject> mazeItems = new ArrayList<MazeObject>(parseItems.length);

		for (String i: parseItems )
		{
			mazeItems.add(new MazeObject(i));		
		}
		return mazeItems;	
	}

}
