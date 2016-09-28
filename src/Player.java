import java.util.ArrayList;


public class Player extends MazeObject {
	public ArrayList<MazeObject> items;
	public Tuple location;
	public int x;
	public int y;
	
	public Player() {
		super("player");
		this.items = new ArrayList<MazeObject>();
		this.location = new Tuple(0,0);
		this.x = this.location.first;
		this.y = this.location.second;
	}
	
	//parameter: String of the input from the console
	//action evaluates the console input and acts accordingly
	//this could be moving the player around the maze, dropping an item, picking up an item, checking at items, looking at the room description
	//if none of those actions are requested the user is notified that they computer does not understand
	public void action(String str) {
		String command[] = str.split(" ");
		switch(command[0])
		{
		case "N":
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
			break;
		case "drop":
			drop(str);
			break;
		case "look":
			look();
			break;
		case "items":
			printItems();
			break;
		default: 
			System.out.println("I'm sorry. I don't understand you");
			break;
		}
		return;
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
		String item = str.replaceAll("\\s*\\btake\\b\\s*", "");
		boolean roomContainedItem = GameFacade.board.maze[this.y][this.x].removeItem(item);
		if (roomContainedItem)
		{
			this.items.add(new Item(item));
			System.out.println("Picked up "+ item);
		}
		else
			System.out.println("This room does not contain that object.");
		return;	
	}
	
	//parameter: String of command "drop <<item>>"
	//if the player currently has the item, it is removed from the list
	//else the user is informed that they do not have the item
	private void drop(String str)
	{
		Item item = new Item(str.replaceAll("\\s*\\btake\\b\\s*", ""));
		
		if( this.items.contains(item) )
		{
			GameFacade.board.maze[this.y][this.x].addItem(item);
			this.items.remove(item);
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

}
