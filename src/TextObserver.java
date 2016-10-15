
public class TextObserver { //extends Observer {
	Boolean activated;
	String textToActivate;
	String effect;
	Tuple room;
	String direction;
	String textToRoom;
	SpecialArrayList<Item> addItems = new SpecialArrayList<Item>();
	SpecialArrayList<Item> removeItems = new SpecialArrayList<Item>();
	String textToPrint;
	Player subject;
	

	public TextObserver(Player subject)
	{
		this.subject = subject;
		this.subject.attachTextObserver(this);
		this.activated = false;
	}
	
	public Boolean update(String text) {
		if (!this.activated && text.equals(this.textToActivate))
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
	
	public String getTextToRoom() {
		return textToRoom;
	}
	
	public void setTextToRoom(String text) {
		this.textToRoom = text;
	}
	
	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public String getTextToActivate() {
		return textToActivate;
	}

	public void setTextToActivate(String textToActivate) {
		this.textToActivate = textToActivate;
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

//	@Override
//	public void update() {
//		// TODO Auto-generated method stub
//		System.out.println("TextObserver was updated but there is not text to observe...");
//	}
}
