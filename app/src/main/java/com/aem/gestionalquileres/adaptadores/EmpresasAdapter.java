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
import com.aem.gestionalquileres.modelos.Empresa;

public class EmpresasAdapter extends ListAdapter<Empresa, EmpresasAdapter.EmpresaViewHolder> {

    public EmpresasAdapter(@NonNull DiffUtil.ItemCallback<Empresa> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public EmpresaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_empresa, parent, false);
        return new EmpresaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpresaViewHolder holder, int position) {
        Empresa empresa = getItem(position);
        holder.textViewDireccion.setText(empresa.getNombre());
        holder.textViewContactoId.setText(empresa.getContactoId());
        // Set other fields as needed
    }

    public static class EmpresaViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewDireccion;
        private final TextView textViewContactoId;

        public EmpresaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDireccion = itemView.findViewById(R.id.textViewDireccion);
            textViewContactoId = itemView.findViewById(R.id.textViewContactoId);
        }
    }

    public static class EmpresaDiff extends DiffUtil.ItemCallback<Empresa> {
        @Override
        public boolean areItemsTheSame(@NonNull Empresa oldItem, @NonNull Empresa newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Empresa oldItem, @NonNull Empresa newItem) {
            return oldItem.equals(newItem);
        }
    }
}
