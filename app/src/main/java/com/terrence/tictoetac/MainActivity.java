package com.terrence.tictoetac;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0=silver, 1=gold
    int activeplayer = 0;

    // 2=unplayed
    int[] gameStates = {2,2,2,2,2,2,2,2,2};

    boolean gameisActive = true;

    int [] [] winningPostions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropClick(View view){
        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameStates[tappedCounter]==2 && gameisActive){

        gameStates[tappedCounter] = activeplayer;

        counter.setTranslationY(-1000f);

        if(activeplayer == 0) {
            counter.setImageResource(R.drawable.golden);
            activeplayer = 1;
        }
        else {
            counter.setImageResource(R.drawable.silver);
            activeplayer = 0;
        }
        counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition:winningPostions){
                if(gameStates[winningPosition[0]] == gameStates[winningPosition[1]]
                        && gameStates[winningPosition[1]] == gameStates[winningPosition[2]]
                        && gameStates[winningPosition[0]] != 2){

                    //When someone won
                    gameisActive = false;
                    String winner = "Silver";

                    if(gameStates[winningPosition[0]] == 0){
                        winner = "Yellow";
                    }

                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else{

                    boolean gameisOver = true;

                    for(int counterState : gameStates){
                        if (counterState == 2) gameisOver = false;
                    }

                    if(gameisOver){
                        TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's draw");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        gameisActive = true;

        activeplayer = 0;

        for (int i = 0; i < gameStates.length; i++)
        {
            gameStates[i] = 2;
        }

            GridLayout gridLayout = (GridLayout)findViewById(R.id.GridLayout);

            for(int i = 0; i < gridLayout.getChildCount();i++ )
            {
                ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
