package com.aem.gestionalquileres.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aem.gestionalquileres.R;
import com.aem.gestionalquileres.actividades.MainActivity;
import com.aem.gestionalquileres.adaptadores.CasasAdapter;
import com.aem.gestionalquileres.modelos.Casa;
import com.aem.gestionalquileres.utilidades.DatabaseManager;
import com.aem.gestionalquileres.utilidades.DecoracionEspacioFinal;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CasasFragment extends Fragment {

    private CasasAdapter adapter;
    private FirebaseFirestore db;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout del fragmento y devolver la vista
        return inflater.inflate(R.layout.fragment_casas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener el NavController desde la MainActivity
        if (getActivity() instanceof MainActivity) {
            navController = ((MainActivity) getActivity()).getNavController();
        }

        // Inicializar Firebase Firestore
        db = DatabaseManager.getInstance().getFirestore();

        // Configurar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCasas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        boolean isHorizontal = layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL;

        // Crear decoración y añadir al RecyclerView
        int space = 6; // Ajustar espacio según sea necesario
        DecoracionEspacioFinal decoracion = new DecoracionEspacioFinal(space, isHorizontal);
        recyclerView.addItemDecoration(decoracion);

        // Inicializar adaptador con una lista vacía y configurar el listener de clics
        adapter = new CasasAdapter(new CasasAdapter.CasaDiff(), casa -> {
            if (navController != null) { // Verificación de navController
                Bundle bundle = new Bundle();
                bundle.putSerializable("casa", casa); // Pasar el objeto Casa al siguiente fragmento
                navController.navigate(R.id.action_casasFragment_to_detalleCasaFragment, bundle); // Navegación
            }
        });
        recyclerView.setAdapter(adapter);

        // Obtener datos desde Firestore
        fetchCasas();
        //todo Dependencia de Firebase:
        // Al obtener los datos de Firestore, se manejan conversiones y asignaciones.
        // Sin embargo, no hay lógica específica para manejar errores de navegación o
        // asegurar un retroceso adecuado al fragmento anterior.
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
                            List<DocumentReference> propietariosRefs = (List<DocumentReference>) document.get("propietario");
                            if (propietariosRefs != null && !propietariosRefs.isEmpty()) {
                                List<String> propietarioIds = new ArrayList<>();
                                for (DocumentReference propietarioRef : propietariosRefs) {
                                    String propietarioId = propietarioRef.getId();
                                    propietarioIds.add(propietarioId);
                                }

                                if (casa != null) {
                                    casa.setPropietarioIds(propietarioIds);
                                }
                            }

                            if (casa != null) {
                                casasList.add(casa);
                            }
                        }

                        adapter.submitList(casasList);
                    }
                });
    }

}