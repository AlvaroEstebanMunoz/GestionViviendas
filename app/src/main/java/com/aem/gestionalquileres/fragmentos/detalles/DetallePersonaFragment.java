/*package com.aem.gestionalquileres.fragmentos.detalles;

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
import com.aem.gestionalquileres.modelos.Alquiler;
import com.bumptech.glide.Glide;

public class DetallePersonaFragment extends Fragment {

    private ImageView imagenAlquiler;
    private TextView direccionAlquiler;
    private String alias;
    private String tituloOriginal;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.item_alquiler, container, false);

        // Inicializar las vistas
        imagenAlquiler = view.findViewById(R.id.imagenAlquiler);
        direccionAlquiler = view.findViewById(R.id.textViewDireccionAlquiler);

        // Agregar CarruselFragment dinámicamente
        agregarCarruselFragment();

        // Obtener el objeto Alquiler pasado en el Bundle
        if (getArguments() != null) {
            Alquiler alquiler = (Alquiler) getArguments().getSerializable("alquiler");
            if (alquiler != null) {
                alias = alquiler.getAlias();
                mostrarDetallesAlquiler(alquiler);
                setToolbarTitle(alias);
            }
        }

        return view;
    }

    private void mostrarDetallesAlquiler(Alquiler alquiler) {
        // Establecer los detalles del alquiler en las vistas
        direccionAlquiler.setText(alquiler.getDireccion());

        // Cargar la imagen usando Glide
        Glide.with(this)
                .load(alquiler.getFoto())
                .placeholder(R.drawable.placeholder_image) // Imagen de placeholder
                .into(imagenAlquiler);
    }

    private void setToolbarTitle(String title) {
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                if (tituloOriginal == null) {
                    // Guardar el título original
                    tituloOriginal = activity.getSupportActionBar().getTitle().toString();
                }
                activity.getSupportActionBar().setTitle(title);
            }
        }
    }

    private void agregarCarruselFragment() {
        // Crear una instancia del CarruselFragment
        CarruselFragment carruselFragment = new CarruselFragment();

        // Crear un Bundle para pasar el ícono a excluir
        Bundle args = new Bundle();
        args.putInt("icono_excluir", R.drawable.icono_default); // Aquí se define el ícono que se quiere excluir
        carruselFragment.setArguments(args);

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
*/