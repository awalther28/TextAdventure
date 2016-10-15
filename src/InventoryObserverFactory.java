
public class InventoryObserverFactory {

	public static void createInventoryObserver(String[] information) {
		InventoryObserver inventoryObs = new InventoryObserver(GameFacade.player);
		
		//create list of items that will activate trigger
		String itemsToActivate = "Items:" + information[2]; //doing this to match item factory parsing
		inventoryObs.setItemsToActivate(ItemsFactory.getItems(itemsToActivate));
		inventoryObs.setEffect(information[3]);
		
		//check to see if we are altering a room
		if(!information[4].equals("None"))
		{
			String info[] = information[4].split(",");
			inventoryObs.setRoom(new Tuple(Integer.parseInt(info[0]), Integer.parseInt(info[1])));
			//if we are removing a barrier, we need a direction
			if(inventoryObs.getEffect().equals("Remove Barrier"))
				inventoryObs.setDirection(info[2]);
			else
				inventoryObs.setDirection(null);
		}
		if(information[4].equals("None"))
		{
			inventoryObs.setRoom(null);
			inventoryObs.setDirection(null);
		}
		
		inventoryObs.setTextToRoom(information[5]);
		
		//create a list for adding items
		String items = "Items:" + information[6]; //doing this to match item factory parsing
		inventoryObs.setAddItems(ItemsFactory.getItems(items));
		
		//create a list for removing items
		items = "Items:" + information[7]; //doing this to match item factory parsing
		inventoryObs.setRemoveItems(ItemsFactory.getItems(items));
		
		inventoryObs.setTextToPrint(information[8]);
	}

}
