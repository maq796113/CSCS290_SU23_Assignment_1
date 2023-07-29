import java.util.Scanner;


public class MazeRunner {
	
	public static long startTime;
	public static long endTime;
	public static char[][] maze;
	public static int numberOfSteps = 0;
	public static int score = 0;
	public static int highscore = 0;
	public static boolean exit_game = false;
	
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
		System.out.println("Welcome to the \"Maze Runner\" game!"
				+ "\n\na. Play Game\nb. Instructions\nc. Credits\n"
				+ "d. High Score\ne. Exit");
		
		try (Scanner scan = new Scanner(System.in)) {
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
			return false;
		}
		return true;
	}
	
	static void movePlayer(char direction) {
		
		
		switch(direction) {
		
		case 'w':
			for (int i=0; i<maze.length; i++) {
				for (int j=0; j<maze[0].length; j++) {
					if (maze[i][j] == 'P') {
						if (isValidMove(i-1, j)) {
							maze[i][j] = '.';
							maze[i-1][j] = 'P';
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
		Scanner scan = new Scanner(System.in);
		char move;
		while (!exit_game) {
			startTime = System.nanoTime();
			System.out.println();
			printMaze();
			System.out.println();
			System.out.print("Enter your move: ");
			move = scan.next().charAt(0);
			movePlayer(move);
			if (hasPlayerWon()) {
				endTime = System.nanoTime();
				displayResult();
			}
			
			
		}
			
	}
	
	static void displayResult() {
		float start = startTime;
		float end = endTime;
		
		float duration = (end-start)/1000000000;
		
		System.out.println("\nYou Took: " + duration + " s");
		System.out.println("Your Score: "+score);

	}
	
	static void updateScore() {
		score += 1;
	}
	
	static void startNewGame() {
		initializeMaze();
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
		System.exit(0);
	}
	
	
	




	public static void main(String[] args) {
		
		
		gameMenu();
		
	}

}

