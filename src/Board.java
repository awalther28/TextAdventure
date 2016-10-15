import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author Allison Walther
 * CSC 300 Project 1
 * September 29, 2016
 * 
 */

public class Board {
	int x;	//dimensions of board
	int y;	//dimensions of board	
	Room[][] maze;
	Tuple playerLocation;
    int maxItems;
	
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
			String playerStart[] = stream.readLine().split(",");
			this.playerLocation = new Tuple(Integer.parseInt(playerStart[0]), Integer.parseInt(playerStart[1]));
			GameFacade.player.setLocation(this.playerLocation);
			
			this.maxItems = Integer.parseInt(stream.readLine());
			GameFacade.player.setMaxItems(this.maxItems);
			
			//start with triggers
			String s = stream.readLine();
			while(s != null)
			{
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
					s = stream.readLine();
					while(!s.equals("END"))
					{
						System.out.println(s);
						s = stream.readLine();
					}
				}
				s = stream.readLine();
			}
			
			fileReader.close();
			stream.close();
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("there was a problem");
		}
	}
	
	    //prints out maze
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
		
		private boolean evaluateBooleanArray(boolean array[])
		{
			boolean bool = true;
			for(boolean i : array)
			{
				bool = bool && i;
			}
			return bool && (array.length>0);
		}

}
