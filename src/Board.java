import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Board {
	int x;	//dimensions of board
	int y;	//dimensions of board	
	Room[][] maze;
	
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
			
			//create board
			for (int i = 0; i < this.x; i ++)
			{
				for (int l = 0; l < this.y; l++)
				{
					this.maze[l][i] = new Room(stream.readLine(), stream.readLine(), stream.readLine());
				}
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
			for (int i = 0; i < this.y; i ++)
			{
				for (int l = 0; l < this.x; l++)
				{
					devLines+= this.maze[i][l] + "\t";	
				}
				line = line + devLines + "\n";
				devLines = "";
			}
			return line;
		}
}
