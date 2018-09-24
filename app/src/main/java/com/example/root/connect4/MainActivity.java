package com.example.root.connect4;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 = Yellow 1=Red
    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},
                                {0,3,6},{1,4,7},{2,5,8},
                                {0,4,8},{2,4,6}};
    boolean isGameActive = true;

    int red;
    int yellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        red = 0;
        yellow = 0;

    }

    public void viewScore(View view){
        ConstraintLayout scoreLayout = findViewById(R.id.scoreLayout);
        scoreLayout.setVisibility(View.VISIBLE);

    }

//

    public void playAgain(View view){
        isGameActive = true;
        ConstraintLayout scoreLayout = findViewById(R.id.scoreLayout);
        scoreLayout.setVisibility(View.INVISIBLE);
        ConstraintLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(view.INVISIBLE);
        activePlayer = 0;
        for (int i=0 ; i <gameState.length; i++){
            gameState[i] = 2;
        }
        ConstraintLayout gridlayout = findViewById(R.id.gridLayout);
        for (int i=0; i<gridlayout.getChildCount();i++){
            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);
        }

    }

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if( gameState[tappedCounter] == 2 && isGameActive) {

            counter.setTranslationY(-1000f);
            gameState[tappedCounter] = activePlayer;
            Log.i("TagPress", counter.getTag().toString());
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.xx);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.oo);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            TextView winnerMessage = findViewById(R.id.winnerMessage);
            TextView redScore = findViewById(R.id.redScore);
            TextView yellowScore = findViewById(R.id.yellowScore);


            String winner;
            for (int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=2){


                        isGameActive = false;
                        if(gameState[winningPosition[0]] == 0){
                            //Toast.makeText(getApplicationContext(),"Yellow has won!!",Toast.LENGTH_SHORT).show();
                            winner = "Yellow";
                            yellow++;
                            System.out.println(yellow);
                        }
                        else{
                            //Toast.makeText(getApplicationContext(),"Red has won!",Toast.LENGTH_SHORT).show();
                            winner = "Red";
                            red++;
                            System.out.println(red);
                        }


                    winnerMessage.setText(winner + " has won !!" );
                        redScore.setText(String.valueOf(red));
                        yellowScore.setText(String.valueOf(yellow));
                    ConstraintLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    //LinearLayout layout = findViewById(R.id.playAgainLayout);
                    //layout.setVisibility(View.VISIBLE);
                }
                  else {
                    boolean gameIsOver = true;
                    for (int counterState: gameState){
                        if(counterState == 2) gameIsOver = false;
                    }
                    if(gameIsOver){
                        winnerMessage.setText("It's a Draw");
                        redScore.setText(String.valueOf(red));
                        yellowScore.setText(String.valueOf(yellow));
                        ConstraintLayout layout = findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }


        }
    }
}
