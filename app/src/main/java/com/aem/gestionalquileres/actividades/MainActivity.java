package com.aem.gestionalquileres.actividades;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.aem.gestionalquileres.R;
import com.google.android.material.navigation.NavigationView;

import com.aem.gestionalquileres.fragmentos.CasasFragment;
import com.aem.gestionalquileres.fragmentos.PersonasFragment;
import com.aem.gestionalquileres.fragmentos.EmpresasFragment;
import com.aem.gestionalquileres.fragmentos.ContactosFragment;
import com.aem.gestionalquileres.fragmentos.ServiciosFragment;
import com.aem.gestionalquileres.fragmentos.RecibosFragment;
import com.aem.gestionalquileres.fragmentos.AlquileresFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura el Toolbar como ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configura el DrawerLayout y ActionBarDrawerToggle
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new CasasFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_casas);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_casas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new CasasFragment()).commit();
        } else if (id == R.id.nav_personas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new PersonasFragment()).commit();
        } else if (id == R.id.nav_empresas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new EmpresasFragment()).commit();
        } else if (id == R.id.nav_contactos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new ContactosFragment()).commit();
        } else if (id == R.id.nav_servicios) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new ServiciosFragment()).commit();
        } else if (id == R.id.nav_recibos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new RecibosFragment()).commit();
        } else if (id == R.id.nav_alquileres) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new AlquileresFragment()).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
