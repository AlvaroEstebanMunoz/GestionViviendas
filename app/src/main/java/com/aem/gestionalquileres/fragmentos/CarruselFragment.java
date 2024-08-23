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


public class CarruselFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carrusel, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCarrusel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Crea el adaptador con los iconos y nombres
        CarruselAdapter adapter = new CarruselAdapter(getCarruselIcons(), getCarruselIconNames(), this::onIconClicked);
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
}
