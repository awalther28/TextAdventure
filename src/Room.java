import java.util.ArrayList;

/**
 * @author Allison Walther
 * CSC 300 Project 1
 * September 29, 2016
 * 
 */

public class Room {
	String description;
	String shortDescription;
	SpecialArrayList<Item> items;
	SpecialArrayList<String> directions;
	
	
	public Room(String desc, String items, String direc)
	{
		this.description = desc;
		this.shortDescription = shortDescCalc();
		this.items = ItemsFactory.getItems(items);
		String dir[] = direc.split(",");
		this.directions = new SpecialArrayList<String>();
		for(int i = 0; i < dir.length; i ++)
		{
			this.directions.add(dir[i]);
		}
	}
	
	//parameter: String of the item description that you want removed
	//return: boolean T-if item is removed, F-if not
	public boolean removeItem(String itemDescription)
	{
		boolean removed = false;
		removed = this.items.remove(new Item(itemDescription));
		return removed;
	}
	
	//adds item to the Room's list of items
	public void addItem(Item thing)
	{
		this.items.add(thing);
		return;
	}
	
	public void addAllItems(SpecialArrayList<Item> stuff)
	{
		this.items.addAll(stuff);
		return;
	}
	
	public void removeAllItems(SpecialArrayList<Item> stuff)
	{
		this.items.removeAll(stuff);
		return;
	}
	//prints out description of the room and items it contains
	public void playerEntersRoom()
	{
		System.out.println(this.description);
		System.out.println(this.items);
		System.out.println(this.directions);
	}
	
	//parameter: string denoting a direction
	//return: boolean T-if room has direction, F-if not
	public boolean containsDirection(String string) 
	{
		return this.directions.contains(string);
	}

	//return: String of a shortened Room description
	private String shortDescCalc()
	{
		String str = null;
		if (this.description.contains("sandy beach"))
			str = "sandy beach";
		if (this.description.contains("flower garden"))
			str = "flower garden";
		if (this.description.contains("graveyard"))
			str = "graveyard";
		if (this.description.contains("field"))
			str = "field";
		if (this.description.contains("pasture"))
			str = "pasture";
		return str;
	}
	
	@Override
	public String toString()
	{
		String acc = "";
		acc += this.description + "\n";
		acc += "Items:" + this.items + "\n";
		acc += this.directions + "\n";
		return acc;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(SpecialArrayList<Item> items) {
		this.items = items;
	}

	public void addText(String textToRoom) {
		this.description = this.description + " " + textToRoom;
	}

	public void addDirection(String string) {
		this.directions.add(string);
	}

	public boolean containsItems(SpecialArrayList<Item> stuff) {
		return this.items.containsAll(stuff);
	}
}

