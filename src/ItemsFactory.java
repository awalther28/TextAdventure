

/**
 * @author Allison Walther
 * CSC 300 Project 1
 * September 29, 2016
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

//	public static void main(String[] args)
//	{
//		ArrayList<Item> items = ItemsFactory.getItems("Items:dead fish,seaweed");
//		System.out.println(items.size());
//		System.out.println(items);
//	}
}
