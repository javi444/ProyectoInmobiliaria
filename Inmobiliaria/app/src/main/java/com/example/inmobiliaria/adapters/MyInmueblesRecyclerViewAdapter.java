package com.example.inmobiliaria.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.models.Inmueble;
import com.example.inmobiliaria.retrofit.services.InmuebleInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Inmueble} and makes a call to the
 * specified {@link InmuebleInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyInmueblesRecyclerViewAdapter extends RecyclerView.Adapter<MyInmueblesRecyclerViewAdapter.ViewHolder> {

    private final List<Inmueble> mValues;
    private final InmuebleInteractionListener mListener;
    Context ctx;

    public MyInmueblesRecyclerViewAdapter(Context ctx,int layout, List<Inmueble> items, InmuebleInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_inmuebles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTitulo.setText(holder.mItem.getTitle());
        holder.tvDireccion.setText(holder.mItem.getAddress());
        holder.tvPrecio.setText(String.valueOf(holder.mItem.getPrice()));
        holder.tvHabitaciones.setText(String.valueOf(holder.mItem.getRooms()));
        holder.tvSize.setText(String.valueOf(holder.mItem.getSize()));


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView  tvTitulo;
        public final TextView tvDireccion;
        public final TextView tvPrecio;
        public final TextView tvHabitaciones;
        public final TextView tvSize;
        public Inmueble mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTitulo = view.findViewById(R.id.tvTitulo);
            tvDireccion = view.findViewById(R.id.tvDireccion);
            tvPrecio = view.findViewById(R.id.tvPrecio);
            tvHabitaciones = view.findViewById(R.id.tvHabitaciones);
            tvSize = view.findViewById(R.id.tvSize);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTitulo.getText() + "'";
        }
    }
}
