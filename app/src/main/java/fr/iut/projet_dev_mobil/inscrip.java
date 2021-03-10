package fr.iut.projet_dev_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class inscrip extends AppCompatActivity {

    EditText nom;
    EditText prenom;
    EditText email;
    EditText dateNaiss;
    EditText password;
    EditText verifPassword;
    RadioGroup sexe;
    Button Valid_button;
    static DatabaseReference reff;
    static Utilisateur user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrip);

        nom=(EditText)findViewById(R.id.v_nom);
        prenom=(EditText)findViewById(R.id.v_prenom);
        dateNaiss=(EditText)findViewById(R.id.v_naiss);
        email=(EditText)findViewById(R.id.v_mailInscription);
        sexe=(RadioGroup)findViewById(R.id.v_sexe);
        password=(EditText)findViewById(R.id.v_pass);
        verifPassword=(EditText)findViewById(R.id.v_verif_pass);
        user = new Utilisateur();
        reff= FirebaseDatabase.getInstance().getReference().child("Utilisateur");
        Valid_button=(Button)findViewById(R.id.Valid_button);
        Valid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean Error = false;
                int SexeCkecked = sexe.getCheckedRadioButtonId();

                if (!user.setNom(nom.getText().toString())){
                    nom.setError("Le prenom est obligatoire !");
                    Error = true;
                }

                if (!user.setPrenom(prenom.getText().toString())){
                    prenom.setError("Le prenom est obligatoire !");
                    Error = true;
                }

                if (!user.setEmail(email.getText().toString())){
                    email.setError("Le email est obligatoire !");
                    Error = true;
                }

                if (!user.setDateNaiss(dateNaiss.getText().toString())){
                    dateNaiss.setError("La date de naissance est obligatoire !");
                    Error = true;
                }

                switch (SexeCkecked) {
                    case 1:
                        user.setSexe(false);
                        break;
                    case 2:
                        user.setSexe(true);
                        break;
                    case -1:
                        sexe.set setError("Le sexe est obligatoire !");
                        Error = true;
                        break;
                }

                if (!user.setPassword(password.getText().toString())){
                    password.setError("Le mot de passe est obligatoire !");
                    Error = true;
                }

                if (!user.setVerifPassword(verifPassword.getText().toString())){
                    verifPassword.setError("Le mot de passe est obligatoire !");
                    Error = true;
                }

                if (!user.isValidPassword(password.getText().toString(),verifPassword.getText().toString())){
                    verifPassword.setError("Le mot de passe n'est pas identique");
                    Error = true;
                }

                if (Error == false){
                    try {
                        reff.push().setValue(user);
                        Toast.makeText(v.getContext(), "Inscription réussit",Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(v.getContext(), "Echec de l'inscription",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}