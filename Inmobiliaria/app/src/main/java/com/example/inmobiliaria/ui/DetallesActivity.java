package com.example.inmobiliaria.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.adapters.ImagenAdapter;
import com.example.inmobiliaria.adapters.MyInmueblesRecyclerViewAdapter;
import com.example.inmobiliaria.models.Inmueble;
import com.example.inmobiliaria.responses.DetallesResponseContainer;
import com.example.inmobiliaria.responses.ResponseContainer;
import com.example.inmobiliaria.retrofit.generator.ServiceGenerator;
import com.example.inmobiliaria.retrofit.services.InmuebleService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesActivity extends AppCompatActivity {
    private List<Inmueble> inmueblesList;
    Context ctx;
    TextView titulo, precio, descripcion, habitaciones, metrosCuadrados, codigoPostal, direccion, categoria, ciudad, provincia;
    Inmueble inmueble;
    ImagenAdapter adapter;
    List<String> imagenes;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        Intent i = getIntent();
        String idInmueble = i.getStringExtra("id");
        cargarDetalles();

        inmueblesList = new ArrayList<>();
        InmuebleService service = ServiceGenerator.createService(InmuebleService.class);
        //---------------
        Call<DetallesResponseContainer<Inmueble>> call = service.getInmueble(idInmueble);

        call.enqueue(new Callback<DetallesResponseContainer<Inmueble>>() {

            @Override
            public void onResponse(Call<DetallesResponseContainer<Inmueble>> call, Response<DetallesResponseContainer<Inmueble>> response) {
                if (response.isSuccessful()) {
                    // error
                    Log.e("RequestSuccessful", response.message());
                    inmueble = response.body().getRows();

                    setearDetalles();

                } else {
                    Log.e("RequestError", response.message());
                }
            }

            @Override
            public void onFailure(Call<DetallesResponseContainer<Inmueble>> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                //Toast.makeText(FotoActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void cargarDetalles() {

        ctx=this;

        titulo = findViewById(R.id.tvTitulo);
        precio = findViewById(R.id.tvPrecio);
        descripcion = findViewById(R.id.tvDescripcion);
        habitaciones = findViewById(R.id.tvHabitaciones);
        metrosCuadrados = findViewById(R.id.tvSize);
        codigoPostal = findViewById(R.id.tvCodigo);
        direccion = findViewById(R.id.tvDireccion);
        categoria = findViewById(R.id.tvCategoria);
        ciudad = findViewById(R.id.tvCiudad);
        provincia = findViewById(R.id.tvProvincia);
        pager = findViewById(R.id.imagenInmueble);

    }

    private void setearDetalles() {

        adapter=new ImagenAdapter(DetallesActivity.this, inmueble.getPhotos());
        pager.setAdapter(adapter);
        titulo.setText(inmueble.getTitle());
        categoria.setText(inmueble.getCategoryId().getName());
        descripcion.setText(inmueble.getDescription());
        ciudad.setText(inmueble.getCity() + " - " + inmueble.getProvince());
        direccion.setText(inmueble.getAddress());
        precio.setText(String.valueOf(inmueble.getPrice()) + " €/mes");
        metrosCuadrados.setText(String.valueOf(inmueble.getSize()) + "m/2");
        habitaciones.setText(String.valueOf(inmueble.getRooms()));
        codigoPostal.setText(inmueble.getZipcode());

    }
}
