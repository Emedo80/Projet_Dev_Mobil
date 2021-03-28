package fr.iut.projet_dev_mobil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class color_memory extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    String TAG="BDD_Android";
    String userId;
    private int defaultColor;
    private int winCondition;
    private double factor;
    private int defaultLife;
    private int val_stage;
    public double val_score;
    public int test = 0;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState)
    {

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_memory);

        int numButton;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            defaultColor = extras.getInt("defaultColor");
            winCondition = extras.getInt("winCondition");
            factor = extras.getDouble("factor");
            defaultLife = extras.getInt("defaultLife",2);
            val_stage = extras.getInt("stage",1);
            val_score = extras.getDouble("score",0);
        }

        numButton = val_stage + 3;

        final int[] arrayColor = {
                Color.rgb(255, 241, 0), // Process yellow
                Color.rgb(0, 24, 143), //Blue 286
                Color.rgb(232, 17, 35), //Red 185
                Color.rgb(0, 158, 73), //Green 355
                Color.rgb(236, 0, 140), //Process magenta
                Color.rgb(255, 140, 0), //Orange 144
                Color.rgb(0, 188, 242), //Process cyan
                Color.rgb(0, 178, 148), //Teal 3275
                Color.rgb(104, 33, 122), //Purple 526
                Color.rgb(186, 216, 10), //Lime 382
        };

        List<Button> buttons = new ArrayList<>();

        final FrameLayout main = findViewById(R.id.main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int size = Math.min(height, width);


        for(int i = 0; i < numButton; i++)
        {
            //Définition de l'emplacement et de la couleur des boutons
            Button button = new Button(this);
            buttons.add(button);
            button.setGravity(Gravity.CENTER);
            button.setBackgroundColor(arrayColor[i]);

            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams((int)(size*0.30),(int) (size*0.15));

            lp.gravity = Gravity.CENTER;

            button.setLayoutParams(lp);

            float angleDeg = i * 360.0f / numButton - 90.0f;
            float angleRad = (float)(angleDeg * Math.PI / 180.0f);

            button.setTranslationX((int)(size*0.3 * (float)Math.cos(angleRad)));
            button.setTranslationY((int)(size*0.3 * (float)Math.sin(angleRad)));

            button.setRotation(angleDeg + 90.0f);
            main.addView(button);
        }

        TextView score = findViewById(R.id.score);
        TextView life = findViewById(R.id.life);
        TextView palier = findViewById(R.id.palier);
        TextView stage = findViewById(R.id.stage);
        Button start = findViewById(R.id.start);

        stage.setText(getString(R.string.niveau) + val_stage);

        Log.e("score", String.valueOf(val_score));

        final GameLib game = new GameLib(
                defaultColor,
                winCondition,
                defaultLife,
                buttons,
                factor,
                arrayColor,
                val_stage,
                score,
                life,
                start,
                palier,
                val_score);


        Thread thread = new Thread(){
            public void run(){
                do {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while(!game.getEnd() );

                if(game.getWin()){
                    //get next view  for game
                    if(val_stage > 7){
                        CheckScore((int) game.getScore());
                        Toast.makeText(color_memory.this, "vous avez gagné !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(color_memory.this, game_choice.class);
                        startActivity(intent);
                    }
                    final Intent startGame = getIntent();
                    startGame.putExtra("stage", val_stage +1);
                    startGame.putExtra("score", game.getScore());
                    startActivityForResult(startGame, val_stage +1);
                }else{
                    CheckScore((int) game.getScore());
                    Intent intent = new Intent(color_memory.this, game_choice.class);
                    startActivity(intent);
                }
            }
        };

        thread.start();


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == val_stage +1) {
            if(resultCode == RESULT_OK) {
                boolean exit = data.getExtras().getBoolean("exit");
                if(exit){
                    Intent intent = new Intent();
                    intent.putExtra("exit", true);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        }
    }

    @Override
    public void onBackPressed() {
        CheckScore((int) val_score);
        Intent intent = new Intent(color_memory.this, game_choice.class);
        startActivity(intent);
    }

    //Partie base de données pour le score
    public void CheckScore(int Score){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        userId = mAuth.getCurrentUser().getUid();
        DocumentReference reference = db.collection("users").document(userId);
        reference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()) {
                    while (test == 0) {
                        double scoreTot = Score + (double) value.getLong("score").intValue();

                        Map<String, Object> user = new HashMap<>();
                        user.put("score", scoreTot);

                        db.collection("users").document(userId).update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void avoid) {
                                Log.d(TAG, "DocumentSnapshot added with ID: ");
                                //Toast.makeText(color_memory.this, "Score changer", Toast.LENGTH_SHORT).show();
                            }
                        });
                        test = 1;
                    }
                }
            }
        });
    }

}

