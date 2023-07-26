import java.util.Scanner;


public class MazeRunner {
	
	
	public static char[][] maze = new char[7][7];
	public static int numberOfSteps = 0;
	public static int score = 0;
	public static int highscore = 0;
	
	//initialize the maze
	static void initializeMaze() {
		for (int i=0; i<maze.length; i++) {
			for (int j=0; j<maze.length; j++) {
				if (i>0 && i<maze.length-1 && j>0 && j<maze.length-1) {
					if (i==1 && j==1)
						maze[i][j] = 'P';
					else if (i==5 && j==5)
						maze[i][j] = 'E';
					else if (i==2 && j>0 && j<4 && j==5)
						System.out.println("safsg");
//						maze[i][j] = '#';
					else
						maze[i][j] = '.';
						
//					if (i==1 && j<maze.length-1)
//						maze[i][j] = '.';
					
				}
				else {
					maze[i][j] = '#';
				}	
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
		return true;
	}
	
	static void movePlayer(char direction) {
		System.out.println();
	}
	
	static Boolean hasPlayerWon() {
		return true;
	}
	
	static void playGame() {
		System.out.println();
	}
	
	static void displayResult() {
		System.out.println();
	}
	
	static void updateScore() {
		System.out.println();
	}
	
	static void startNewGame() {
		System.out.println();
	}
	
	static void showInstructions() {
		System.out.println();
	}
	
	static void exitGame() {
		System.out.println();
	}
	
	
	




	public static void main(String[] args) {
		long startTime = System.nanoTime();
		float start = startTime;
		System.out.println("Welcome to the \"Maze Runner\" game!"
				+ "\n\na. Play Game\nb. Instructions\nc. Credits\n"
				+ "d. High Score\ne. Exit");
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Choose wisely (a/b/c/d/e): ");
		char choice = scan.next().charAt(0);
		if (Character.isUpperCase(choice)) {
			choice = Character.toLowerCase(choice);
		}
		switch(choice) {
		case 'a':
			
		
		}
		initializeMaze();
		printMaze();
		long endTime = System.nanoTime();
		float end = endTime;
		
		float duration = (end-start)/1000000000;
		
		System.out.println("\nTimed: "+duration + " s");
	}

}

