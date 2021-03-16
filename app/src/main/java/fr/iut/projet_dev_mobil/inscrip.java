package fr.iut.projet_dev_mobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class inscrip extends AppCompatActivity {

    EditText v_nom,v_prenom,v_email,v_pass,v_verif_pass;
    TextView mDisplayDate;
    Button Valid_button;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    private FirebaseAuth mAuth;
    String TAG="BDD_Android";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrip);

        mDisplayDate= ( TextView ) findViewById(R.id.Datedisplay);
        v_nom=findViewById(R.id.v_nom);
        v_prenom=findViewById(R.id.v_prenom);
        v_email=findViewById(R.id.v_mailInscription);
        v_pass=findViewById(R.id.v_pass);
        v_verif_pass=findViewById(R.id.v_verif_pass);
        Valid_button=(Button)findViewById(R.id.Valid_button);

        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int Year = cal.get(Calendar.YEAR);
                int Month = cal.get(Calendar.MONTH);
                int Day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                  inscrip.this,
                  android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                  onDateSetListener,
                  Year,Month,Day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int Year, int Month, int Day) {
               Month = Month + 1;
               Log.d(TAG, "onDateSet: dd/mm/yyyy" + Day + "/" + Month + "/" + Year);

               String date = Day + "/" + Month + "/" + Year;
               mDisplayDate.setText(date);
            }
        };

        Valid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( v_pass.getText().toString().equals(v_verif_pass.getText().toString()) ) {
                    addUser(v_email.getText().toString(), v_pass.getText().toString());
                }
                else {
                    Toast.makeText(v.getContext(), "Mots de passes pas identique!", Toast.LENGTH_SHORT).show();
                }

                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("Prenom", v_prenom.getText().toString());
                user.put("Nom", v_nom.getText().toString());
                // Add a new document with a generated ID
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
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