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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_memory);

        Blue = findViewById(R.id.Blue);
        Red = findViewById(R.id.Red);
        Yellow = findViewById(R.id.Yellow);
        Green = findViewById(R.id.Green);

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
        }


    }

    private void getButton(int button_pushed) {

    }
}