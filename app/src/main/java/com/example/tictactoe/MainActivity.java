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

    String turn; // התור הנוכחי ("X" או "O")
    String[][] board; // הלוח (3x3)
    int count; // מספר הלחיצות על הלוח

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // טיפול ב-padding על כפתורים כאשר יש מערכת ברים
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_02), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        onNewGame(); // אתחול של משחק חדש
    }

    // אתחול לוח חדש
    private void onNewGame() {
        board = new String[3][3];
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                board[row][col] = ""; // כל שדה ריק בהתחלה

        turn = "X"; // מתחילים תמיד ב-X
        count = 0; // מספר לחיצות
    }

    // טיפול בלחיצה על כפתור
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
        if (view.getId() == R.id.btn_22)
            handleClick(2, 2, R.id.btn_22);
    }

    // טיפול בסיום תור (בדיקת מנצח או חילוף תור)
    private void onTurnEnd() {
        if (isWinner()) {
            endGame(turn + " won!");
        } else if (count == 9) { // אם הלוח מלא, אז יש תיקו
            endGame("It's a draw!");
        } else {
            // החלפת תור בין X ל-O
            turn = turn.equals("X") ? "O" : "X";
        }
    }

    // סיום המשחק עם הודעה על מנצח
    private void endGame(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");
        builder.setMessage(message);
        builder.setPositiveButton("New Game", (dialogInterface, i) -> onNewGame());
        builder.setNegativeButton("Exit", (dialogInterface, i) -> finish());
        builder.show();
    }

    // פונקציה לבדוק אם יש מנצח
    private boolean isWinner() {
        // בדיקת שורות
        for (int row = 0; row < 3; row++) {
            if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2]) && !board[row][0].equals("")) {
                return true;
            }
        }
        // בדיקת עמודות
        for (int col = 0; col < 3; col++) {
            if (board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col]) && !board[0][col].equals("")) {
                return true;
            }
        }
        // בדיקת אלכסונים
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            return true;
        }
        return false;
    }

    // טיפול בלחיצה על כפתור (הוספת X או O ללוח)
    private void handleClick(int row, int col, int id) {
        if (board[row][col].equals("")) { // רק אם השדה ריק
            board[row][col] = turn; // שמור את התור הנוכחי ("X" או "O")
            Button btn = findViewById(id);
            btn.setText(turn); // עדכון הטקסט בכפתור (הצגת X או O)
            count++; // הוספת לחיצה
            onTurnEnd(); // סיום תור והחלפה בין השחקנים
        }
    }
}
