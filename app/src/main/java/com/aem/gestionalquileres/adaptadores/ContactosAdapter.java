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
import com.aem.gestionalquileres.modelos.Contacto;

public class ContactosAdapter extends ListAdapter<Contacto, ContactosAdapter.ContactoViewHolder> {

    public ContactosAdapter(@NonNull DiffUtil.ItemCallback<Contacto> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contacto, parent, false);
        return new ContactoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto contacto = getItem(position);
        holder.textViewDireccion.setText(contacto.getDireccion());
        holder.textViewTelefono.setText(contacto.getTelefono());
        // Set other fields as needed
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewDireccion;
        private final TextView textViewTelefono;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDireccion = itemView.findViewById(R.id.textViewDireccion);
            textViewTelefono = itemView.findViewById(R.id.textViewTelefono);
        }
    }

    public static class ContactoDiff extends DiffUtil.ItemCallback<Contacto> {
        @Override
        public boolean areItemsTheSame(@NonNull Contacto oldItem, @NonNull Contacto newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contacto oldItem, @NonNull Contacto newItem) {
            return oldItem.equals(newItem);
        }
    }
}
