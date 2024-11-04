package com.example.tictactoe;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_02), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
            public class MainActivity extends AppCompatActivity {
                String turn;
                String[][] board;
                int count;
                private void onNewGame() {
                    board = new String[3][3];
                    for (int row=0; row < 3; row++)
                        for (int col=0; col < 3; col++)
                            board[row][col] = new String();

                    turn = "X";
                    count = 0;
                }
            if(view.getId() == R.id.btn00)
                handleClick(0,0,r.id.btn_00);
            if(view.getId() ==R.id.btn_01)
                handleClick(0,1,r.id.btn_00);
            });
    }
}