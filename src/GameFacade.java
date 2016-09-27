import java.io.IOException;
import java.util.Scanner;



public class GameFacade {
	//Tuple playerLocation;
	static Board board;
	//static Player player;
	static boolean running;
	
	public GameFacade (String textFile) throws IOException
	{
		this.board = new Board(textFile);
		//this.playerLocation = board.whereIsPlayer();
		//this.player = new Player(this.playerLocation, 'U', 100, 100, 100, 100, 100);
		this.running = true;
	}
	
	public void run()
	{
//		@SuppressWarnings("resource")
//		Scanner in = new Scanner(System.in);
//		String direction = in.next();
//		while( !direction.equals("x") )
//		{			
//			this.playerLocation = this.player.move(direction);
//			direction = in.next();
//		}
		
	}

	public static void main(String args[]) throws IOException
	{
		GameFacade game = new GameFacade("board.txt");
		System.out.println(game.board);
//		while (running)
//		{
//			game.run();
//		}
	}
}

