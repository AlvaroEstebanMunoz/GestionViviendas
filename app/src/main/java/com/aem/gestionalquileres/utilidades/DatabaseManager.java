package com.aem.gestionalquileres.utilidades;

import com.google.firebase.firestore.FirebaseFirestore;

public class DatabaseManager {
    private static DatabaseManager instance;
    private FirebaseFirestore db;

    private DatabaseManager() {
        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public FirebaseFirestore getFirestore() {
        return db;
    }

    // Aquí puedes agregar métodos específicos de la base de datos según lo que necesites
}
