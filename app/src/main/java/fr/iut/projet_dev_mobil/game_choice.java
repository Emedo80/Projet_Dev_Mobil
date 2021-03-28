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
    Button Facile,Difficile,Expert;

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

        readData();


        Facile.setOnClickListener(v -> {
            final Intent intent = new Intent(v.getContext(), color_memory.class);
            intent.putExtra("defaultColor",1);
            intent.putExtra("winCondition",7);
            intent.putExtra("factor",(double) 1);
            Toast.makeText(v.getContext(), "Mode Facile.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        Difficile.setOnClickListener(v -> {
            final Intent intent = new Intent(v.getContext(), color_memory.class);
            intent.putExtra("defaultColor",3);
            intent.putExtra("winCondition",10);
            intent.putExtra("factor", 1.5);
            Toast.makeText(v.getContext(), "Mode Difficile.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        Expert.setOnClickListener(v -> {
            final Intent intent = new Intent(v.getContext(), color_memory.class);
            intent.putExtra("defaultColor",4);
            intent.putExtra("winCondition",12);
            intent.putExtra("factor",(double) 2);
            Toast.makeText(v.getContext(), "Mode Expert.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

    }

    public void readData(){
        userId = mAuth.getCurrentUser().getUid();
        DocumentReference reference = db.collection("users").document(userId);
        reference.addSnapshotListener(this, (value, error) -> {
            if (value.exists()) {
                v_name.setText(value.getData().get("Prenom").toString());
                v_score.setText(value.getData().get("score").toString());
            }
        });
    }
}