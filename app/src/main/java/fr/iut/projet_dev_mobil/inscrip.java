package fr.iut.projet_dev_mobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

public class inscrip extends AppCompatActivity {

    EditText v_nom,v_prenom,v_email,v_pass,v_verif_pass;
    Button Valid_button;

    private FirebaseAuth mAuth;
    String TAG="BDD_Android";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrip);

        v_nom=findViewById(R.id.v_nom);
        v_prenom=findViewById(R.id.v_prenom);
        v_email=findViewById(R.id.v_mailInscription);
        v_pass=findViewById(R.id.v_pass);
        v_verif_pass=findViewById(R.id.v_verif_pass);
        Valid_button=(Button)findViewById(R.id.Valid_button);

        mAuth = FirebaseAuth.getInstance();

        Valid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser(v_email.getText().toString(), v_pass.getText().toString());

            }
        });
    }

    public void addUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(inscrip.this, MainActivity.class);
                            Toast.makeText(inscrip.this, "Inscription réussi.", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(inscrip.this, "Erreur lore de l'inscription",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

}