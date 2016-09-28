import java.io.IOException;
import java.util.Scanner;

// how do you exit the program
// where should the player start
// does look print just the description or does it also include the items in the room
// how would you like us to denote when a room is empty

public class GameFacade {
	static Board board;
	static Player player;
	static boolean running;
	
	public GameFacade (String textFile) throws IOException
	{
		this.board = new Board(textFile);
		this.player = new Player();
		this.running = true;
	}
	
	public void run()
	{
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String command = in.nextLine();
		while( !command.equals("exit") )
		{			
			this.player.action(command);
			command = in.nextLine();
		}
		this.running = false;
		return;
	}

	public static void main(String args[]) throws IOException
	{
		GameFacade game = new GameFacade("board.txt");
		while (running)
		{
			game.run();
		}
	}
}

