package com.example.docai_utlimatum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import kotlin.text.UStringsKt;

public class MainActivity extends AppCompatActivity {
    private EditText mNume, mPrenume, mIDDOC;
    //    private EditText BPM_VALUE , SPO2_VALUE;
    private Button buttonRead, button2;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("LOGGG", "MAIN");

        Intent intent
                = new Intent(MainActivity.this,
                activity_login.class);
        startActivity(intent);

//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//
////        BPM_VALUE = findViewById(R.id.BPM_VALUE);
////        SPO2_VALUE = findViewById(R.id.SPO2_VALUE);
//        buttonRead = findViewById(R.id.buttonRead);
//        button2 = findViewById(R.id.button2);
//
//        mNume = findViewById(R.id.mNume);
//        mPrenume = findViewById(R.id.mPrenume);
//        mIDDOC = findViewById(R.id.mIDDOC);
//        buttonRead.setOnClickListener((v) -> {
//
//            String IDDOC = mIDDOC.getText().toString();
//            String nume = mNume.getText().toString();
//            String prenume = mPrenume.getText().toString();
//
//            HashMap<String, String> userMap = new HashMap<>();
//
//            userMap.put("ID", IDDOC);
//            userMap.put("Nume", nume);
//            userMap.put("Prenume", prenume);
//
//            root.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        });
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ShowActivity.class));
//            }
//        });

    }
}