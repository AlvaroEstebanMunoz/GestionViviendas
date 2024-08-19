package com.aem.gestionalquileres.actividades;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aem.gestionalquileres.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavController navController; // Añadido para el NavController
    private AppBarConfiguration appBarConfiguration; // Añadido para la configuración de la AppBar

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

        // Configura el NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }


        // Configura la AppBarConfiguration
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_casas, R.id.nav_personas, R.id.nav_empresas, R.id.nav_contactos,
                R.id.nav_servicios, R.id.nav_recibos, R.id.nav_alquileres)
                .setDrawerLayout(drawer)
                .build();

        // Configura la navegación para la AppBar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        if (savedInstanceState == null) {
            // Reemplaza el fragmento actual por CasasFragment al iniciar la aplicación
            navController.navigate(R.id.casasFragment);
            navigationView.setCheckedItem(R.id.nav_casas);
        }

        // Configura el OnBackPressedCallback
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    if (navController != null && navController.getCurrentDestination() != null) {
                        // Usar NavController para manejar el retroceso
                        navController.navigateUp();
                    } else {
                        finish();
                    }
                }
            }
        });
    }
    public NavController getNavController() {
        return navController;
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        // Si tienes un menú de opciones, asegúrate de inflarlo aquí
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Maneja la acción del menú aquí
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Usa el NavController para manejar la navegación
        if (navController != null) {
            if (id == R.id.nav_casas) {
                navController.navigate(R.id.nav_casas);
            } else if (id == R.id.nav_personas) {
                navController.navigate(R.id.nav_personas);
            } else if (id == R.id.nav_empresas) {
                navController.navigate(R.id.nav_empresas);
            } else if (id == R.id.nav_contactos) {
                navController.navigate(R.id.nav_contactos);
            } else if (id == R.id.nav_servicios) {
                navController.navigate(R.id.nav_servicios);
            } else if (id == R.id.nav_recibos) {
                navController.navigate(R.id.nav_recibos);
            } else if (id == R.id.nav_alquileres) {
                navController.navigate(R.id.nav_alquileres);
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Manejar la acción del botón de navegación hacia arriba
        return navController != null && navController.navigateUp() || super.onSupportNavigateUp();
    }
}
