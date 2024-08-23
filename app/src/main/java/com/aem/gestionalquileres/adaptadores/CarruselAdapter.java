package com.aem.gestionalquileres.adaptadores;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aem.gestionalquileres.R;

public class CarruselAdapter extends RecyclerView.Adapter<CarruselAdapter.CarruselViewHolder> {

    private final int[] icons;
    private final String[] iconNames; // Agregado para los nombres de los iconos
    private final OnIconClickListener listener;

    public interface OnIconClickListener {
        void onIconClick(int position);
    }

    public CarruselAdapter(int[] icons, String[] iconNames, OnIconClickListener listener) {
        this.icons = icons;
        this.iconNames = iconNames;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarruselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrusel_icon, parent, false);
        return new CarruselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarruselViewHolder holder, int position) {
        holder.imageViewIcon.setImageResource(icons[position]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.imageViewIcon.setTooltipText(iconNames[position]); // Establecer el texto del tooltip
        }
        holder.itemView.setOnClickListener(v -> listener.onIconClick(position));
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    static class CarruselViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewIcon;

        CarruselViewHolder(View itemView) {
            super(itemView);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
        }
    }
}
