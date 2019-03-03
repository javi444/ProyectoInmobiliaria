package com.example.inmobiliaria.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.models.Inmueble;
import com.example.inmobiliaria.models.Registro;
import com.example.inmobiliaria.responses.LoginResponse;
import com.example.inmobiliaria.retrofit.generator.ServiceGenerator;
import com.example.inmobiliaria.retrofit.services.InmuebleService;
import com.example.inmobiliaria.retrofit.services.LoginService;
import com.example.inmobiliaria.util.UtilToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInmuebleActivity extends AppCompatActivity {
    EditText titulo, descripcion, precio, habitaciones, tamanyo, direccion, codigo, ciudad, provincia;
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inmueble);

        titulo = findViewById(R.id.etTitulo);
        descripcion = findViewById(R.id.etDescripcion);
        precio = findViewById(R.id.etPrecio);
        habitaciones = findViewById(R.id.etHabitaciones);
        tamanyo = findViewById(R.id.etSize);
        direccion = findViewById(R.id.etSize);
        codigo = findViewById(R.id.etCodigo);
        ciudad = findViewById(R.id.etCiudad);
        provincia = findViewById(R.id.etProvincia);
        enviar = findViewById(R.id.buttonRegistro);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = titulo.getText().toString().trim();
                String description = descripcion.getText().toString().trim();
                Double price = Double.valueOf(precio.getText().toString().trim());
                Integer rooms = Integer.valueOf(habitaciones.getText().toString().trim());
                Double size = Double.valueOf(tamanyo.getText().toString().trim());
                String address = direccion.getText().toString().trim();
                String zipcode = codigo.getText().toString().trim();
                String city = ciudad.getText().toString().trim();
                String province = provincia.getText().toString().trim();

                Inmueble inmueble = new Inmueble(title, description, price, rooms, size, address, zipcode, city, province);

                InmuebleService service = ServiceGenerator.createService(InmuebleService.class);

                Call<Inmueble> inmuebleCall = service.addInmueble();

                inmuebleCall.enqueue(new Callback<Inmueble>() {
                    @Override
                    public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                        if (response.code() == 201) {

                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<Inmueble> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());

                    }
                });
            }
        });
    }
}
