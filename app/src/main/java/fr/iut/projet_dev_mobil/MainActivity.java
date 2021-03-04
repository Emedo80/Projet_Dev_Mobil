package fr.iut.projet_dev_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickConnexion(View view){
        setContentView(R.layout.connexion);
    }

    public void ClickInscription(View view){
        setContentView(R.layout.inscription);
    }
}