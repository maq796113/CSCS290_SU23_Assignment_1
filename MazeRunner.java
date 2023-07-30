import java.util.Scanner;


public class MazeRunner {
	
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
	
	
	static void update_time() {
		endTime = System.nanoTime();
		float start = startTime;
		float end = endTime;
		
		elapsedTime = (end-start)/1000000000;
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
		System.out.println("1. When");
	}
	
	static void showCredits() {
		System.out.println("Muhammad Abdullah Qureshi\n221-437659");
	}
	static void showHighScore() {
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

