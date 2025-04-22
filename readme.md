# Maze Generation and Recursive Pathfinding

This project generates a random maze and solves it using a recursive backtracking algorithm.

## Features
- **Maze Generation**: Uses recursive backtracking to carve random paths through the maze, ensuring a solvable structure.
- **Pathfinding**: Implements a depth-first search (DFS) algorithm to find the path from start ('S') to finish ('F').
- **Backtracking**: If a path leads to a dead-end, the algorithm backtracks and tries other directions.

## How It Works
1. **Generate Maze**: The maze is initialized with walls ('#') and carved using `carve()`. The start point ('S') and finish ('F') are placed at specific positions.
2. **Solve Maze**: The `solve()` method is used to explore the maze recursively. It marks cells as visited and backtracks when it hits a dead-end.
3. **Backtracking**: If a direction leads to a dead-end, the algorithm "unmarks" cells and tries another path.

## Methods
- **`generateMaze()`**: Initializes the maze and carves paths.
- **`carve()`**: Recursively carves the maze paths in random directions.
- **`solve()`**: Solves the maze using DFS and backtracking.
- **`printMaze()`**: Displays the maze.