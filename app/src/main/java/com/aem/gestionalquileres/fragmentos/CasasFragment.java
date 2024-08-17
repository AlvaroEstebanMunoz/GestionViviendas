package com.aem.gestionalquileres.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aem.gestionalquileres.R;
import com.aem.gestionalquileres.adaptadores.CasasAdapter;
import com.aem.gestionalquileres.modelos.Casa;
import com.aem.gestionalquileres.utilidades.DecoracionEspacioFinal;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CasasFragment extends Fragment {

    private RecyclerView recyclerView;
    private CasasAdapter adapter;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_casas, container, false);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewCasas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        boolean isHorizontal = layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL;

        // Determine orientation and create decoration
        int space = 6; // Adjust space as needed
        DecoracionEspacioFinal decoracion = new DecoracionEspacioFinal(space, isHorizontal);
        recyclerView.addItemDecoration(decoracion);

        // Initialize adapter with an empty list and set item click listener
        adapter = new CasasAdapter(new CasasAdapter.CasaDiff(), casa -> {
            // Handle double tap event, navigate to item_casa
            Bundle bundle = new Bundle();
            bundle.putSerializable("casa", casa); // Pass the Casa object to the next fragment
            Navigation.findNavController(view).navigate(R.id.action_casasFragment_to_detalleCasaFragment, bundle);
        });

        recyclerView.setAdapter(adapter);

        // Obteniendo el NavController desde el FragmentContainerView
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);


        // Fetch data from Firestore
        fetchCasas();

        return view;
    }

    private void fetchCasas() {
        db.collection("Casas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Casa> casasList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Casa casa = document.toObject(Casa.class);

                            // Convertir DocumentReference a String (ID)
                            DocumentReference propietarioRef = document.getDocumentReference("propietario");
                            if (propietarioRef != null) {
                                String propietarioId = propietarioRef.getId();
                                casa.setPropietarioId(propietarioId);
                            }

                            casasList.add(casa);
                        }

                        adapter.submitList(casasList);
                    }
                });
    }
}
