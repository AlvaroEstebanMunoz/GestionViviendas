package com.aem.gestionalquileres.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aem.gestionalquileres.R;
import com.aem.gestionalquileres.modelos.Persona;

public class PersonasAdapter extends ListAdapter<Persona, PersonasAdapter.PersonaViewHolder> {

    public PersonasAdapter(@NonNull DiffUtil.ItemCallback<Persona> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona persona = getItem(position);
        holder.textViewNombre.setText(persona.getNombre());
        holder.textViewEmail.setText(persona.getEmail());
        // Set other fields as needed
    }


    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNombre;
        private final TextView textViewEmail;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewDireccion);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }

    public static class PersonaDiff extends DiffUtil.ItemCallback<Persona> {
        @Override
        public boolean areItemsTheSame(@NonNull Persona oldItem, @NonNull Persona newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Persona oldItem, @NonNull Persona newItem) {
            return oldItem.equals(newItem);
        }
    }
}
