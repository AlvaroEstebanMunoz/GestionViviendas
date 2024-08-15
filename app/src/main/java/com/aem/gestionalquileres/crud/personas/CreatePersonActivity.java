package com.aem.gestionalquileres.crud.personas;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.aem.gestionalquileres.R;
import com.aem.gestionalquileres.modelos.Persona;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreatePersonActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText nameEditText, emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        db = FirebaseFirestore.getInstance();
        nameEditText = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmail);
        Button saveButton = findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(v -> addPerson());
    }

    private void addPerson() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();

        Persona persona = new Persona(name, email);

        db.collection("Personas")
                .add(persona)
                .addOnSuccessListener(documentReference -> finish())
                .addOnFailureListener(e -> {
                    // Handle error
                });
    }
}
