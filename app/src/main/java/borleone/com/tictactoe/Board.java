package borleone.com.tictactoe;

import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class Board {

    private int[][] grid;
    private int turn = 1;
    private Stack<Integer> playOrder;
    private ArrayList<Pair> win_places = new ArrayList<>();

    Board() {
        grid = new int[3][3];
        playOrder = new Stack<>();
    }

    public void newGame() {
        // Initialize all cells in grid
        for (int[] row : grid)
            Arrays.fill(row, 0);

        // Initialize Player 1's turn
        turn = 1;

        // Clear stack of playing order
        playOrder.clear();
        if (!win_places.isEmpty()) win_places.clear();
    }

    public void play(int row, int col) {
        grid[row][col] = turn;
        turn = (turn == 1) ? 2 : 1;
        playOrder.push((row * 3) + col);
    }

    public int getValue(int row, int col) {
        return grid[row][col];
    }

    public int getResult() {
        int i;
        if (!win_places.isEmpty()) win_places.clear();

        for (i = 0; i < 3; ++i)
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][0] != 0) {
                win_places.add(new Pair<>(i, 0));
                win_places.add(new Pair<>(i, 1));
                win_places.add(new Pair<>(i, 2));
                return grid[i][0];
            }

        for (i = 0; i < 3; ++i)
            if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[0][i] != 0) {
                win_places.add(new Pair<>(0, i));
                win_places.add(new Pair<>(1, i));
                win_places.add(new Pair<>(2, i));
                return grid[0][i];
            }

        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] != 0) {
            win_places.add(new Pair<>(0, 0));
            win_places.add(new Pair<>(1, 1));
            win_places.add(new Pair<>(2, 2));
            return grid[0][0];
        }

        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] != 0) {
            win_places.add(new Pair<>(0, 2));
            win_places.add(new Pair<>(1, 1));
            win_places.add(new Pair<>(2, 0));
            return grid[0][2];
        }

        //Draw
        if (playOrder.size() == 9)
            return 0;

        return -1;
    }

    public int getTurn() {
        return turn;
    }

    public ArrayList<Pair> getWin_places() {
        return win_places;
    }

    public Pair undo() {
        // Undo disable for first turn
        if (playOrder.empty()) return null;

        int lastPlayed = playOrder.pop();
        int row, col;
        row = lastPlayed / 3;
        col = lastPlayed % 3;
        grid[row][col] = 0;
        turn = (turn == 1) ? 2 : 1;
        return new Pair<>(row, col);
    }
}
