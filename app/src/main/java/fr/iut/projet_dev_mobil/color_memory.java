package fr.iut.projet_dev_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class color_memory extends AppCompatActivity {
    Button Blue;
    Button Red;
    Button Yellow;
    Button Green;
    public int sequence = 0;
    public int table[] = new int[10];
    public int life = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_memory);

        Blue = findViewById(R.id.Blue);
        Red = findViewById(R.id.Red);
        Yellow = findViewById(R.id.Yellow);
        Green = findViewById(R.id.Green);

        for(int i=0;i<10;i++){
            table[i] = ((int) Math.random() * 4  + 1); // choisit la première couleur
        }


        Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getButton(2);
            }
        });

        Yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getButton(3);
            }
        });

        Green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getButton(4);
            }
        });


    }

    public void sequence_show(int sequence_end){
        for(int i=0; i<=sequence_end;i++){

            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            button_show(table[i]);
        }
    }



    public void getButton(int button_pushed) { // incrémentation de la séquence si le bouton est le bon
        for (int i=0;i<sequence;i++){
            if(table[sequence] == button_pushed){
                sequence++;
            }
            else{
                life -= 1;

            }
        }

    }

    public void button_show (int button_pushed){ //Si premier bouton = bleu alors changer la couleur du bleu pour le mettre en évidence
         if(button_pushed == 1){
            Blue.setBackgroundColor(Color.BLUE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                public void run() {
                    Blue.setBackgroundColor(Color.BLUE);
                }
            }, 200);
        }

         else if (button_pushed == 2){
             Red.setBackgroundColor(Color.RED);
             Handler handler = new Handler();
             handler.postDelayed(new Runnable(){
                 public void run() {
                     Red.setBackgroundColor(Color.RED);
                 }
             }, 200);
         }

         else if (button_pushed == 3){
             Yellow.setBackgroundColor(Color.YELLOW);
             Handler handler = new Handler();
             handler.postDelayed(new Runnable(){
                 public void run() {
                     Yellow.setBackgroundColor(Color.YELLOW);
                 }
             }, 200);
         }

         else if (button_pushed ==4){
             Green.setBackgroundColor(Color.GREEN);
             Handler handler = new Handler();
             handler.postDelayed(new Runnable(){
                 public void run() {
                     Green.setBackgroundColor(Color.GREEN);
                 }
             }, 200);
         }


    }


}

