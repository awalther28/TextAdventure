import java.util.ArrayList;


public class Room {
	String description;
	String shortDescription;
	ArrayList<MazeObject> items;
	String[] directions;
	
	
	public Room(String desc, String items, String direc)
	{
		this.description = desc;
		this.shortDescription = shortDescCalc();
		this.items = ItemsFactory.getItems(items);
		this.directions = direc.split(",");
	}
	
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
	
	public ArrayList<MazeObject> getContents()
	{
		return this.items;
	}
	
	public void emptyRoom()
	{
		this.items = null;
	}
	
	
	
}

