package fr.iut.projet_dev_mobil;


//import android.annotation.SuppressLint;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class GameLib {
    private final int [] colors = new int[50];
    private int checkPosition =0;
    private int roundCounter = 0 ;
    private int Position = 0;
    private int life;
    private final double def_score;
    private double score = 0;
    private boolean alive = false;
    private boolean win = false;
    private boolean end = false;
    private final TextView labelLife;
    private final TextView labelPalier;
    private final Button buttonStart;
    private final TextView score_value;
    private final TextView life_TextView;

    private final int defaultColor;
    private final int winCondition;
    private final int palier;

    private final double factor;

    //list of gaming buttons
    private final List<Button> buttons;
    //list of color in same order than gaming button
    private final int[] arrayColor;


    @SuppressLint("SetTextI18n")
    GameLib(int defaultColor,
            int winCondition,
            int defaultLife,
            List<Button> buttons,
            double factor,
            int[] arrayColor,
            int palier,
            TextView labelScore,
            TextView labelLife,
            Button buttonStart,
            TextView labelPalier,
            double score) {


        life = defaultLife;
        this.defaultColor = defaultColor;
        this.winCondition = winCondition;
        this.factor = factor;
        this.buttons = buttons;
        this.arrayColor = arrayColor;
        this.palier = palier;
        this.labelLife = labelLife;
        this.buttonStart = buttonStart;
        this.labelPalier = labelPalier;
        this.def_score = score;

        checkSiButtonExist();

        score_value = labelScore;
        score_value.setText("points :" + score);
        life_TextView = labelLife;


        buttonStart.setVisibility(View.INVISIBLE);

        int i = 0;
        for (final Button myButton : buttons) {
            final int  myColor = arrayColor[i];
            myButton.setBackgroundColor(myColor);
            myButton.setAlpha(0.5f);
            i++;
        }

        buttonStart.setOnClickListener(view -> newSequence());

        newGame();

        i = 0;

        for (final Button myButton : buttons) {
            final int  myColor = arrayColor[i];

            myButton.setOnClickListener(view -> {
                colorCheck(myColor);
                colorHighlight(myButton);
            });
            i++;
        }


        Thread thread = new Thread(){
            public void run(){

            }
        };
        thread.start();
    }

    //Vérification de l'existance des boutons
    private void checkSiButtonExist() {
        for (Button button : buttons) {
            String checking = button.getText().toString();
            Log.i("checkfor :", checking);
        }
    }

    //Création de la séquence du jeu
    @SuppressLint("SetTextI18n")
    private void newSequence() {
        // 4 the win :
        if (alive && roundCounter == winCondition) {
            win = true;
            message(buttonStart, "Gagné !");
            end();

        }else {

            Random rd = new Random();

            int[] possibleColors = arrayColor;

            //Setup tour 1 avec le defaultColor
            if (roundCounter == 0) {
                for (int i = 0; i < defaultColor; i++) {
                    colors[roundCounter] = possibleColors[rd.nextInt(buttons.size())];
                    roundCounter++;
                }
            } else{
                //Création d'une nouvelle couleur et l'ajoute au tableau au numéro correspondant à l'indice du palier
                colors[roundCounter] = possibleColors[rd.nextInt(buttons.size())];
                roundCounter++;
            }
            labelPalier.setText("Palier : " + (roundCounter));
            setSequence();
            alive =true;
            checkPosition =0;
        }
    }

    //Affiche les couleurs sur le boutons
    private void setSequence(){

        final View background= buttonStart;

        showSequence(background);

        background.postDelayed(() -> {

            updateBackground(Color.GRAY);

            background.setAlpha(1f);

            for(Button myButton: buttons){
                myButton.setEnabled(true);
            }
        },(roundCounter +1)*500);

        buttonStart.setEnabled(false);

    }

    //change le fond du buttonStart
    private void updateBackground(int color){

         buttonStart.setBackgroundColor(color);

    }

    @SuppressLint("SetTextI18n")
    private void newGame() {
        Button startButton = buttonStart;
        startButton.setBackgroundColor(Color.GRAY);
        roundCounter = 0;
        score = def_score;
        score_value.setText("Points : " + score);
        life_TextView.setText("Vies :" + life);
        startButton.setEnabled(true);
        startButton.setVisibility(View.VISIBLE);
        labelPalier.setText("Palier : " + roundCounter);

    }


    //Verification de la couleur du jeu et celle choisie par le joueur
    @SuppressLint("SetTextI18n")
    private void colorCheck(int color) {
        if (alive) {
            if (color == colors[checkPosition]) {
                checkPosition++;

            } else {
                life--;
                life_TextView.setText("Vies :" + life);
                if (life <= 0)
                    alive = false;
                if (life > 0)
                    newGame();
            }
            isDead();


        }
    }
    @SuppressLint("SetTextI18n")
    private void isDead(){
            if (checkPosition == roundCounter && alive) {
                score += palier * factor;
                score_value.setText("Points : " + score);

                newSequence();

                for (Button myButton : buttons)
                    myButton.setEnabled(false);
            }

        if(!alive && roundCounter >0){
            message(buttonStart,"Game over");
            end();
        }
    }

    private void end(){
           end =  true;
    }

    @SuppressLint("SetTextI18n")
    private void message(final Button startButton, final String msg){
        startButton.postDelayed(() -> {

            for(Button myButton: buttons)
                myButton.setVisibility(View.INVISIBLE);

            labelPalier.setVisibility(View.INVISIBLE);

            labelLife.setVisibility(View.INVISIBLE);

            startButton.setBackgroundColor(Color.BLACK);
            startButton.setTextColor(Color.WHITE);
            startButton.setText(msg);

        },50);

        startButton.postDelayed(() -> {

            startButton.setText("Jouer");
            startButton.setBackgroundColor(Color.GRAY);
            startButton.setTextColor(Color.BLACK);
            TextView t= labelPalier;
            t.setText("Palier : 0");
            labelLife.setVisibility(View.VISIBLE);

            for(Button myButton: buttons)
                myButton.setVisibility(View.VISIBLE);

            t.setVisibility(View.VISIBLE);

        },3500);

        score_value.setText("Points : "+ score);

        startButton.setEnabled(true);

    }
    private void showSequence(final View background){

        Position =0;
        for(int i = 0; i< roundCounter; i++) {
            if (colors[i] != 0) {
                background.postDelayed(() -> {
                    updateBackground(colors[Position]);
                    background.setAlpha(0.2f);

                }, (i+1) * 500);

                background.postDelayed(() -> background.setAlpha(0.5f),(500*(i+1))+100);

                background.postDelayed(() -> background.setAlpha(0.9f),(500*(i+1))+150);


                background.postDelayed(() -> background.setAlpha(1f),(500*(i+1))+250);

                background.postDelayed(() -> background.setAlpha(0.9f),(500*(i+1))+350);

                background.postDelayed(() -> background.setAlpha(0.5f),(500*(i+1))+400);

                background.postDelayed(() -> {

                    background.setAlpha(0.2f);


                    Position++;
                },(500*(i+1))+450);

            }
        }
    }

    private void colorHighlight(Button button) {

        final Button b = button;

        b.postDelayed(() -> b.setAlpha(0.5f), 0);

        b.postDelayed(() -> b.setAlpha(0.75f), 50);

        b.postDelayed(() -> b.setAlpha(1f), 100);

        b.postDelayed(() -> b.setAlpha(0.75f), 250);

        b.postDelayed(() -> b.setAlpha(0.5f), 300);
    }


    public boolean getWin(){ return win; }
    public boolean getEnd(){ return end; }
    public double getScore(){ return score; }






}


