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
import com.aem.gestionalquileres.adaptadores.EmpresasAdapter;
import com.aem.gestionalquileres.modelos.Empresa;
import com.aem.gestionalquileres.utilidades.DatabaseManager;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

public class EmpresasFragment extends Fragment {

    private RecyclerView recyclerView;
    private EmpresasAdapter adapter;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empresas, container, false);

        // Initialize Firebase Firestore
        db = DatabaseManager.getInstance().getFirestore();

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewEmpresas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize adapter with an empty list
        adapter = new EmpresasAdapter(new EmpresasAdapter.EmpresaDiff());
        recyclerView.setAdapter(adapter);

        // Fetch data from Firestore
        fetchEmpresas();

        return view;
    }

    private void fetchEmpresas() {
        db.collection("Empresas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        adapter.submitList(Objects.requireNonNull(task.getResult()).toObjects(Empresa.class));
                    }
                });
    }
}
