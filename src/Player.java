import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Allison Walther
 * CSC 300 Project 1.2
 * October 16, 2016
 * 
 */

public class Player extends MazeObject {
	public SpecialArrayList<Item> items;
	public Tuple location;
	public int x;
	public int y;
	private List<TextObserver> textObservers = new ArrayList<TextObserver>();
	private List<InventoryObserver> inventoryObservers = new ArrayList<InventoryObserver>();
	private List<RoomInventoryObserver> roomInventoryObservers = new ArrayList<RoomInventoryObserver>();
	public int maxItems;
	
	
	public Player() {
		super("player");
		this.items = new SpecialArrayList<Item>();
	}
	
	public Tuple getLocation() {
		return location;
	}

	public void setLocation(Tuple location) {
		this.location = location;
		this.x = location.second;
		this.y = this.location.first;
	}

	public List<TextObserver> getTextObservers() {
		return textObservers;
	}

	public int getMaxItems() {
		return maxItems;
	}

	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	//parameter: String of the input from the console
	//action evaluates the console input and acts accordingly
	//this could be moving the player around the maze, dropping an item, picking up an item, checking at items, looking at the room description
	//if none of those actions are requested the user is notified that they computer does not understand
	public void action(String str) {
		String command[] = str.split(" ");
		switch(command[0])
		{
		case ("N"):
			move(command[0]);
			break;
		case "S":
			move(command[0]);
			break;
		case "E":
			move(command[0]);
			break;
		case "W":
			move(command[0]);
			break;
		case "take":
			take(str);
			notifyInventoryObserver(this.items);
			break;
		case "drop":
			drop(str);
			notifyRoomInventoryObserver();
			break;
		case "look":
			look();
			break;
		case "items":
			printItems();
			break;
		case "save":
			saveGame(str);
			break;
		default: 
			Boolean success = notifyTextObserver(str);
			if (!success)
				System.out.println("I'm sorry. I don't understand you");
			break;
		}
		return;
		}
	
