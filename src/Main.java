import java.util.*;

public class Main {
    public static void main(String[] args) {
        int rows = 11;
        int cols = 11;
        char[][] maze = generateMaze(rows, cols);
        printMaze(maze);

    }

    public static char[][] generateMaze(int rows, int cols) {
        Random random = new Random();
        char[][] maze = new char[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                maze[i][j] = '#';
            }
        }

        int startX = 1 + 2 * random.nextInt((rows - 2) / 2); // случайный нечётный индекс
        int startY = 0; // левая граница
        maze[startX][startY] = ' '; // вход

        int endX = 1 + 2 * random.nextInt((rows - 2) / 2);
        int endY = cols - 1; // правая граница
        maze[endX][endY] = ' '; // выход

        return maze;
    }

    public static void printMaze(char[][] maze) {
        for (char[] row : maze) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

}