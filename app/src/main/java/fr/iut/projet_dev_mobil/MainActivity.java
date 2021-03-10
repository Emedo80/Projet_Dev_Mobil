package fr.iut.projet_dev_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickConnexion(View view){
        startActivity(new Intent(view.getContext(),connexion.class));
    }

    public void ClickInscription(View view){
        startActivity(new Intent(view.getContext(),inscrip.class));
    }
}