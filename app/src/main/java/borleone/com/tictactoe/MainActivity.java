package borleone.com.tictactoe;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Board tictactoe;
    private Button[][] mGrid;
    private Button mNewGame, mUndo;
    private TextView mGameStatus;
    private boolean isGameActive;
    final ObjectAnimator[] animator = new ObjectAnimator[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGrid = new Button[3][3];
        mGrid[0][0] = (Button) findViewById(R.id.grid_0);
        mGrid[0][1] = (Button) findViewById(R.id.grid_1);
        mGrid[0][2] = (Button) findViewById(R.id.grid_2);
        mGrid[1][0] = (Button) findViewById(R.id.grid_3);
        mGrid[1][1] = (Button) findViewById(R.id.grid_4);
        mGrid[1][2] = (Button) findViewById(R.id.grid_5);
        mGrid[2][0] = (Button) findViewById(R.id.grid_6);
        mGrid[2][1] = (Button) findViewById(R.id.grid_7);
        mGrid[2][2] = (Button) findViewById(R.id.grid_8);

        mNewGame = (Button) findViewById(R.id.button_new_game);
        mUndo = (Button) findViewById(R.id.button_undo);
        mGameStatus = (TextView) findViewById(R.id.textView_status);

        mGrid[0][0].setOnClickListener(this);
        mGrid[0][1].setOnClickListener(this);
        mGrid[0][2].setOnClickListener(this);
        mGrid[1][0].setOnClickListener(this);
        mGrid[1][1].setOnClickListener(this);
        mGrid[1][2].setOnClickListener(this);
        mGrid[2][0].setOnClickListener(this);
        mGrid[2][1].setOnClickListener(this);
        mGrid[2][2].setOnClickListener(this);
        mNewGame.setOnClickListener(this);
        mUndo.setOnClickListener(this);

        tictactoe = new Board();
        isGameActive = true;
        tictactoe.newGame();

        animator[0] = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.blink);
        animator[1] = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.blink);
        animator[2] = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.blink);

    }

    @Override
    public void onClick(View v) {
        int row, col;

        if (isGameActive) {
            switch (v.getId()) {
                case R.id.grid_0:
                    row = 0;
                    col = 0;
                    if (tictactoe.getValue(row, col) == 0) {
                        tictactoe.play(row, col);
                        mGrid[row][col].setText(tictactoe.getValue(row, col) == 1 ? "X" : "O");
                    }
                    break;
                case R.id.grid_1:
                    row = 0;
                    col = 1;
                    if (tictactoe.getValue(row, col) == 0) {
                        tictactoe.play(row, col);
                        mGrid[row][col].setText(tictactoe.getValue(row, col) == 1 ? "X" : "O");
                    }
                    break;
                case R.id.grid_2:
                    row = 0;
                    col = 2;
                    if (tictactoe.getValue(row, col) == 0) {
                        tictactoe.play(row, col);
                        mGrid[row][col].setText(tictactoe.getValue(row, col) == 1 ? "X" : "O");
                    }
                    break;
                case R.id.grid_3:
                    row = 1;
                    col = 0;
                    if (tictactoe.getValue(row, col) == 0) {
                        tictactoe.play(row, col);
                        mGrid[row][col].setText(tictactoe.getValue(row, col) == 1 ? "X" : "O");
                    }
                    break;
                case R.id.grid_4:
                    row = 1;
                    col = 1;
                    if (tictactoe.getValue(row, col) == 0) {
                        tictactoe.play(row, col);
                        mGrid[row][col].setText(tictactoe.getValue(row, col) == 1 ? "X" : "O");
                    }
                    break;
                case R.id.grid_5:
                    row = 1;
                    col = 2;
                    if (tictactoe.getValue(row, col) == 0) {
                        tictactoe.play(row, col);
                        mGrid[row][col].setText(tictactoe.getValue(row, col) == 1 ? "X" : "O");
                    }
                    break;
                case R.id.grid_6:
                    row = 2;
                    col = 0;
                    if (tictactoe.getValue(row, col) == 0) {
                        tictactoe.play(row, col);
                        mGrid[row][col].setText(tictactoe.getValue(row, col) == 1 ? "X" : "O");
                    }
                    break;
                case R.id.grid_7:
                    row = 2;
                    col = 1;
                    if (tictactoe.getValue(row, col) == 0) {
                        tictactoe.play(row, col);
                        mGrid[row][col].setText(tictactoe.getValue(row, col) == 1 ? "X" : "O");
                    }
                    break;
                case R.id.grid_8:
                    row = 2;
                    col = 2;
                    if (tictactoe.getValue(row, col) == 0) {
                        tictactoe.play(row, col);
                        mGrid[row][col].setText(tictactoe.getValue(row, col) == 1 ? "X" : "O");
                    }
                    break;
            }

            mGameStatus.setText(tictactoe.getTurn() == 1 ? getString(R.string.x_turn) : getString(R.string.o_turn));

            int result = tictactoe.getResult();
            if (result >= 0) {
                isGameActive = false;
                String gameResult = null;
                ArrayList<Pair> list_places;
                switch (result) {
                    case 0:
                        gameResult = getString(R.string.game_draw);
                        break;
                    case 1:
                        gameResult = getString(R.string.x_win);
                        list_places = tictactoe.getWin_places();
                        blinkPlaces(list_places);
                        break;
                    case 2:
                        gameResult = getString(R.string.o_win);
                        list_places = tictactoe.getWin_places();
                        blinkPlaces(list_places);
                        break;
                }
                mGameStatus.setText(gameResult);
            }
        }

        switch (v.getId()) {
            case R.id.button_new_game:
                tictactoe.newGame();
                isGameActive = true;
                mGameStatus.setText(R.string.x_turn);
                for (int i = 0; i < 3; ++i) {
                    animator[i].end();
                    for (int j = 0; j < 3; ++j) {
                        mGrid[i][j].setText("");
                        mGrid[i][j].setTextColor(getColor(R.color.white));
                    }
                }
                break;
            case R.id.button_undo:
                Pair position = tictactoe.undo();
                if (position != null) {
                    row = (int) position.first;
                    col = (int) position.second;
                    mGrid[row][col].setText("");
                    mGameStatus.setText(tictactoe.getTurn() == 1 ? getString(R.string.x_turn) : getString(R.string.o_turn));
                    if (!isGameActive) {
                        ArrayList<Pair> list_places = tictactoe.getWin_places();
                        for (int i = 0; i < 3; ++i)
                            animator[i].end();
                        for (Pair coordinate : list_places)
                            mGrid[(int) coordinate.first][(int) coordinate.second].setTextColor(getColor(R.color.white));
                        isGameActive = true;
                    }
                } else Toast.makeText(this, "Game not started", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // Blink the winning combination of X's or O's
    private void blinkPlaces(ArrayList<Pair> list_places) {
        int i = 0;
        for (Pair coordinate : list_places) {
            animator[i].setTarget(mGrid[(int) coordinate.first][(int) coordinate.second]);
            animator[i++].start();
        }
    }
}
