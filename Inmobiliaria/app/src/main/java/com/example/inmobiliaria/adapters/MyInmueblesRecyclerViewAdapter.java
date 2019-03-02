package com.example.inmobiliaria.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.inmobiliaria.R;
import com.example.inmobiliaria.models.Inmueble;

import com.example.inmobiliaria.responses.InmuebleResponse;

import com.example.inmobiliaria.retrofit.generator.ServiceGenerator;
import com.example.inmobiliaria.retrofit.generator.TipoAutenticacion;
import com.example.inmobiliaria.retrofit.services.InmuebleInteractionListener;
import com.example.inmobiliaria.retrofit.services.InmuebleService;
import com.example.inmobiliaria.ui.DetallesActivity;
import com.example.inmobiliaria.ui.LoginActivity;
import com.example.inmobiliaria.util.UtilToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Inmueble} and makes a call to the
 * specified {@link InmuebleInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyInmueblesRecyclerViewAdapter extends RecyclerView.Adapter<MyInmueblesRecyclerViewAdapter.ViewHolder> {

    private final List<Inmueble> mValues;
    private final InmuebleInteractionListener mListener;
    private final Context ctx;
    private String jwt;
    private InmuebleService inmuebleService;
    private List<Inmueble> inmueblesList;
    private boolean esFav;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        jwt = UtilToken.getToken(ctx);
        holder.mItem = mValues.get(position);
        holder.tvTitulo.setText(holder.mItem.getTitle());
        holder.tvDireccion.setText(holder.mItem.getAddress());
        holder.tvPrecio.setText(String.valueOf(holder.mItem.getPrice()));
        holder.tvHabitaciones.setText(String.valueOf(holder.mItem.getRooms()));
        holder.tvSize.setText(String.valueOf(holder.mItem.getSize()));
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, DetallesActivity.class);
                i.putExtra("id", mValues.get(position).getId());
                ctx.startActivity(i);
            }
        });

        if(UtilToken.getToken(ctx) == null){
            holder.fav.setVisibility(View.GONE);
        }

        if (holder.mItem.getPhotos() != null) {
            Glide.with(ctx).load(holder.mItem.getPhotos().get(0)).into(holder.imagen);
        } else {
            Glide.with(ctx).load("http://www.bellezaverde.es/wp-content/uploads/2017/08/wnetrze-w-szarosciach-nowoczesne-13.jpg").into(holder.imagen);}

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (jwt == null) {
                    Intent i = new Intent(ctx, LoginActivity.class);
                    ctx.startActivity(i);
                } else {

                    if (esFav = mValues.get(position).isEsFav()){
                        inmueblesList = new ArrayList<>();
                        inmuebleService = ServiceGenerator.createService(InmuebleService.class, UtilToken.getToken(ctx), TipoAutenticacion.JWT);

                        Call<InmuebleResponse> call = inmuebleService.deleteFavoritos(holder.mItem.getId());
                        call.enqueue(new Callback<InmuebleResponse>() {

                            @Override
                            public void onResponse(Call<InmuebleResponse> call, Response<InmuebleResponse> response) {
                                if (response.isSuccessful()) {
                                    // error
                                    Log.e("RequestSuccessful", response.message());
                                    holder.fav.setImageResource(R.drawable.like);
                                    esFav = ! esFav;

                                } else {
                                    Log.e("RequestError", response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<InmuebleResponse> call, Throwable t) {
                                Log.e("NetworkFailure", t.getMessage());

                            }
                        });
                    }else{
                        inmueblesList = new ArrayList<>();
                        inmuebleService = ServiceGenerator.createService(InmuebleService.class, UtilToken.getToken(ctx), TipoAutenticacion.JWT);

                        Call<InmuebleResponse> call = inmuebleService.addFavoritos(holder.mItem.getId());
                        call.enqueue(new Callback<InmuebleResponse>() {

                            @Override
                            public void onResponse(Call<InmuebleResponse> call, Response<InmuebleResponse> response) {
                                if (response.isSuccessful()) {
                                    // error
                                    Log.e("RequestSuccessful", response.message());
                                    holder.fav.setImageResource(R.drawable.likecolor);
                                    esFav = ! esFav;

                                } else {
                                    Log.e("RequestError", response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<InmuebleResponse> call, Throwable t) {
                                Log.e("NetworkFailure", t.getMessage());

                            }
                        });
                    }

                }

            }
        });
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
        public final ImageView imagen;
        public final ImageView fav;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTitulo = view.findViewById(R.id.tvTitulo);
            tvDireccion = view.findViewById(R.id.tvDireccion);
            tvPrecio = view.findViewById(R.id.tvPrecio);
            tvHabitaciones = view.findViewById(R.id.tvHabitaciones);
            tvSize = view.findViewById(R.id.tvSize);
            imagen = view.findViewById(R.id.picture);
            fav = view.findViewById(R.id.iconFav);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTitulo.getText() + "'";
        }
    }
}
