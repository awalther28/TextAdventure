import java.io.IOException;
import java.util.Scanner;

/**
 * @author Allison Walther
 * CSC 300 Project 1
 * September 29, 2016
 * 
 */

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
		GameFacade game = new GameFacade("maze.txt");
		while (running)
		{
			game.run();
		}
	}
}

