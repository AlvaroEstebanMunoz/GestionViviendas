package com.aem.gestionalquileres.fragmentos.detalles;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aem.gestionalquileres.R;
import com.aem.gestionalquileres.fragmentos.CarruselFragment;
import com.aem.gestionalquileres.modelos.Casa;
import com.bumptech.glide.Glide;

public class DetalleCasaFragment extends Fragment {

    private ImageView imagenCasa;
    //private TextView aliasCasa;
    private TextView direccionCasa;
    private String alias;
    private String tituloOriginal;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.item_casa, container, false);

        // Inicializar las vistas
        imagenCasa = view.findViewById(R.id.imagenCasa);
        //aliasCasa = view.findViewById(R.id.aliasCasa);
        direccionCasa = view.findViewById(R.id.textViewDireccion);

        // Agregar CarruselFragment dinámicamente
        agregarCarruselFragment();

        // Obtener el objeto Casa pasado en el Bundle
        if (getArguments() != null) {
            Casa casa = (Casa) getArguments().getSerializable("casa");
            if (casa != null) {
                alias =casa.getAlias();
                mostrarDetallesCasa(casa);
                setToolbarTitle(alias);
            }
        }

        return view;
    }

    private void mostrarDetallesCasa(Casa casa) {
        // Establecer los detalles de la casa en las vistas
        direccionCasa.setText(casa.getDireccion());
        //aliasCasa.setText(casa.getAlias());

        // Cargar la imagen usando Glide
        Glide.with(this)
                .load(casa.getFoto())
                .placeholder(R.drawable.placeholder_image) // Imagen de placeholder
                .into(imagenCasa);
    }

    private void setToolbarTitle(String title) {
        if (getActivity() != null) {
            AppCompatActivity activity= (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null){
                if (tituloOriginal == null) {
                    // Guardar el título original
                    tituloOriginal = activity.getSupportActionBar().getTitle().toString();
                }
            }
        }
    }

    private void agregarCarruselFragment() {
        // Crear una instancia del CarruselFragment
        CarruselFragment carruselFragment = new CarruselFragment();

        // Obtener el FragmentManager y comenzar una transacción
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Reemplazar el contenedor (el include en tu layout) con el CarruselFragment
        fragmentTransaction.replace(R.id.carruselFragment, carruselFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Restaurar el título original
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle(tituloOriginal);
            }
        }
    }

}
