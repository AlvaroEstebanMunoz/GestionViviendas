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
import com.aem.gestionalquileres.adaptadores.RecibosAdapter;
import com.aem.gestionalquileres.modelos.Recibo;
import com.aem.gestionalquileres.utilidades.DatabaseManager;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

public class RecibosFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecibosAdapter adapter;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recibos, container, false);

        // Initialize Firebase Firestore
        db = DatabaseManager.getInstance().getFirestore();

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewRecibos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize adapter with an empty list
        adapter = new RecibosAdapter(new RecibosAdapter.ReciboDiff());
        recyclerView.setAdapter(adapter);

        // Fetch data from Firestore
        fetchRecibos();

        return view;
    }

    private void fetchRecibos() {
        db.collection("Recibos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        adapter.submitList(Objects.requireNonNull(task.getResult()).toObjects(Recibo.class));
                    }
                });
    }
}
