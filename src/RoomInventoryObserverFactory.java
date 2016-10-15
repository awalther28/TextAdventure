
public class RoomInventoryObserverFactory {

	public static void createRoomInventoryObserver(String[] information) {
		RoomInventoryObserver roomInventoryObs = new RoomInventoryObserver(GameFacade.player);
		
		roomInventoryObs.createRoomItemHashTable(information[2]);
		roomInventoryObs.setEffect(information[3]);
		
		//check to see if we are altering a room
		if(!information[4].equals("None"))
		{
			String info[] = information[4].split(",");
			roomInventoryObs.setRoom(new Tuple(Integer.parseInt(info[0]), Integer.parseInt(info[1])));
			//if we are removing a barrier, we need a direction
			if(roomInventoryObs.getEffect().equals("Remove Barrier"))
				roomInventoryObs.setDirection(info[2]);
			else
				roomInventoryObs.setDirection(null);
		}
		if(information[4].equals("None"))
		{
			roomInventoryObs.setRoom(null);
			roomInventoryObs.setDirection(null);
		}
		
		roomInventoryObs.setTextToRoom(information[5]);
		
		//create a list for adding items
		String items = "Items:" + information[6]; //doing this to match item factory parsing
		roomInventoryObs.setAddItems(ItemsFactory.getItems(items));
		
		//create a list for removing items
		items = "Items:" + information[7]; //doing this to match item factory parsing
		roomInventoryObs.setRemoveItems(ItemsFactory.getItems(items));
		
		roomInventoryObs.setTextToPrint(information[8]);		
	}

}
