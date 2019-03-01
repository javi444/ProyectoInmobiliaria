package com.example.inmobiliaria.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.inmobiliaria.R;
import com.example.inmobiliaria.fragments.InmueblesFragment;
import com.example.inmobiliaria.retrofit.services.InmuebleInteractionListener;
import com.example.inmobiliaria.util.UtilToken;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, InmuebleInteractionListener {

    private Toolbar toolbar;
    private NavigationView navigationView;
    TextView nav_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        hideMenuItem();
        setSupportActionBar(toolbar);
        nav_login = findViewById(R.id.nav_login);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new InmueblesFragment(), "inmueblesFrag").commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    private void hideMenuItem() {
        Menu items = navigationView.getMenu();

        if (UtilToken.getToken(DashboardActivity.this) == null) {
            items.findItem(R.id.nav_fav).setVisible(false);
            items.findItem(R.id.nav_mis).setVisible(false);
            items.findItem(R.id.nav_inmuebles).setVisible(false);
            items.findItem(R.id.nav_user).setVisible(false);
            items.findItem(R.id.nav_exit).setVisible(false);
        }else{
            items.findItem(R.id.nav_login).setVisible(false);
            items.findItem(R.id.nav_registro).setVisible(false);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_inmuebles) {

        } else if (id == R.id.nav_fav) {

        } else if (id == R.id.nav_mis) {

        } else if (id == R.id.nav_user) {

        } else if (id == R.id.nav_exit) {

        } else if (id == R.id.nav_login){
            Intent i = new Intent(
                    DashboardActivity.this,
                    LoginActivity.class
            );

            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mostrarInfoUsuarioMenu() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);

        ImageView iv = headerView.findViewById(R.id.picture);
        TextView name = headerView.findViewById(R.id.userName);
        TextView email = headerView.findViewById(R.id.emailUser);

        name.setText(UtilToken.getNombreUser(DashboardActivity.this).substring(0, 1).toUpperCase() + UtilToken.getNombreUser(DashboardActivity.this).substring(1));
        email.setText(UtilToken.getEmailUser(DashboardActivity.this));
        Glide.with(this).load(UtilToken.getPhotoUser(DashboardActivity.this)).apply(RequestOptions.circleCropTransform()).into(iv);

    }
}
