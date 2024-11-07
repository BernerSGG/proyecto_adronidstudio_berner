package com.example.combgnada;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PersonasActivity extends AppCompatActivity {

    // Declaración de Firebase Auth y elementos de la interfaz
    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Vincular los elementos de la interfaz de usuario
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        // Configurar el botón para iniciar sesión
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Verificar si los campos no están vacíos
                if (!email.isEmpty() && !password.isEmpty()) {
                    signIn(email, password);
                } else {
                    Toast.makeText(PersonasActivity.this, "Por favor ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para manejar el inicio de sesión
    private void signIn(String email, String password) {
        // Usamos el método signInWithEmailAndPassword para autenticar al usuario
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Si el inicio de sesión es exitoso, mostramos un mensaje y verificamos el usuario
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(PersonasActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                        // Aquí puedes agregar la lógica para abrir otra actividad o continuar
                    } else {
                        // Si falla el inicio de sesión, mostramos un mensaje de error
                        Toast.makeText(PersonasActivity.this, "Error en la autenticación", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}




