package com.aem.gestionalquileres.crud.personas;

// EditPersonActivity.java

import android.app.Person;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.aem.gestionalquileres.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditPersonActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText nameEditText, emailEditText;
    private String personId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        db = FirebaseFirestore.getInstance();
        nameEditText = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmail);
        Button saveButton = findViewById(R.id.buttonSave);

        personId = getIntent().getStringExtra("PERSON_ID"); // Pass person ID from the previous activity

        // Fetch current data
        db.collection("Personas").document(personId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    Person person = documentSnapshot.toObject(Person.class);
                    if (person != null) {
                        nameEditText.setText(person.getName());
                        emailEditText.setText(person.getUri());
                    }
                });

        saveButton.setOnClickListener(v -> updatePerson());
    }

    private void updatePerson() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();

        db.collection("Personas").document(personId)
                .update("name", name, "email", email)
                .addOnSuccessListener(aVoid -> finish()) // Close activity
                .addOnFailureListener(e -> {
                    // Handle error
                });
    }
}
