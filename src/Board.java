import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author Allison Walther
 * CSC 300 Project 1.2
 * October 16, 2016
 * 
 */

public class Board {
	int x;	//dimensions of board
	int y;	//dimensions of board	
	Room[][] maze;
	Tuple playerLocation;
    int maxItems;
    ArrayList<String> preface;
	
	public Board(String textFile) throws IOException
	{
		try 
		{
			FileReader fileReader = new FileReader(textFile);		
			BufferedReader stream = new BufferedReader(fileReader);
			
			String[] coordinates = stream.readLine().split(",");
			this.x = Integer.parseInt(coordinates[1]);
			this.y = Integer.parseInt(coordinates[0]);
			this.maze = new Room[this.y][this.x];
			
			//create Rooms with Items and directions
			for (int i = 0; i < this.x; i ++)
			{
				for (int l = 0; l < this.y; l++)
				{
					this.maze[l][i] = new Room(stream.readLine(), stream.readLine(), stream.readLine());
				}
			}
			
			//parse for player's starting location
			//set location after we create triggers and check for preface
			//we do this so that the preface will be read first then the room description of the room will be read 
			String playerStart[] = stream.readLine().split(",");
			
			this.maxItems = Integer.parseInt(stream.readLine());
			GameFacade.player.setMaxItems(this.maxItems);
			
			//start with triggers
			String s = stream.readLine();
			while(s != null)
			{
				
				ArrayList<String> acc = new ArrayList<String>();
				if (s.equals("Trigger"))
				{
					String allInfo[] = {stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine(), stream.readLine()};
					if( allInfo[1].equals("Enter Text"))
					{
						TextObserverFactory.createTextObserver(allInfo);
					}
					if( allInfo[1].equals("Items In Inventory"))
					{
						InventoryObserverFactory.createInventoryObserver(allInfo);
					}
					if( allInfo[1].equals("Items In Rooms"))
					{
						RoomInventoryObserverFactory.createRoomInventoryObserver(allInfo);
					}
				}
				//means that we are loading a saved game
				if (s.equals("Previously"))
				{
					GameFacade.player.setItems(ItemsFactory.getItems(stream.readLine()));
				}

				//used to give introduction to text adventure
				if (s.equals("PRINT"))
				{
					acc.add(s);
					s = stream.readLine();
					acc.add(s);
					
					while(!s.equals("END"))
					{
						System.out.println(s);
						s = stream.readLine();
						acc.add(s);
					}
				}
				if(acc.size() > 2)
					this.preface = acc;
				if(acc.size() < 2)
					this.preface = null;
				
				s = stream.readLine();
			}
			
			//set player location now after preface has been read
			this.playerLocation = new Tuple(Integer.parseInt(playerStart[0]), Integer.parseInt(playerStart[1]));
			GameFacade.player.setLocation(this.playerLocation);
			
			System.out.println("Enter command: ");
			fileReader.close();
			stream.close();
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("there was a problem");
		}
	}
	
	    //prints out maze FOR DEV USE ONLY
		@Override
		public String toString()
		{
			String devLines = "";
			String line = "";
			for (int l = 0; l < this.y; l ++)
			{
				for (int i = 0; i < this.x; i++)
				{
					devLines+= this.maze[l][i].getDescription() + "\t";	
				}
				line = line + devLines + "\n";
				devLines = "";
			}
			return line;
		}
		
		//used for saving 
		//TODO change this to toString to make everything fit better together
			//TODO change save to call GameFacade.board.toString() instead of ....saveRoomString()
		public String saveRoomString()
		{
			String roomInfo = "";
			for (int i = 0; i < this.x; i ++)
			{
				for (int l = 0; l < this.y; l++)
				{
					roomInfo+= this.maze[l][i].toString();	
				}
			}
			return roomInfo;	
		}
		
		public String prefaceToString()
		{
			String prefaceInfo = "";
			String line;
			for (int i = 0; i < this.preface.size(); i++)
			{
				line = this.preface.get(i);
				if(line.equals("PRINT"))
				{
					prefaceInfo += line + "\n";
					//https://www.mkyong.com/java/how-to-get-current-timestamps-in-java/
					//used link to create timestamp
					Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
					prefaceInfo += "Last time played: " + timestamp + "\n\n";
				}
				else
					prefaceInfo += line + "\n";
			}
			return prefaceInfo;
		}
		
		public Tuple getPlayerLocation()
		{
			return this.playerLocation;
		}
		
		public void setPlayerLocation( Tuple location)
		{
			this.playerLocation = location;
		}
		
		public int getMaxItems()
		{
			return this.maxItems;
		}

		//parameter: Tuple room and the direction from that room in which you want to remove a barrier
		//removes the barrier and fixes the adjacent room's information accordingly
		public void removeBarrier(Tuple room, String direction) {
			int y = room.getFirst();
			int x = room.getSecond();
			switch(direction)
			{
				case("N"):
					this.maze[y][x].addDirection("N");
					this.maze[y-1][x].addDirection("S");
					break;
				case("S"):
					this.maze[y][x].addDirection("S");
					this.maze[y+1][x].addDirection("N");
					break;
				case("E"):
					this.maze[y][x].addDirection("E");
					this.maze[y][x+1].addDirection("W");
					break;
				case("W"):
					this.maze[y][x].addDirection("W");
					this.maze[y][x-1].addDirection("E");
					break;		
			}
		}

		//parameter: HashTable of room, item pair
		//checks the rooms listed in the hashtable to see if they have all the items
		//returns true if all of the rooms contain all of the necessary items
		//otherwise returns false
		public boolean checkRooms(
				Hashtable<Tuple, SpecialArrayList<Item>> roomItemHashTable) {
			Enumeration<Tuple> allRooms = roomItemHashTable.keys();
			boolean booleanArray[] = new boolean[roomItemHashTable.size()]; 
			int i = 0;
			while(allRooms.hasMoreElements())
			{
				Tuple room = allRooms.nextElement();
				int y = room.getFirst();
				int x = room.getSecond();
				SpecialArrayList<Item> items = roomItemHashTable.get(room);
				booleanArray[i] = maze[y][x].containsItems(items);
				i++;
			}
			return this.evaluateBooleanArray(booleanArray);
		}
		
		//parameter: array of booleans
		//returns true if the array is longer than 0 and all the booleans are true
		//otherwise returns false
		private boolean evaluateBooleanArray(boolean array[])
		{
			boolean bool = true;
			for(boolean i : array)
			{
				bool = bool && i;
			}
			return bool && (array.length>0);
		}

		public boolean hasPreface() {
			if(this.preface == null)
				return false;
			return true;
		}

}
