/**
 * @author Allison Walther
 * CSC 300 Project 1.2
 * October 16, 2016
 * 
 */
public class TextObserverFactory {

	
	public static TextObserver createTextObserver(String[] information)
	{
		TextObserver txtObs = new TextObserver(GameFacade.player);
		
		//has it been activated
		if(information[0].equals("Activated"))
			txtObs.setActivated(true);
		else
			txtObs.setActivated(false);
		
		txtObs.setTextToActivate(information[2]);
		txtObs.setEffect(information[3]);
		
		//check to see if we are altering a room
		if(!information[4].equals("None"))
		{
			String info[] = information[4].split(",");
			txtObs.setRoom(new Tuple(Integer.parseInt(info[0]), Integer.parseInt(info[1])));
			//if we are removing a barrier, we need a direction 
			if(txtObs.getEffect().equals("Remove Barrier"))
				txtObs.setDirection(info[2]);
			else
				txtObs.setDirection(null);
		}
		if(information[4].equals("None"))
		{
			txtObs.setRoom(null);
			txtObs.setDirection(null);
		}
		
		txtObs.setTextToRoom(information[5]);
		
		//create a list for adding items
		String items = "Items:" + information[6]; //doing this to match item factory parsing
		txtObs.setAddItems(ItemsFactory.getItems(items));
		
		//create a list for removing items
		items = "Items:" + information[7]; //doing this to match item factory parsing
		txtObs.setRemoveItems(ItemsFactory.getItems(items));
		
		txtObs.setTextToPrint(information[8]);
		
		return txtObs;
	}
}
