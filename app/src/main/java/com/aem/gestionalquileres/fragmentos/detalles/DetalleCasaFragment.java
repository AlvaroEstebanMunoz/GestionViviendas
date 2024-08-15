package com.aem.gestionalquileres.fragmentos.detalles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aem.gestionalquileres.R;
import com.aem.gestionalquileres.modelos.Casa;
import com.bumptech.glide.Glide;

public class DetalleCasaFragment extends Fragment {

    private ImageView imagenCasa;
    private TextView aliasCasa;
    private TextView direccionCasa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.item_casa, container, false);

        // Inicializar las vistas
        imagenCasa = view.findViewById(R.id.foto);
        aliasCasa = view.findViewById(R.id.aliasCasa);
        direccionCasa = view.findViewById(R.id.textViewDireccion);

        // Obtener el objeto Casa pasado en el Bundle
        if (getArguments() != null) {
            Casa casa = (Casa) getArguments().getSerializable("casa");
            if (casa != null) {
                mostrarDetallesCasa(casa);
            }
        }

        return view;
    }

    private void mostrarDetallesCasa(Casa casa) {
        // Establecer los detalles de la casa en las vistas
        aliasCasa.setText(casa.getAlias());
        direccionCasa.setText(casa.getDireccion());

        // Cargar la imagen usando Glide
        Glide.with(this)
                .load(casa.getFoto())
                .placeholder(R.drawable.placeholder_image) // Imagen de placeholder
                .into(imagenCasa);
    }
}
