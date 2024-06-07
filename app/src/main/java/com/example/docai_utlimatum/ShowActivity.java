package com.example.docai_utlimatum;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");
    private FirebaseFirestore fireDB = FirebaseFirestore.getInstance();

    private MyAdapter adapter;
    private ArrayList<Model> list;

    long deviceID;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list= new ArrayList<>();
        adapter = new MyAdapter(this,list );

        recyclerView.setAdapter(adapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
        {
            Log.d("LOGGG", "User is signed in");
            String email = user.getEmail();
            Log.d("LOGGG", email);
//            String deviceID;

            fireDB.collection("Vitals")
                    .whereEqualTo("email", email)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful())
                            {
                                Log.d("FBLOGGG", "task.isSuccessful");
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("FBLOGGG", document.getId() + " => " + document.getData());
                                    deviceID = (long) document.getData().get("DeviceID");
                                    Log.d("LOGGG", String.valueOf(deviceID));
                                    onQueryDone();
                                }
                            }
                            else
                            {
                                Log.w("FBLOGGG", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        else
        {
            Log.d("LOGGG", "User is NOT signed in");
        }
    }

    private void onQueryDone() {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Model model = dataSnapshot.getValue(Model.class);
                    Log.d("LOGGG", "EMAIL");
                    Log.d("LOGGG", String.valueOf(model.getID()));
                    Log.d("LOGGG", String.valueOf(deviceID));
                    if (deviceID == model.getID())
                    {
                        Log.d("LOGGG", "SAME");
                        list.add(model);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}










