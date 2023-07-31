import java.util.Scanner;


public class MazeRunner {

	//initializing and declaring variables
	public static long startTime;
	public static long endTime;
	public static float elapsedTime;
	public static char[][] maze;
	public static int numberOfSteps = 0;
	public static int score = 0;
	public static int[] scores;
	public static int highscore = 0;
	public static boolean exit_game = false;
	public static Scanner scan = new Scanner(System.in);
	
	static void updateHighscore() {
		if (scores == null) {             //check if the array isn't initialized already
			scores = new int[1];      //initializes the array with length 1 when we know that the array isn't initialized yet
			scores[0] = score;        //the current score would go in the newly initialized array
			highscore = score;        //highscore is updated to the current score 
		}
		else {
			int len = scores.length;     
			int[] hs = new int[len + 1];    //initializing a new array with length one greater than the length of our scores array where we store all the scores. We are doing this to add a new score
			for (int i = 0; i < len; i++)      
				hs[i] = scores[i];       
			hs[len] = score;
			scores = hs;
			
			int max = scores[0];
			for (int j = 1; j < len + 1; j++)     //finding the max in the int array
	            if (scores[j] > max)
	                max = scores[j];
	         
			highscore = max;            //setting the max to highscore
		}
		
	}
	
	static void update_time() {
		endTime = System.nanoTime();     //end time
		float start = startTime;     //converting variables to float type
		float end = endTime;
		
		elapsedTime = (end-start)/1000000000;      //calculating the time elapsed and then converting micro seconds to seconds
	}
	
	static void playAgain() {
		System.out.print("Do you want to play again? (y/n) ");
		char cho = scan.next().charAt(0);
		if (cho == 'Y' || cho == 'y') {
			gameMenu();
		}
		else if (cho == 'N' || cho == 'n') {
			exitGame();
		}
		else
			System.out.println("Invalid Input");
	}
	
	//initialize the maze
	static void initializeMaze() {
		
		maze = new char[][] {{'#', '#', '#', '#', '#', '#', '#'}, 
				             {'#', 'P', '.', '.', '.', '.', '#'}, 
				             {'#', '#', '#', '#', '.', '#', '#'}, 
				             {'#', '.', '.', '.', '.', '.', '#'},
				             {'#', '#', '#', '.', '#', '#', '#'}, 
			 	             {'#', '.', '.', '.', '.', 'E', '#'}, 
				             {'#', '#', '#', '#', '#', '#', '#'}
			 	             };
	}

	static void gameMenu() {
		System.out.println("\n\na. Play Game\nb. Instructions\nc. Credits\n"
				+ "d. High Score\ne. Exit");
		System.out.print("Choose wisely (a/b/c/d/e): ");
		char choice = scan.next().charAt(0);
		if (Character.isUpperCase(choice)) {
			choice = Character.toLowerCase(choice);
		}
		switch(choice) {
		case 'a':
			startNewGame();
		case 'b':
			showInstructions();
		case 'c':
			showCredits();
		case 'd':
			showHighScore();
		case 'e':
			exitGame();
		default:
			System.out.println("Invalid Choice");
			break;
		}
		
	}
	
	static void printMaze() {
		System.out.println();
		for (int k=0; k<maze.length; k++) {
			for (int l=0; l<maze.length; l++) {
				System.out.print(maze[k][l]);
				}
			System.out.println();
			}
	}
	
	static Boolean isValidMove(int newX, int newY) {
		
		if (maze[newX][newY] == '#') {
			updateScore();
			return false;
		}
		return true;
	}
	
