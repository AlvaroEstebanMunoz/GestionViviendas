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

    // Interfaz para manejar el evento de toque
    public interface OnItemClickListener {
        void onItemClick(Alquiler alquiler);
    }

    private final OnItemClickListener onItemClickListener;

    // Constructor modificado para aceptar el listener
    public AlquileresAdapter(@NonNull DiffUtil.ItemCallback<Alquiler> diffCallback, OnItemClickListener listener) {
        super(diffCallback);
        this.onItemClickListener = listener;
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
        holder.bind(alquiler);
    }

    public class AlquilerViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewFechaInicio;
        private final TextView textViewFechaFin;

        public AlquilerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFechaInicio = itemView.findViewById(R.id.textViewFechaInicio);
            textViewFechaFin = itemView.findViewById(R.id.textViewFechaFin);

            // Configurar el listener de toque
            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(getItem(position));
                    }
                }
            });
        }

        public void bind(Alquiler alquiler) {
            textViewFechaInicio.setText(alquiler.getFechaInicio());
            textViewFechaFin.setText(alquiler.getFechaFin());
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
