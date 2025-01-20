package com.aem.gestionalquileres.fragmentos.detalles;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.aem.gestionalquileres.R;
import com.aem.gestionalquileres.modelos.Alquiler;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class DetalleAlquilerFragment extends Fragment {

    private TextView textViewFechaInicio, textViewFechaFin, textViewRenta, textViewRenovable, textViewIncremento;
    private String tituloOriginal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout
        View view = inflater.inflate(R.layout.item_alquiler, container, false);

        // Inicializar las vistas
        textViewFechaInicio = view.findViewById(R.id.textViewFechaInicio);
        textViewFechaFin = view.findViewById(R.id.textViewFechaFin);
        textViewRenta = view.findViewById(R.id.textViewRenta);
        textViewRenovable = view.findViewById(R.id.textViewRentaRenovable);
        textViewIncremento = view.findViewById(R.id.textViewIncremento);

        // Obtener datos del Bundle
        if (getArguments() != null) {
            String alquilerId = getArguments().getString("alquilerId");
            if (alquilerId != null) {
                cargarDatosAlquiler(alquilerId);
            }
        }

        return view;
    }

    private void cargarDatosAlquiler(String alquilerId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference alquilerRef = db.collection("alquileres").document(alquilerId);

        alquilerRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                Alquiler alquiler = documentSnapshot.toObject(Alquiler.class);
                if (alquiler != null) {
                    mostrarDetallesAlquiler(alquiler);
                    setToolbarTitle("Alquiler: " + alquiler.getId());
                }
            }
        }).addOnFailureListener(e -> Log.e("DetalleAlquilerFragment", "Error al cargar datos", e));
    }

    @SuppressLint("SetTextI18n")
    private void mostrarDetallesAlquiler(Alquiler alquiler) {
        textViewFechaInicio.setText(alquiler.getFechaInicio() != null ? alquiler.getFechaInicio() : "No disponible");
        textViewFechaFin.setText(alquiler.getFechaFin() != null ? alquiler.getFechaFin() : "No disponible");
        textViewRenta.setText(alquiler.getRenta() != null ? alquiler.getRenta() + " €" : "No disponible");
        textViewRenovable.setText(alquiler.getRenovable() != null && alquiler.getRenovable() ? "Sí" : "No");
        textViewIncremento.setText(alquiler.getIncrementoAnual() != null ? alquiler.getIncrementoAnual() + "%" : "No disponible");
    }

    private void setToolbarTitle(String title) {
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                if (tituloOriginal == null) {
                    tituloOriginal = activity.getSupportActionBar().getTitle().toString();
                }
                activity.getSupportActionBar().setTitle(title);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle(tituloOriginal);
            }
        }
    }
}
