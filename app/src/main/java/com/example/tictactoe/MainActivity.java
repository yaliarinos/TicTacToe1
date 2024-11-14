package com.example.tictactoe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String turn;
    String[][] board;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_02), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        onNewGame();
    }


    private void onNewGame() {
        board = new String[3][3];
        for (int row=0; row < 3; row++)
            for (int col=0; col < 3; col++)
                board[row][col] = new String();

        turn = "X";
        count = 0;
    }


    public void onButtonClick(View view) {
        if (view.getId() == R.id.btn_00)
            handleClick(0, 0, R.id.btn_00);
        if (view.getId() == R.id.btn_01)
            handleClick(0, 1, R.id.btn_01);
        if (view.getId() == R.id.btn_02)
            handleClick(0, 2, R.id.btn_02);
        if (view.getId() == R.id.btn_10)
            handleClick(1, 0, R.id.btn_10);
        if (view.getId() == R.id.btn_11)
            handleClick(1, 1, R.id.btn_11);
        if (view.getId() == R.id.btn_12)
            handleClick(1, 2, R.id.btn_12);
        if (view.getId() == R.id.btn_20)
            handleClick(2, 0, R.id.btn_20);
        if (view.getId() == R.id.btn_21)
            handleClick(2, 1, R.id.btn_21);
        if (view.getId() == R.id.btn_02)
            handleClick(0, 2, R.id.btn_02);
    }

    private void onTurnEnd() {
            // בדיקה האם יש מנצח - חשוב לבצע לפני הבדיקה אם הלוח מלא
            if (isWinner())
                endGame(turn + " won!");
            else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("More Info");
            String msg = "This is the message body";
            builder.setMessage(msg);
            builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
// Exit handling

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
// Cancel handling

                    }
                });
                AlertDialog dialog = builder.show();
            }

        }

    private void endGame(String s) {
    }

    private boolean isWinner() {
        return false;
    }

    private void handleClick(int row, int col, int id) {
        if (board[row][col].equals("")) {
            board[row][col] = turn;
            Button btn = findViewById(id);
            btn.setText(turn);
            onTurnEnd();
        }
    }

}


