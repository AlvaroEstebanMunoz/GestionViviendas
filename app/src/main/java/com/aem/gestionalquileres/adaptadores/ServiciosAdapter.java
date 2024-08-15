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
import com.aem.gestionalquileres.modelos.Servicio;

public class ServiciosAdapter extends ListAdapter<Servicio, ServiciosAdapter.ServicioViewHolder> {

    public ServiciosAdapter(@NonNull DiffUtil.ItemCallback<Servicio> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_servicio, parent, false);
        return new ServicioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioViewHolder holder, int position) {
        Servicio servicio = getItem(position);
        holder.textViewEmpresaId.setText(servicio.getEmpresaId());
        holder.textViewContactoId.setText(servicio.getDescripcion());
        // Set other fields as needed
    }

    public static class ServicioViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewEmpresaId;
        private final TextView textViewContactoId;

        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewEmpresaId = itemView.findViewById(R.id.textViewEmpresaId);
            textViewContactoId = itemView.findViewById(R.id.textViewContactoId);
        }
    }

    public static class ServicioDiff extends DiffUtil.ItemCallback<Servicio> {
        @Override
        public boolean areItemsTheSame(@NonNull Servicio oldItem, @NonNull Servicio newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Servicio oldItem, @NonNull Servicio newItem) {
            return oldItem.equals(newItem);
        }
    }
}
