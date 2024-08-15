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
import com.aem.gestionalquileres.modelos.Alquiler;

public class AlquileresAdapter extends ListAdapter<Alquiler, AlquileresAdapter.AlquilerViewHolder> {

    public AlquileresAdapter(@NonNull DiffUtil.ItemCallback<Alquiler> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public AlquilerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alquiler, parent, false);
        return new AlquilerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlquilerViewHolder holder, int position) {
        Alquiler alquiler = getItem(position);
        holder.textViewCasaId.setText(String.valueOf(alquiler.getCasaId()));
        holder.textViewFechaInicio.setText(alquiler.getFechaInicio());
        holder.textViewFechaFin.setText(alquiler.getFechaFin());
        // Set other fields as needed
    }

    public static class AlquilerViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewCasaId;
        private final TextView textViewFechaInicio;
        private final TextView textViewFechaFin;

        public AlquilerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCasaId = itemView.findViewById(R.id.textViewCasaId);
            textViewFechaInicio = itemView.findViewById(R.id.textViewFechaInicio);
            textViewFechaFin = itemView.findViewById(R.id.textViewFechaFin);
        }
    }

    public static class AlquilerDiff extends DiffUtil.ItemCallback<Alquiler> {
        @Override
        public boolean areItemsTheSame(@NonNull Alquiler oldItem, @NonNull Alquiler newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Alquiler oldItem, @NonNull Alquiler newItem) {
            return oldItem.equals(newItem);
        }
    }
}
