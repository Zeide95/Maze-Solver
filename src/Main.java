import java.util.*;

public class Main {
    private static final Random random = new Random();
    private static final int rows = 11;
    private static final int cols = 11;
    private static char[][] maze;

    public static void main(String[] args) {
        maze = generateMaze(rows, cols);
        printMaze();

    }

    public static char[][] generateMaze(int rows, int cols) {
        maze = new char[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                maze[i][j] = '#';
            }
        }

        int startX = 1, startY = 0; // левая граница
        int endX = rows - 2, endY = cols - 1; // правая граница
        maze[startX][startY] = ' '; // вход
        maze[startX][1] = ' ';

        carve(startX, 1);

        maze[endX][endY] = ' '; // выход
        maze[endX][endY - 1] = ' ';

        return maze;
    }

    public static void carve(int x, int y) {
        int[] dx = {0, 0, -2, 2};
        int[] dy = {-2, 2, 0, 0};
        List<Integer> directions = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(directions, random);

        for (int dir : directions) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (isInBounds(nx, ny) && maze[nx][ny] == '#') {
                maze[nx][ny] = ' ';
                maze[x + dx[dir] / 2][y + dy[dir] / 2] = ' ';
                carve(nx, ny);
            }
        }
    }

    public static boolean isInBounds(int x, int y) {
        return x > 0 && x < maze.length - 1 && y > 0 && y < maze[0].length - 1;
    }

    public static void printMaze() {
        for (char[] row : maze) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static char[][] copyMaze() {
        char[][] maze2 = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(maze[i], 0, maze2[i], 0, cols);
        }

        maze2[1][0] = 'S';
        maze2[rows - 2][cols - 1] = 'F';

        return maze2;
    }

}