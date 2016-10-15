
public class InventoryObserver {
	Boolean activated;
	SpecialArrayList<Item> itemsToActivate;
	String effect;
	Tuple room;
	String direction;
	String textToRoom;
	SpecialArrayList<Item> addItems = new SpecialArrayList<Item>();
	SpecialArrayList<Item> removeItems = new SpecialArrayList<Item>();
	String textToPrint;
	Player subject;
	
	
	public InventoryObserver(Player subject)
	{
		this.subject = subject;
		this.subject.attachInventoryObserver(this);
		this.activated = false;
	}
	
	
	public Boolean update(SpecialArrayList<Item> items2) {
		if (!this.activated && items2.containsAll(this.itemsToActivate))
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


	public Boolean getActivated() {
		return activated;
	}


	public void setActivated(Boolean activated) {
		this.activated = activated;
	}


	public SpecialArrayList<Item> getItemsToActivate() {
		return itemsToActivate;
	}


	public void setItemsToActivate(SpecialArrayList<Item> specialArrayList) {
		this.itemsToActivate = specialArrayList;
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
