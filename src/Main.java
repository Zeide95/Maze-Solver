import java.util.*;

public class Main {
    private static final Random random = new Random();
    private static final int rows = 11;
    private static final int cols = 15;
    private static final char[][] maze = generateMaze(rows, cols);

    public static void main(String[] args) {
        printMaze(maze);

    }

    public static char[][] generateMaze(int rows, int cols) {

        char[][] maze = new char[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                maze[i][j] = '#';
            }
        }

        int startX = 1, startY = 0; // левая граница
        maze[startX][startY] = ' '; // вход
        maze[startX][1] = ' ';

        carve(startX, 1, maze);

        // выход справа
        for (int i = rows - 2; i > 0; i--) {
            if (maze[i][cols - 2] == ' ') {
                maze[i][cols - 1] = ' ';
                break;
            }
        }

        return maze;
    }

    public static void carve(int x, int y, char[][] maze) {
        int[] dx = {0, 0, -2, 2};
        int[] dy = {-2, 2, 0, 0};
        List<Integer> directions = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(directions, random);

        for (int dir : directions) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (isInBounds(nx, ny, maze) && maze[nx][ny] == '#') {
                maze[nx][ny] = ' ';
                maze[x + dx[dir] / 2][y + dy[dir] / 2] = ' ';
                carve(nx, ny, maze);
            }
        }
    }

    public static boolean isInBounds(int x, int y, char[][] maze) {
        return x > 0 && x < maze.length - 1 && y > 0 && y < maze[0].length - 1;
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