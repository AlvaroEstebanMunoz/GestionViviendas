package com.aem.gestionalquileres.adaptadores;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aem.gestionalquileres.R;
import com.aem.gestionalquileres.modelos.Casa;
import com.bumptech.glide.Glide;

public class CasasAdapter extends ListAdapter<Casa, CasasAdapter.CasaViewHolder> {

    // Interfaz para manejar el evento de doble toque
    public interface OnItemClickListener {
        void onItemDoubleTap(Casa casa);
    }

    private final OnItemClickListener onItemClickListener;

    // Constructor modificado para aceptar el listener
    public CasasAdapter(@NonNull DiffUtil.ItemCallback<Casa> diffCallback, OnItemClickListener listener) {
        super(diffCallback);
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public CasaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_casa_card, parent, false);
        return new CasaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CasaViewHolder holder, int position) {
        Casa casa = getItem(position);
        holder.bind(casa);
    }

    public class CasaViewHolder extends RecyclerView.ViewHolder {
        private final ImageView foto;
        private final TextView alias;
        private final GestureDetector gestureDetector;

        public CasaViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.foto);
            alias = itemView.findViewById(R.id.alias);

            // Configurar GestureDetector para manejar el doble toque
            gestureDetector = new GestureDetector(itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemDoubleTap(getItem(position));
                            return true;
                        }
                    }
                    return false;
                }
            });

            // Configurar el listener de toques
            itemView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
        }

        public void bind(Casa casa) {
            alias.setText(String.valueOf(casa.getAlias()));

            // Cargar la imagen desde la URL
            Glide.with(itemView.getContext())
                    .load(casa.getFoto())
                    .placeholder(R.drawable.placeholder_image) // Imagen de placeholder
                    .into(foto);
        }
    }

    public static class CasaDiff extends DiffUtil.ItemCallback<Casa> {
        @Override
        public boolean areItemsTheSame(@NonNull Casa oldItem, @NonNull Casa newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Casa oldItem, @NonNull Casa newItem) {
            return oldItem.equals(newItem);
        }
    }
}
