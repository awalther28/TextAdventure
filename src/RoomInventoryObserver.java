import java.util.Hashtable;


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
		this.activated = false;
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
					System.out.println("Congrats! You won!!!!");
					GameFacade.running = false;
					break;
				case("Lose Game"):
					System.out.println("You lost...");
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
			}
			this.activated = true;
			return true;		
		}
		return false;
	}
	
	//
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
