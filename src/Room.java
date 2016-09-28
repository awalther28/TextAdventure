import java.util.ArrayList;


public class Room {
	String description;
	String shortDescription;
	ArrayList<Item> items;
	String[] directions;
	
	
	public Room(String desc, String items, String direc)
	{
		this.description = desc;
		this.shortDescription = shortDescCalc();
		this.items = ItemsFactory.getItems(items);
		this.directions = direc.split(",");
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
	
	//prints out description of the room and items it contains
	public void playerEntersRoom()
	{
		System.out.println(this.description);
		System.out.println(this.items);
	}
	
	//parameter: string denoting a direction
	//return: boolean T-if room has direction, F-if not
	public boolean containsDirection(String string) {
		for( int i = 0; i < this.directions.length; i ++ )
		{
			if (this.directions[i].equals(string))
				return true;
		}
		return false;
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
		return shortDescription;
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

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public String[] getDirections() {
		return directions;
	}

	public void setDirections(String[] directions) {
		this.directions = directions;
	}
}

