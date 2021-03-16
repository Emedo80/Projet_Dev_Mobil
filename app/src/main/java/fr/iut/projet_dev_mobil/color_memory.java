package fr.iut.projet_dev_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class color_memory extends AppCompatActivity {
    Button Blue;
    Button Red;
    Button Yellow;
    Button Green;
    public int position = 0;
    public int tableau[] = new int[10];
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
            tableau[i] = ((int) Math.random() * 4  + 1); // choisit la première couleur
        }


        Blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getButton(1);
            }
        });

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



    public void getButton(int button_pushed) { // incrémentation de la séquence si le bouton est le bon (revoir nom variable)
        for (int i=0;i<position;i++){
            if(tableau[position] == button_pushed){
                position++;
            }
            else{
                life -= 1;

            }
        }

    }

    public void button_show (int button_pushed){ //Si premier bouton = bleu alors changer la couleur du bleu pour le mettre en évidence
         if(button_pushed == 1){
            Blue.setBackgroundResource(R.drawable.blue1);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                public void run() {
                    Blue.setBackgroundResource((R.drawable.blue)); // Ne fonctionne pas, à reprendre
                }
            })
        }
    }


}