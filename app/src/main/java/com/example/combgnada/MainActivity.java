package com.example.combgnada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnPersonas;
    private Button btnProdutos;
    private Button btnOrdenes;
    private Button btnInformes;
    private Button btnConfiguracion;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        bindUI();
        eventos();
    }

    private void bindUI() {
        btnPersonas = findViewById(R.id.btnPersonas);
        btnProdutos = findViewById(R.id.btnProductos);
        btnOrdenes = findViewById(R.id.btnOrdenes);
        btnInformes = findViewById(R.id.btnInformes);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);
    }

    private void eventos() {
        btnPersonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intentar iniciar sesión antes de abrir PersonasActivity
                iniciarSesion("garciashalin50@gmail.com", "TEAMOMAMI");
            }
        });

        btnProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductosActivity.class);
                startActivity(intent);
            }
        });

        btnOrdenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrdenesActivity.class);
                startActivity(intent);
            }
        });

        btnInformes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InformesActivity.class);
                startActivity(intent);
            }
        });

        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfiguracionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarSesion(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                        // Si el inicio de sesión es exitoso, abrir PersonasActivity
                        Intent intent = new Intent(MainActivity.this, PersonasActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
