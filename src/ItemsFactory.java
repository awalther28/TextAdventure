

/**
 * @author Allison Walther
 * CSC 300 Project 1.2
 * October 16, 2016
 * 
 */

public class ItemsFactory {
	
	public static SpecialArrayList<Item> getItems(String items)
	{
		String[] parse = items.split(":");
		String[] parseItems = parse[1].split(",");
		
		SpecialArrayList<Item> mazeItems = new SpecialArrayList<Item>();

		for (String i: parseItems )
		{
			if (! i.equalsIgnoreCase("none"))
				mazeItems.add(new Item(i));		
		}
		return mazeItems;	
	}
}
