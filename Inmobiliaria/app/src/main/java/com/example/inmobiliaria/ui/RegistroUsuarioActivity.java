package com.example.inmobiliaria.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.models.Registro;

public class RegistroUsuarioActivity extends AppCompatActivity {

    EditText editTextEmail2, editTextNombre, editTextPass2;
    Button buttonRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        editTextEmail2 = findViewById(R.id.editTextEmail2);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextPass2 = findViewById(R.id.editTextPass2);
        buttonRegistro = findViewById(R.id.buttonRegistro);

        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextNombre.getText().toString().trim();
                String email = editTextEmail2.getText().toString().trim();
                String password = editTextPass2.getText().toString().trim();

                Registro registro = new Registro(email, password, name);


                LoginService service = ServiceGenerator.createService(LoginService.class);


                Call<LoginResponse> loginReponseCall = service.doRegister(registro);


                loginReponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.code() == 201) {
                            // éxito
                            // startActivity(new Intent(RegistroActivity.this, NuevoActivity.class));
                            //Toast.makeText(RegistroUsuario.this, "Usuario registrado y logeado con éxito", Toast.LENGTH_LONG).show();
                            //Log.d("token", response.body().getToken());

                            //ServiceGenerator.jwtToken = response.body().getToken();
                            UtilToken.setToken(RegistroUsuario.this, response.body().getToken());
                            startActivity(new Intent(RegistroUsuario.this, PaginaPrincipal2.class));

                        } else {
                            // error
                            Toast.makeText(RegistroUsuario.this, "Error en el registro. Revise los datos introducidos", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(RegistroUsuario.this, "Error de conexión", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