	static void movePlayer(char direction) {
		
		if (direction != '\0') {
			numberOfSteps += 1;
		}
		if (Character.isUpperCase(direction)) {
			direction = Character.toLowerCase(direction);
		}
		switch(direction) {
		
		case 'w':
			for (int i=0; i<maze.length; i++) {
				for (int j=0; j<maze[0].length; j++) {
					if (maze[i][j] == 'P') {
						if (isValidMove(i-1, j)) {
							maze[i][j] = '.';
							maze[i-1][j] = 'P';
							return;
							
						}
						else
							System.out.println("You Hit A Wall, Try Again");
					}
				}
			}
			break;
			
		case 's':
			for (int i=0; i<maze.length; i++) {
				for (int j=0; j<maze[0].length; j++) {
					if (maze[i][j] == 'P') {
						if (isValidMove(i+1, j)) {
							maze[i][j] = '.';
							maze[i+1][j] = 'P';
							return;
						}
						else
							System.out.println("You Hit A Wall, Try Again");
						
					}
				}
			}
			break;
			
		case 'a':
			for (int i=0; i<maze.length; i++) {
				for (int j=0; j<maze[0].length; j++) {
					if (maze[i][j] == 'P') {
						if (isValidMove(i, j-1)) {
							maze[i][j] = '.';
							maze[i][j-1] = 'P';
							return;
						}
						else
							System.out.println("You Hit A Wall, Try Again");
					}
				}
			}
			break;
			
		case 'd':
			for (int i=0; i<maze.length; i++) {
				for (int j=0; j<maze[0].length; j++) {
					if (maze[i][j] == 'P') {
						if (isValidMove(i, j+1)) {
							maze[i][j] = '.';
							maze[i][j+1] = 'P';
							return;
						}
						else
							System.out.println("You Hit A Wall, Try Again");
					}
				}
			}
			break;
		
		default:
			System.out.println("Invalid Direction");
			break;
		}
	}
	
	static boolean hasPlayerWon() {
		boolean res = true;
		for (int i=0; i<maze.length; i++) {
			for (int j=0; j<maze[0].length; j++) {
				if (maze[i][j] == 'P') {
					if (maze[i][j+1]!='E') {
						res = false;
					}
				}
			}
		}
		return res;
	}
	
	static void playGame() {
		
		char move;
		startTime = System.nanoTime();
		while (!exit_game) {
			System.out.println();
			printMaze();
			System.out.println();
			System.out.print("Enter your move: ");
			move = scan.next().charAt(0);
			update_time();
			movePlayer(move);
			if (hasPlayerWon()) {
				updateScore();
				displayResult();
				playAgain();
				
			}
			if (elapsedTime > 24) {
				System.out.println("You Ran Out of Time!!\nYou Lost!");
				playAgain();
			}
			
			
		}
		
			
	}
	
	static void displayResult() {
		
		
		System.out.println("\nYou Took: " + elapsedTime + " seconds");
		System.out.println("Your Score: "+score);
		showHighScore();
		System.out.println("Number of Steps: "+numberOfSteps );
	}
	
	static void updateScore() {
		if (elapsedTime<=5  && elapsedTime>0)
			score = 50000;
		else if (elapsedTime<=10 && elapsedTime>5)
			score = 10000;
		else if (elapsedTime<=15 && elapsedTime>10)
			score = 5000;
		else if (elapsedTime<=20 && elapsedTime>15)
			score = 1000;
		else if (elapsedTime<=22 && elapsedTime>20)
			score = 500;
		else
			score = 5;
		
	}
	
	static void startNewGame() {
		initializeMaze();
		startTime = 0;
		endTime = 0;
		elapsedTime = 0;
		numberOfSteps = 0;
		score = 0;
		playGame();
	}
	
	static void showInstructions() {
		System.out.println("1. You are suppossed to get to E in the maze, where  \'#\' represents walls that are impassable, "+
				   "\'.\' represents open paths that you can move through,"+
				   " P represents the starting position of the player, "+
				   "E represents the exit point that the player needs to reach.\n2."+
				   "You are supposed to move using the \'w\' (up), \'s\' (down), \'a\' (left), \'d\' (right) "+
				   "\n3. If you don't complete the maze under 25 seconds, you will loose."+
				   "\n4. When you complete the maze in time, you will be awarded score with respect to how quick you were in completing");
	}
	
	static void showCredits() {
		System.out.println("Muhammad Abdullah Qureshi\n221-437659");
	}
	static void showHighScore() {
		updateHighscore();
		System.out.println("High Score: "+highscore);
	}
	
	static void exitGame() {
		System.out.println("Exiting the Game.......");
		exit_game = true;
		System.exit(0);
	}
	
	
	




	public static void main(String[] args) {
		System.out.println("Welcome to the \"Maze Runner\" game!");
		
		gameMenu();
		
	}

}

