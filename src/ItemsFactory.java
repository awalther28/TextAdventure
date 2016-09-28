import java.util.ArrayList;


public class ItemsFactory {
	
	public static ArrayList<Item> getItems(String items)
	{
		String[] parse = items.split(":");
		String[] parseItems = parse[1].split(",");
		
		ArrayList<Item> mazeItems = new ArrayList<Item>(parseItems.length);

		for (String i: parseItems )
		{
			if (! i.equals("none"))
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
