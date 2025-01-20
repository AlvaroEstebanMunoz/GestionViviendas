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
import com.aem.gestionalquileres.adaptadores.ContactosAdapter;
import com.aem.gestionalquileres.modelos.Contacto;
import com.aem.gestionalquileres.utilidades.DatabaseManager;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

public class ContactosFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContactosAdapter adapter;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactos, container, false);

        // Initialize Firebase Firestore
        db = DatabaseManager.getInstance().getFirestore();

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewContactos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize adapter with an empty list
        adapter = new ContactosAdapter(new ContactosAdapter.ContactoDiff());
        recyclerView.setAdapter(adapter);

        // Fetch data from Firestore
        fetchContactos();

        return view;
    }

    private void fetchContactos() {
        db.collection("Contactos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        adapter.submitList(Objects.requireNonNull(task.getResult()).toObjects(Contacto.class));
                    }
                });
    }
}
