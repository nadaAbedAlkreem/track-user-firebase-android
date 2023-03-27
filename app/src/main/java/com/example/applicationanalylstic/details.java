package com.example.applicationanalylstic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationjave.Model.category;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;

public class details extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView TextNote;
     ImageView ViewImage;
    TextView TextNoteBody;
    private FirebaseAnalytics mFirebaseAnalytics;
    Calendar calendar = Calendar.getInstance() ;
    int houres  = calendar.get(Calendar.HOUR) ;
    int minutes  = calendar.get(Calendar.MINUTE) ;
    int second  = calendar.get(Calendar.SECOND) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        String idNoteCurrent =  intent.getStringExtra("id")  ;
        TextNote  = findViewById(R.id.haeder_note);
        TextNoteBody  = findViewById(R.id.body_note);
        ViewImage = findViewById(R.id.imageView);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        screenTrack("details");
        Toast.makeText(this, idNoteCurrent, Toast.LENGTH_SHORT).show();
        Log.e("nada" , idNoteCurrent);

        db.collection("Notes").document(idNoteCurrent).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null) {
                                Log.d("TAG", "data already exists.");
                                String noteText = document.getString("note");
                                String header = document.getString("header");
                                String image = document.getString("image");
                                Log.e("nadaImage" , image);
                                Log.e("nada" , noteText);
                                TextNote.setText(header);
                                TextNoteBody.setText(noteText);
                                ImageView imageView = findViewById(R.id.imageView);
                                Picasso.get().load(image).into(imageView);

//                                ViewImage.setImageURI(Uri.parse(image));
                                Log.d("TAG"  , noteText);

                            } else {
                                Log.d("TAG", "data doesn't exist.");
                            }
                        } else {
                            Log.d("TAG", task.getException().getMessage()); //Never ignore potential errors!
                        }
                    }
                });


    }
    public  void screenTrack(String name){
        Bundle bundle =  new Bundle() ;
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME  , name);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS  , "Main home " );

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW   , bundle);


    }

    @Override
    protected void onPause() {

        Calendar calendar = Calendar.getInstance() ;
        int houres2  = calendar.get(Calendar.HOUR) ;
        int minutes2  = calendar.get(Calendar.MINUTE) ;
        int second2  = calendar.get(Calendar.SECOND) ;
        int h = houres2 - houres  ;
        int m = minutes2   - minutes  ;
        int s = second2 - second ;
        HashMap<String , Object > time  = new HashMap<>() ;
        time.put("hourse" , h ) ;
        time.put("minuset" , m ) ;
        time.put("second" , s ) ;
        time.put("screen_name" , "details" ) ;


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("trackUser")
                .add(time)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                          @Override
                                          public void onSuccess(DocumentReference documentReference) {
                                              Log.e("TAG", "Data added successfully to database");
                                          }
                                      }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAG", "Failed to add database");


                    }
                });
        super.onPause();
    }

}