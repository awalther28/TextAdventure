Allison Walther

Trigger Spec Clarification for my game:
	Enter Text Trigger
		the user can trigger this by entering the text wherever
	
	Items In Rooms
		these can only be triggered when the user drops an item in a room
		nothing happens when another triggers puts items in a room
		
	Items In Inventory
		these can only be triggered when the use picks up an item from a room
		
	Effects:
		Delete Item From Game & Add Item To Game
			will only work if you give a specific room for the item to be added or deleted

Start Screen & Loading Games:
	Right now, there is no easy way to quit out of the start screen.
	If you want to load a game file, make sure it is located in the same directory that the emulator is located in.
	Please do not save games as loading.txt, basicCommands.txt, or titles.txt... it will write over my text files.
	
Additions:
	The text file is set up as normal. If the game creator would like to add a preface to the adventure, they may do so by 
	writing on the next newline (putting whatever you want in the ...): 
	PRINT
	...
	...
	...
	END
	When loading a saved game, the timestamp from when the game was saved will be printed along with the preface.
	
"PoorSapAndWickedWitch":
	I shamelessly stole the riddles for the potion ingredients from http://www.geeknative.com/3031/ride-the-riddles/
	I am not that clever. The story line is a Walther original. 