import java.util.Enumeration;
import java.util.Hashtable;
/**
 * @author Allison Walther
 * CSC 300 Project 1.2
 * October 16, 2016
 * 
 */

public class RoomInventoryObserver {
	Boolean activated;
	Hashtable<Tuple, SpecialArrayList<Item>> roomItemHashTable;
	String effect;
	Tuple room;
	String direction;
	String textToRoom;
	SpecialArrayList<Item> addItems = new SpecialArrayList<Item>();
	SpecialArrayList<Item> removeItems = new SpecialArrayList<Item>();
	String textToPrint;
	Player subject;
	
	public RoomInventoryObserver(Player subject)
	{
		this.subject = subject;
		this.subject.attachRoomInventoryObserver(this);
	}
	
	
	public Boolean update() {
		if (!this.activated && GameFacade.board.checkRooms(this.roomItemHashTable))
		{
			switch(this.effect)
			{
				case("Add Item To Game"):
					if(this.room != null)
					{
						int y = this.room.getFirst();
						int x = this.room.getSecond();
						GameFacade.board.maze[y][x].addAllItems(this.addItems);
						if (!this.textToRoom.equals("None"))
						{
							GameFacade.board.maze[y][x].addText(this.textToRoom);
						}
						if (!this.textToPrint.equals("None"))
						{
							System.out.println(this.textToPrint);
						}
					}
					break;
				case("Delete Item From Game"):
					if(this.room != null)
					{
						int y = this.room.getFirst();
						int x = this.room.getSecond();
						GameFacade.board.maze[y][x].removeAllItems(this.removeItems);
						if (!this.textToRoom.equals("None"))
						{
							GameFacade.board.maze[y][x].addText(this.textToRoom);
						}
						if (!this.textToPrint.equals("None"))
						{
							System.out.println(this.textToPrint);
						}
					}
					break;	
				case("Win Game"):
					if (!this.textToPrint.equals("None"))
					{
						System.out.println(this.textToPrint);
					}
					System.out.println("Congrats! You won!!!!");
					GameFacade.running = false;
					break;
				case("Lose Game"):
					if (!this.textToPrint.equals("None"))
					{
						System.out.println(this.textToPrint);
					}
					System.out.println("...you lost.");
					GameFacade.running = false;
					break;
				case("Remove Barrier"):
					{
						int y = this.room.getFirst();
						int x = this.room.getSecond();
						GameFacade.board.removeBarrier(this.room, this.direction);
						if (!this.textToRoom.equals("None"))
						{
							GameFacade.board.maze[y][x].addText(this.textToRoom);
						}
						if (!this.textToPrint.equals("None"))
						{
							System.out.println(this.textToPrint);
						}
					}
					break;
				case("Print"):
					{
						System.out.println(this.textToPrint);
						break;
					}
			}
			this.activated = true;
			return true;		
		}
		return false;
	}
	
	//parameter: string from the text file
	//parses text file string and creates a hashtable for rooms and their respective items
	//TODO put this in the RoomInventoryObserverFactory
	public void createRoomItemHashTable(String garbage) {
		Hashtable<Tuple, SpecialArrayList<Item>> hT = new Hashtable<Tuple, SpecialArrayList<Item>>();
		//clean up garbage string from text file
		//row,col:item,item,...item_row,col:item_
		String roomItemPair[] = garbage.split("_");
		for(String i: roomItemPair)
		{
			//parse row,col:item,...item 
			//create room Tuple and use it as a key in the HashTable
			String roomNdItems[] = i.split(":");
			String roomCoord[] = roomNdItems[0].split(",");
			//create key
			Tuple room = new Tuple(Integer.parseInt(roomCoord[0]), Integer.parseInt(roomCoord[1]));
			//create value
			SpecialArrayList<Item> items = ItemsFactory.getItems("Items:"+roomNdItems[1]);
			//put in hashtable
			hT.put(room, items);
		}
		this.roomItemHashTable = hT;
	}

	@Override
	public String toString()
	{
		String acc = "";
		acc += "Trigger \n";
		if(this.activated)
			acc+="Activated \n";
		else
			acc+="Not Activated \n";
		acc+="Items In Rooms \n";
		acc+=this.specialHashTableToString();
		acc+=this.effect + " \n";
		if(this.room != null)
		{
			acc+=this.room;
			if(this.effect.equals("Remove Barrier"))
				acc+=","+this.direction;
			acc+=" \n";
		}
		else
			acc+="None \n";
		acc+= this.textToRoom + " \n";
		acc+= this.addItems.toString() + " \n";
		acc+= this.removeItems.toString() + " \n";
		acc+= this.textToPrint + " \n";	
		return acc;
	}
	
	//used by toString in order to save the state of the HashTable
	private String specialHashTableToString() {
		Enumeration<Tuple> allRooms = roomItemHashTable.keys();
		String acc = "";
		while(allRooms.hasMoreElements())
		{
			Tuple room = allRooms.nextElement();
			SpecialArrayList<Item> items = roomItemHashTable.get(room);
			acc+= room.toString()+":"+items.toString();
		}
		acc+= " \n";
		return acc;
	}


	public Hashtable<Tuple, SpecialArrayList<Item>> getRoomItemHashTable() {
		return roomItemHashTable;
	}

	
	public Boolean getActivated() {
		return activated;
	}


	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public String getEffect() {
		return effect;
	}


	public void setEffect(String effect) {
		this.effect = effect;
	}


	public Tuple getRoom() {
		return room;
	}


	public void setRoom(Tuple room) {
		this.room = room;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public String getTextToRoom() {
		return textToRoom;
	}


	public void setTextToRoom(String textToRoom) {
		this.textToRoom = textToRoom;
	}


	public SpecialArrayList<Item> getAddItems() {
		return addItems;
	}


	public void setAddItems(SpecialArrayList<Item> addItems) {
		this.addItems = addItems;
	}


	public SpecialArrayList<Item> getRemoveItems() {
		return removeItems;
	}


	public void setRemoveItems(SpecialArrayList<Item> removeItems) {
		this.removeItems = removeItems;
	}


	public String getTextToPrint() {
		return textToPrint;
	}


	public void setTextToPrint(String textToPrint) {
		this.textToPrint = textToPrint;
	}


	public Player getSubject() {
		return subject;
	}


	public void setSubject(Player subject) {
		this.subject = subject;
	}
	
}
