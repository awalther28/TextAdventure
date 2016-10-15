import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Allison Walther
 * CSC 300 Project 1.2
 * October 16, 2016
 * 
 */

public class GameFacade {
	static Board board;
	static Player player;
	static boolean running;
	
	public GameFacade () throws IOException
	{
		String textFile = this.startScreen();
		this.player = new Player();
		this.board = new Board(textFile);
		this.running = true;
	}
	
	public void run() throws FileNotFoundException
	{
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String command = in.nextLine();
		while( !command.equals("quit") && this.running)
		{			
			this.player.action(command);
			command = in.nextLine();
		}
		this.running = false;
		return;
	}
	
	//parameter: file name of file you want to print and how fast you want it to print
	//prints of the contents of the file, putting a the thread to sleep inbetween each line
	public void gameDescriptionPrompt(String fileName, int speed) throws FileNotFoundException
	{
		BufferedReader stream;
		try {
			stream = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = stream.readLine()) != null)
			{
				System.out.println(line);
				
				//prints out text slowly 
				//http://stackoverflow.com/questions/25333935/slowly-print-text-in-java
				try {				
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//first thing to happen when the emulator loads
	//prompts user
	//displays possible files to play (whether they are saved games or the original copies
	//allows user to pick file
	//returns the file that the user chose
	private String startScreen()
	{
		try {
			//props to http://patorjk.com/software/taag/ for the ASCII art
			this.gameDescriptionPrompt("title.txt", 100);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//print out all the text files the user can load
		ArrayList<String> txtFiles = TextFileFilter.finder("./");
		for(String i: txtFiles)
		{
			System.out.println(i + "\n");
			try {				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//allow the user to pick one of the items listed above
		Scanner in = new Scanner(System.in);
		String input = "";
		boolean fileNotChosen = true;
		while(fileNotChosen)
		{
			input = in.nextLine();
			if(txtFiles.contains(input))
			{
				fileNotChosen = false;
				System.out.println("Good pick! While we are loading that up, take a look at the basic commands. They are how you operate the game.");
			}
			else
				System.out.println("That's not an option. Please refer to the list above.");
		}
		
		try {
			this.gameDescriptionPrompt("basicCommands.txt", 200);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			this.gameDescriptionPrompt("loading.txt", 1200);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return input;
	}
	
	public static void main(String args[]) throws IOException
	{
		GameFacade game = new GameFacade();
		while (game.running)
		{
			game.run();
		}
	}
}

