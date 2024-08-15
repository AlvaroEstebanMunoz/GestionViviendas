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
import com.aem.gestionalquileres.modelos.Recibo;

public class RecibosAdapter extends ListAdapter<Recibo, RecibosAdapter.ReciboViewHolder> {

    public RecibosAdapter(@NonNull DiffUtil.ItemCallback<Recibo> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ReciboViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recibo, parent, false);
        return new ReciboViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReciboViewHolder holder, int position) {
        Recibo recibo = getItem(position);
        holder.textViewPersonaId.setText(recibo.getPersonaId());
        holder.textViewConcepto.setText(recibo.getConcepto());
        // Set other fields as needed
    }

    public static class ReciboViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewPersonaId;
        private final TextView textViewConcepto;

        public ReciboViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPersonaId = itemView.findViewById(R.id.textViewPersonaId);
            textViewConcepto = itemView.findViewById(R.id.textViewFecha);
        }
    }

    public static class ReciboDiff extends DiffUtil.ItemCallback<Recibo> {
        @Override
        public boolean areItemsTheSame(@NonNull Recibo oldItem, @NonNull Recibo newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Recibo oldItem, @NonNull Recibo newItem) {
            return oldItem.equals(newItem);
        }
    }
}
