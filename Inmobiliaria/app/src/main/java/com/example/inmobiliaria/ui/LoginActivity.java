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
import com.example.inmobiliaria.fragments.InmueblesFragment;
import com.example.inmobiliaria.responses.LoginResponse;
import com.example.inmobiliaria.retrofit.generator.ServiceGenerator;
import com.example.inmobiliaria.retrofit.services.LoginService;
import com.example.inmobiliaria.util.UtilToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPass;
    Button buttonLogin;
    TextView textViewRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPass = findViewById(R.id.editTextPass);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewRegistro = findViewById(R.id.textViewRegistro);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email_txt = editTextEmail.getText().toString();
                String password_txt = editTextPass.getText().toString();

                // String credentials = email_txt + ":" + password_txt;

                // final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);


                LoginService service = ServiceGenerator.createService(LoginService.class, email_txt, password_txt);
                Call<LoginResponse> call = service.doLogin();

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.code() != 201) {
                            // error
                            Log.e("RequestError", response.message());
                            Toast.makeText(LoginActivity.this, "Error de petición", Toast.LENGTH_SHORT).show();
                        } else {
                            //UtilToken.getToken( MainActivity.this);
                            UtilToken.setToken(LoginActivity.this, response.body().getToken());
                            Log.i("PRUEBA","Entra en evento click");
                            Intent i = new Intent(
                                    LoginActivity.this,
                                    InmueblesFragment.class
                            );

                            startActivity(i);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        textViewRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        LoginActivity.this,
                        RegistroUsuarioActivity.class
                );

                startActivity(i);

            }
        });
    }
}
