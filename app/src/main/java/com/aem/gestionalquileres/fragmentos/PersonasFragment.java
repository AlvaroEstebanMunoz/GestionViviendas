package com.aem.gestionalquileres.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aem.gestionalquileres.R;
import com.aem.gestionalquileres.adaptadores.PersonasAdapter;
import com.aem.gestionalquileres.modelos.Persona;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

public class PersonasFragment extends Fragment {

    private RecyclerView recyclerView;
    private PersonasAdapter adapter;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personas, container, false);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewPersonas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize adapter with an empty list
        adapter = new PersonasAdapter(new PersonasAdapter.PersonaDiff());
        recyclerView.setAdapter(adapter);

        // Fetch data from Firestore
        fetchPersonas();

        return view;
    }

    private void fetchPersonas() {
        db.collection("Personas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        adapter.submitList(Objects.requireNonNull(task.getResult()).toObjects(Persona.class));
                    }
                });
    }
}
