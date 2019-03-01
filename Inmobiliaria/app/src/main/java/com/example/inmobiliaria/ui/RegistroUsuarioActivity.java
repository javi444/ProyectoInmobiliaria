package com.example.inmobiliaria.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.models.Registro;
import com.example.inmobiliaria.responses.LoginResponse;
import com.example.inmobiliaria.retrofit.generator.ServiceGenerator;
import com.example.inmobiliaria.retrofit.services.LoginService;
import com.example.inmobiliaria.util.UtilToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroUsuarioActivity extends AppCompatActivity {

    EditText editTextEmail2, editTextNombre, editTextPass2;
    Button buttonRegistro;
    TextView tvVolverLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        editTextEmail2 = findViewById(R.id.editTextEmail2);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextPass2 = findViewById(R.id.editTextPass2);
        buttonRegistro = findViewById(R.id.buttonRegistro);
        tvVolverLogin = findViewById(R.id.tvVolverLogin);

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

                            UtilToken.setToken(RegistroUsuarioActivity.this, response.body().getToken());

                        } else {

                            Toast.makeText(RegistroUsuarioActivity.this, "Error en el registro. Revise los datos introducidos", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(RegistroUsuarioActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        tvVolverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        RegistroUsuarioActivity.this,
                        LoginActivity.class
                );

                startActivity(i);

            }
        });
    }
}
