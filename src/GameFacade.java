import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
		this.player = new Player();
		this.board = new Board(textFile);
		this.running = true;
	}
	
	public void run() throws FileNotFoundException
	{
		//this.gameDescriptionPrompt();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String command = in.nextLine();
		while( !command.equals("quit") && this.running )
		{			
			this.player.action(command);
			command = in.nextLine();
		}
		this.running = false;
		return;
	}
	
	public void gameDescriptionPrompt() throws FileNotFoundException
	{
		BufferedReader stream;
		try {
			stream = new BufferedReader(new FileReader("introPrompt.txt"));
			String line;
			int sleepTime = 0;
			while((line = stream.readLine()) != null)
			{
				System.out.println(line);
				
				//prints out text slowly 
				//http://stackoverflow.com/questions/25333935/slowly-print-text-in-java
//				try {
//					if (sleepTime > 23)						
//						Thread.sleep(100);
//					else
//						Thread.sleep(200);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				sleepTime++;
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException
	{
		GameFacade game = new GameFacade("maze1.txt");
		game.gameDescriptionPrompt();
		while (game.running)
		{
			game.run();
		}
	}
}

