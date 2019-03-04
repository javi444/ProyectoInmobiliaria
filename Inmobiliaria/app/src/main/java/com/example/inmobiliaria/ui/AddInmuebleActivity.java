package com.example.inmobiliaria.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.dto.AddInmuebleDto;
import com.example.inmobiliaria.responses.CategoriaResponse;
import com.example.inmobiliaria.responses.ResponseContainer;
import com.example.inmobiliaria.responses.UserResponse;
import com.example.inmobiliaria.retrofit.generator.ServiceGenerator;
import com.example.inmobiliaria.retrofit.generator.TipoAutenticacion;
import com.example.inmobiliaria.retrofit.services.CategoriaService;
import com.example.inmobiliaria.retrofit.services.InmuebleService;
import com.example.inmobiliaria.retrofit.services.UserService;
import com.example.inmobiliaria.util.UtilToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInmuebleActivity extends AppCompatActivity {
    EditText titulo, descripcion, precio, habitaciones, tamanyo, direccion, codigo, ciudad, provincia, localizacion;
    Button enviar;
    private Spinner categories;
    UserResponse user;
    private String loc, jwt;
    InmuebleService service;
    private List<CategoriaResponse> listCategorias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inmueble);

        jwt = UtilToken.getToken(this);
        user = getUser();

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
        categories = findViewById(R.id.spinner_category);
        localizacion = findViewById(R.id.etLocalizacion);
        cargarCategorias();

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crearInmueble();

               /* String title = titulo.getText().toString().trim();
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
                });*/
            }
        });
    }

    public void crearInmueble() {

        AddInmuebleDto inmueble = new AddInmuebleDto();
        CategoriaResponse categoria = (CategoriaResponse) categories.getSelectedItem();
        inmueble.setTitle(titulo.getText().toString());
        inmueble.setRooms(Long.parseLong(habitaciones.getText().toString()));
        inmueble.setDescription(descripcion.getText().toString());
        inmueble.setAddress(direccion.getText().toString());
        inmueble.setZipcode(codigo.getText().toString());
        inmueble.setCity(ciudad.getText().toString());
        inmueble.setPrice(Long.parseLong(precio.getText().toString()));
        inmueble.setSize(Long.parseLong(tamanyo.getText().toString()));
        inmueble.setProvince(provincia.getText().toString());
        inmueble.setOwnerId(user.getId());
        inmueble.setCategoryId(categoria.getId());
        inmueble.setLoc(localizacion.getText().toString());

        addInmueble(inmueble);

    }

    public void addInmueble(AddInmuebleDto inmueble) {
        service = ServiceGenerator.createService(InmuebleService.class, UtilToken.getToken(this), TipoAutenticacion.JWT);


        Call<AddInmuebleDto> call = service.addInmueble(inmueble);
        call.enqueue(new Callback<AddInmuebleDto>() {
            @Override
            public void onResponse(Call<AddInmuebleDto> call, Response<AddInmuebleDto> response) {

                if (response.isSuccessful()) {

                    System.out.println(response.body());

                    Toast.makeText(AddInmuebleActivity.this, "Created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddInmuebleActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddInmuebleDto> call, Throwable t) {
                Toast.makeText(AddInmuebleActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public UserResponse getUser() {
        final UserResponse user = new UserResponse();
        UserService serviceUser = ServiceGenerator.createService(UserService.class, jwt, TipoAutenticacion.JWT);
        Call<UserResponse> callUser = serviceUser.getUser();

        callUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    user.setId(response.body().getId());
                    user.setEmail(response.body().getEmail());
                    user.setName(response.body().getName());
                    user.setPicture(response.body().getPicture());
                    user.setPassword(response.body().getPassword());
                    Log.d("OK", "Usuario obtenido");
                } else {
                    Log.e("Error", "Error obteniendo usuario");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("Failure", "Error obteniendo usuario");
            }
        });

        return user;
    }

    public void cargarCategorias() {
        CategoriaService serviceCategoria = ServiceGenerator.createService(CategoriaService.class);
        Call<ResponseContainer<CategoriaResponse>> callCategoria = serviceCategoria.listCategorias();

        callCategoria.enqueue(new Callback<ResponseContainer<CategoriaResponse>>() {
            @Override
            public void onResponse(Call<ResponseContainer<CategoriaResponse>> call, Response<ResponseContainer<CategoriaResponse>> response) {
                if (response.isSuccessful()) {
                    int spinnerPosition = 1;
                    Log.d("successCategory", "Categoría obtenida");
                    listCategorias = response.body().getRows();
                    System.out.println(listCategorias);
                    List<String> categorias = new ArrayList<>();

                    ArrayAdapter<CategoriaResponse> adapter =
                            new ArrayAdapter<>(AddInmuebleActivity.this, android.R.layout.simple_spinner_dropdown_item, listCategorias);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categories.setAdapter(adapter);
                    categories.setSelection(listCategorias.size() - 1);
                } else {
                    Toast.makeText(AddInmuebleActivity.this, "Error cargando categorías", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<CategoriaResponse>> call, Throwable t) {
                Toast.makeText(AddInmuebleActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
