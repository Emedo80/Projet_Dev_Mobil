package fr.iut.projet_dev_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class game_choice extends AppCompatActivity {

    //Button Facile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choice);
/*
        Facile=findViewById(R.id.BoutonFacile);

        Facile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), color_memory.class);
                Toast.makeText(v.getContext(), "Mode Facile.", Toast.LENGTH_SHORT).show();
                //startActivity(intent);
            }
        });
*/
    }

    public void BoutonFacile(view view){
        startActivity(new Intent(view.getContext(),color_memory.class));
    }
}