	//parameter: entire string from the command line
	//saves the current state of the game to a text file that chosen by the user
	//if no text file exists, one is created
	//if one does exist, it is overwritten
	private void saveGame(String command) {
		String str[] = command.split(" ");
		//make sure that the user gave you something to save it by
		if (str.length > 1) 
		{
			String fileName = str[1];
			try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
			              new FileOutputStream("./"+fileName+".txt"), "utf-8")))
			{
				writer.write(GameFacade.board.y+","+GameFacade.board.x);
				writer.newLine();
				writer.write(GameFacade.board.saveRoomString());
				writer.write(this.y+","+this.x);
				writer.newLine();
				writer.write(this.maxItems+"");
				writer.newLine();
				for(InventoryObserver i: this.inventoryObservers)
				{
					writer.write(i.toString());
				}
				for(TextObserver i: this.textObservers)
				{
					writer.write(i.toString());
				}
				for(RoomInventoryObserver i: this.roomInventoryObservers)
				{
					writer.write(i.toString());
				}
				writer.write("Previously");
				writer.newLine();
				writer.write("Items:"+this.items.toString());
				writer.newLine();
			    if (GameFacade.board.hasPreface())
			    	writer.write(GameFacade.board.prefaceToString());
				writer.close();
				System.out.println("Your game has been saved as: "+fileName+".txt");
			} catch ( IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("Could not save game; you did not specify a name. Try 'save <fileName>' \n");
	}

	//parameter: String of the desired direction a player wants to move towards
	//this function checks the boundaries of the maze, 
	//if the move is legally within the bounds and next Room permits you to move there, the player enters the new room, a description of it is printed
	//else the user is notified that either they hit a wall or have reached the edge of the maze
	private void move(String direction)
	{
		switch (direction)
		{
		case "N":
			if (this.y -1 > -1)
			{
				if (GameFacade.board.maze[(this.y-1)][this.x].containsDirection("S"))
				{
					GameFacade.board.maze[(this.y-1)][this.x].playerEntersRoom();
					this.location = new Tuple((this.y-1), this.x);
					this.y = this.y - 1;		
					GameFacade.board.setPlayerLocation(this.location);
				}
				else 
					System.out.println("Sorry--there is a wall. \n\n");
			}
			else 
				System.out.println("Sorry--you have reached the edge of the text adventure. \n\n");
			break;
		case "S":
			if (this.y +1 < GameFacade.board.y)
			{
				if (GameFacade.board.maze[(this.y+1)][this.x].containsDirection("N"))
				{
					GameFacade.board.maze[(this.y+1)][this.x].playerEntersRoom();
					this.location = new Tuple((this.y+1), this.x);
					this.y = this.y + 1;
					GameFacade.board.setPlayerLocation(this.location);
				}
				else 
					System.out.println("Sorry--there is a wall. \n\n");
			}
			else 
				System.out.println("Sorry--you have reached the edge of the text adventure. \n\n");
			break;
		case "E":
			if (this.x+1 < GameFacade.board.x)
			{
				if (GameFacade.board.maze[this.y][this.x+1].containsDirection("W"))
				{
					GameFacade.board.maze[this.y][this.x+1].playerEntersRoom();
					this.location = new Tuple(this.y, this.x+1);
					this.x = this.x + 1;
					GameFacade.board.setPlayerLocation(this.location);
				}
				else 
					System.out.println("Sorry--there is a wall. \n\n");
			}
			else 
				System.out.println("Sorry--you have reached the edge of the text adventure. \n\n");
			break;
		case "W":
			if (this.x-1 > -1)
			{
				if (GameFacade.board.maze[this.y][this.x-1].containsDirection("E"))
				{
					GameFacade.board.maze[this.y][this.x-1].playerEntersRoom();
					this.location = new Tuple(this.y, this.x-1);
					this.x = this.x - 1;
					GameFacade.board.setPlayerLocation(this.location);
				}
				else 
					System.out.println("Sorry--there is a wall. \n\n");
			}
			else 
				System.out.println("Sorry--you have reached the edge of the text adventure. \n\n");
			break;
		}	
		return;
	}

	//parameter: String command "take <<item>>"
	//attempts to remove item from the room
	//if successful the item is added to the player's items and the player is informed that an item is picked up
	//else the player is informed that the item is not in that room
	private void take(String str) 
	{
		//http://stackoverflow.com/questions/10661482/remove-a-specific-word-from-a-string
		// used this source to understand how to remove "take" from str
		String item = str.replaceAll("\\s*\\btake\\b\\s*", "").trim();
		if (this.items.size() < this.maxItems)
		{
			boolean roomContainedItem = GameFacade.board.maze[this.y][this.x].removeItem(item);
			if (roomContainedItem)
			{
				this.items.add(new Item(item));
				System.out.println("Picked up "+ item);
			}
			else
				System.out.println("This room does not contain that object.");
		}
		else
			System.out.println("Your inventory is full. Enter 'drop <item>' to drop an item in order to free up space.");
		return;	
	}
	
	//parameter: String of command "drop <<item>>"
	//if the player currently has the item, it is removed from the list
	//else the user is informed that they do not have the item
	private void drop(String str)
	{
		
		Item item = new Item(str.replaceAll("\\s*\\bdrop\\b\\s*", "").trim());
		
		if( this.items.contains((MazeObject)item) )
		{
			GameFacade.board.maze[this.y][this.x].addItem(item);
			this.items.remove(item);
			System.out.println("You dropped " + item.toString());
		}
		else
			System.out.println("You do not have that item, look again at your inventory.");
		return;
	}
	
	//prints a list of the items that the player currently has
	private void printItems()
	{
		System.out.println(this.items);
		return;
	}
	
	//prints out what is in the room the played is currently in
	private void look()
	{
		GameFacade.board.maze[this.y][this.x].playerEntersRoom();
		return;
	}
	
	
	//**OBSERVER STUFF**
	
	//TextObserver--"Enter Text"
	public void attachTextObserver(TextObserver observer)
	{
		this.textObservers.add(observer);
	}
	
	public Boolean notifyTextObserver(String text)
	{
		Boolean bool = false;
		for(TextObserver i: this.textObservers)
		{
			bool = bool | i.update(text);
		}
		return bool;
	}
	
	//InventoryObserver--"Items in Inventory"
	public void attachInventoryObserver(InventoryObserver observer)
	{
		this.inventoryObservers.add(observer);
	}
	
	public Boolean notifyInventoryObserver(SpecialArrayList<Item> items2)
	{
		Boolean bool = false;
		for(InventoryObserver i: this.inventoryObservers)
		{
			bool = bool | i.update(items2);
		}
		return bool;
	}
	
	//RoomInventoryObserver--"Items in Room"
	public void attachRoomInventoryObserver(RoomInventoryObserver observer)
	{
		this.roomInventoryObservers.add(observer);
	}
	
	public Boolean notifyRoomInventoryObserver()
	{
		Boolean bool = false;
		for(RoomInventoryObserver i: this.roomInventoryObservers)
		{
			bool = bool | i.update();
		}
		return bool;
	}

	public void setItems(SpecialArrayList<Item> items2) {
		this.items = items2;	
	}

}
