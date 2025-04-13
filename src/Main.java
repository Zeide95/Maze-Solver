import java.util.*;

public class Main {
    public static void main(String[] args) {
        int rows = 15;
        int cols = 15;
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
        maze[startX][1] = ' ';

        carve(startX, 1, maze, random);

        // выход справа
        for (int i = rows - 2; i > 0; i--) {
            if (maze[i][cols - 2] == ' ') {
                maze[i][cols - 1] = ' ';
                break;
            }
        }

        return maze;
    }

    public static void carve(int x, int y, char[][] maze, Random random) {
        int[] dx = {0, 0, -2, 2};
        int[] dy = {-2, 2, 0, 0};
        Integer[] directions = {0, 1, 2, 3};
        Collections.shuffle(Arrays.asList(directions), random);

        for (int dir : directions) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (isInBounds(nx, ny, maze) && maze[nx][ny] == '#') {
                maze[nx][ny] = ' ';
                maze[x + dx[dir] / 2][y + dy[dir] / 2] = ' ';
                carve(nx, ny, maze, random);
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