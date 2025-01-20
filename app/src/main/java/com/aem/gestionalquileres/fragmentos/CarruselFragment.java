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
import com.aem.gestionalquileres.adaptadores.CarruselAdapter;

import java.util.Arrays;


public class CarruselFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carrusel, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCarrusel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //se excluye el icono del fragmento desde el que se llama al carrusel
        int[] icons = getCarruselIcons();
        int excludeIcon = R.drawable.casa; // Valor por defecto

        // Verifica si hay un argumento que indique qué ícono excluir
        if (getArguments() != null) {
            excludeIcon = getArguments().getInt("icono_excluir", R.drawable.casa);
        }

        // Filtra el ícono que debe excluirse
        int[] filteredIcons = filterIcons(icons, excludeIcon);

        // Obtener los nombres de los íconos, filtrando también la misma posición del nombre
        String[] iconNames = getCarruselIconNames();
        String[] filteredIconNames = filterIconNames(iconNames, icons, excludeIcon);

        // Crea el adaptador con los iconos y nombres
        CarruselAdapter adapter = new CarruselAdapter(filteredIcons, filteredIconNames, this::onIconClicked);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private int[] getCarruselIcons() {
        return new int[] {
                R.drawable.casa,
                R.drawable.contacto,
                R.drawable.persona,
                R.drawable.servicio,
                R.drawable.recibo,
                R.drawable.alquiler,
                R.drawable.empresa
        };
    }

    private String[] getCarruselIconNames() {
        return new String[] {
                "Casas",
                "Contactos",
                "Personas",
                "Servicios",
                "Recibos",
                "Alquileres",
                "Empresas"
        };
    }

    private int[] filterIcons(int[] icons, int excludeIcon) {
        // Contar cuántos íconos deben permanecer
        int count = 0;
        for (int icon : icons) {
            if (icon != excludeIcon) {
                count++;
            }
        }

        // Crear un nuevo array con el tamaño correcto
        int[] filteredIcons = new int[count];
        int index = 0;

        // Añadir solo los íconos que no sean el que queremos excluir
        for (int icon : icons) {
            if (icon != excludeIcon) {
                filteredIcons[index++] = icon;
            }
        }

        return filteredIcons;
    }

    private void onIconClicked(int position) {
        switch (position) {
            case 0:
                // Acción de navegar a Alquileres
                // Navigation.findNavController(getView()).navigate(R.id.action_to_alquileresFragment);
                break;
            case 1:
                // Acción de navegar a Recibos
                // Navigation.findNavController(getView()).navigate(R.id.action_to_recibosFragment);
                break;
            // Agrega los casos restantes para cada ícono
        }
    }
    private String[] filterIconNames(String[] iconNames, int[] icons, int excludeIcon) {
        // Contar cuántos nombres deben permanecer
        int count = 0;
        for (int i = 0; i < icons.length; i++) {
            if (icons[i] != excludeIcon) {
                count++;
            }
        }

        // Crear un nuevo array con el tamaño correcto para los nombres
        String[] filteredNames = new String[count];
        int index = 0;

        // Añadir solo los nombres cuyos íconos no sean el excluido
        for (int i = 0; i < icons.length; i++) {
            if (icons[i] != excludeIcon) {
                filteredNames[index++] = iconNames[i];
            }
        }

        return filteredNames;
    }


}
