package com.aem.gestionalquileres.actividades;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aem.gestionalquileres.R;
import com.aem.gestionalquileres.utilidades.DatabaseManager;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavController navController; // Añadido para el NavController
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura el Toolbar como ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();  // Usar getSupportActionBar() en lugar de ActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // Mostrar la flecha
            actionBar.setHomeButtonEnabled(true);       // Habilitar el botón
        }
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
        // Añadido para la configuración de la AppBar
         appBarConfiguration = new AppBarConfiguration.Builder(
        //        R.id.nav_casas, R.id.nav_personas, R.id.nav_empresas, R.id.nav_contactos,
        //        R.id.nav_servicios, R.id.nav_recibos, R.id.nav_alquileres)
                R.id.casasFragment,
                R.id.alquileresFragment, R.id.recibosFragment)
                .setOpenableLayout(drawer)
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
                } else if (navController != null && navController.popBackStack()) {
                    // Si hay un destino previo, regresa a él
                } else {
                    // Si no hay destinos previos, cierra la actividad
                    finish();
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
        Log.d("MainActivity", "onOptionsItemSelected called for " + item.getItemId());
        if (item.getItemId() == android.R.id.home) {
            // Abre el DrawerLayout o maneja la navegación
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                // En caso de que el Drawer no esté abierto, navega hacia atrás
                getOnBackPressedDispatcher().onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!navController.popBackStack()) {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Usa el NavController para manejar la navegación
        if (navController != null) {
            if (id == R.id.nav_casas) {
                navController.navigate(R.id.casasFragment);
            } else if (id == R.id.nav_personas) {
                navController.navigate(R.id.personasFragment);
            } else if (id == R.id.nav_empresas) {
                navController.navigate(R.id.empresasFragment);
            } else if (id == R.id.nav_contactos) {
                navController.navigate(R.id.contactosFragment);
            } else if (id == R.id.nav_servicios) {
                navController.navigate(R.id.serviciosFragment);
            } else if (id == R.id.nav_recibos) {
                navController.navigate(R.id.recibosFragment);
            } else if (id == R.id.nav_alquileres) {
                navController.navigate(R.id.alquileresFragment);
            } else if (id == R.id.nav_salir) {
                cerrarConexionBD(); // Método para cerrar la conexión a la BBDD
                finishAffinity(); // Cierra la aplicación de forma segura
                return true;
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Imprime los fragmentos en la pila de navegación
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            Log.d("NavStack", "Fragment: " + fragment.getClass().getSimpleName());
        }

        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    private void cerrarConexionBD() {
        // Verifica si la conexión está abierta y ciérrala
        DatabaseManager.getInstance().getFirestore().terminate();
    }
}
