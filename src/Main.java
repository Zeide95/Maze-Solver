import java.util.*;

public class Main {
    private static final Random random = new Random();
    private static final int rows = 11, cols = 21;
    private static final char wall = '#', path = ' ', step = '.';
    private static char[][] maze;

    public static void main(String[] args) {
        maze = generateMaze(rows, cols);
        printMaze();
        char[][] mazeWithPath = copyMaze();
        solve(1, 1, mazeWithPath);
        printCopy(mazeWithPath);
    }

    public static char[][] generateMaze(int rows, int cols) {
        maze = new char[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                maze[i][j] = wall;
            }
        }

        maze[1][0] = path; // вход
        maze[1][1] = path;

        carve(1, 1);

        maze[rows - 2][cols - 1] = path; // выход
        maze[rows - 2][cols - 2] = path;

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

            if (isInBounds(nx, ny) && maze[nx][ny] == wall) {
                maze[nx][ny] = path;
                maze[x + dx[dir] / 2][y + dy[dir] / 2] = path;
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

    public static boolean solve(int x, int y, char[][] maze2) {
        if (x < 0 || y < 0 || x >= rows || y >= cols) return false;

        if (maze2[x][y] == 'F') return true;

        if (maze2[x][y] == wall || maze2[x][y] == step || maze2[x][y] == 'S') return false;

        maze2[x][y] = step;

        if (solve(x, y + 1, maze2)) return true; // вправо
        if (solve(x - 1, y, maze2)) return true; // вверх
        if (solve(x, y - 1, maze2)) return true; // влево
        if (solve(x + 1, y, maze2)) return true; // вниз

        maze2[x][y] = path; // откат
        return false;
    }

    public static void printCopy(char[][] maze) {
        for (char[] row : maze) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

}