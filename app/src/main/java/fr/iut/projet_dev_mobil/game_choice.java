package fr.iut.projet_dev_mobil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class game_choice extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    String userId;
    TextView v_name,v_score;
    Button Facile,Difficile,Expert,Chrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choice);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        v_name=findViewById(R.id.v_User);
        v_score=findViewById(R.id.v_Score);
        Facile=findViewById(R.id.BoutonFacile);
        Difficile=findViewById(R.id.BoutonDifficile);
        Expert=findViewById(R.id.BoutonExpert);
        Chrono=findViewById(R.id.BoutonChrono);

        readData();


        Facile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), color_memory.class);
                intent.putExtra("nb_bloc_start",1);
                intent.putExtra("nb_bloc_4_win",7);
                intent.putExtra("poids_du_mode",(double) 1);
                Toast.makeText(v.getContext(), "Mode Facile.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        Difficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), color_memory.class);
                intent.putExtra("nb_bloc_start",3);
                intent.putExtra("nb_bloc_4_win",10);
                intent.putExtra("poids_du_mode", 1.5);
                Toast.makeText(v.getContext(), "Mode Difficile.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        Expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), color_memory.class);
                intent.putExtra("nb_bloc_start",4);
                intent.putExtra("nb_bloc_4_win",12);
                intent.putExtra("poids_du_mode",(double) 2);
                Toast.makeText(v.getContext(), "Mode Expert.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        Chrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), color_memory.class);
                intent.putExtra("nb_bloc_start",1);
                intent.putExtra("nb_bloc_4_win",8);
                intent.putExtra("default_life",3);
                intent.putExtra("poids_du_mode", 1.5);
                intent.putExtra("chrono",true);
                Toast.makeText(v.getContext(), "Mode Chrono.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void readData(){
        userId = mAuth.getCurrentUser().getUid();
        DocumentReference reference = db.collection("users").document(userId);
        reference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()) {
                    v_name.setText(value.getData().get("Prenom").toString());
                    v_score.setText(value.getData().get("score").toString());
                }
            }
        });
    }
}