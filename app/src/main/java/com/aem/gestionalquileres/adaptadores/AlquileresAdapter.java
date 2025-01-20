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

        // Setear los datos del alquiler en los elementos de la vista
        holder.textViewFechaInicio.setText(alquiler.getFechaInicio());
        holder.textViewFechaFin.setText(alquiler.getFechaFin());
        holder.textViewRenta.setText(String.format("$%.2f", alquiler.getRenta()));

        //if (alquiler.getRenovable() != null) {
        //    holder.textViewRenovable.setText(alquiler.getRenovable() ? "Sí" : "No");
        //}

        if (alquiler.getIncrementoAnual() != null) {
            holder.textViewIncremento.setText(
                    String.format("%.2f%%", alquiler.getIncrementoAnual())
            );
        }

        // todo Por ahora no mostramos datos de casa y persona, pero los ViewHolders están preparados
        //holder.textViewCasa.setText("Casa no informada");
        holder.textViewPersona.setText("Persona no informada");
    }

    public static class AlquilerViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewFechaInicio;
        private final TextView textViewFechaFin;
        private final TextView textViewRenta;
        //private final TextView textViewRenovable;
        private final TextView textViewIncremento;
        //private final TextView textViewCasa;
        private final TextView textViewPersona;

        public AlquilerViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFechaInicio = itemView.findViewById(R.id.textViewFechaInicio);
            textViewFechaFin = itemView.findViewById(R.id.textViewFechaFin);
            textViewRenta = itemView.findViewById(R.id.textViewRenta);
            textViewIncremento = itemView.findViewById(R.id.textViewIncremento);
            //todo aquí hay que poner la referencia de la casa y de los inquilinos casaRef personaRef
            //textViewCasa = itemView.findViewById(R.id.textViewCasa);
            textViewPersona = itemView.findViewById(R.id.textViewInquilinos);
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
