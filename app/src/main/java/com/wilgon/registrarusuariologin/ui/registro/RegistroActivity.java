package com.wilgon.registrarusuariologin.ui.registro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wilgon.registrarusuariologin.databinding.ActivityRegistroBinding;
import com.wilgon.registrarusuariologin.modelo.Usuario;
import com.wilgon.registrarusuariologin.ui.login.MainActivity;
import com.wilgon.registrarusuariologin.ui.login.MainActivityViewModel;


public class RegistroActivity extends AppCompatActivity {

    //private static final int REQUEST_PERMISSION_CAMERA = 100;
    //private static final int TAKE_PICTURE = 100;
    //private static final int REQUEST_PERMISION_WRITE_STORAGE = 200;

    private ActivityRegistroBinding binding;
    private RegistroActivityViewModel mv;
    private MainActivityViewModel main;
    private Usuario usuarioactual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        main = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("llamada") && intent.getStringExtra("llamada").equals("login")) {
            Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
            if (usuario != null) {
                binding.EtNombre.setText(usuario.getNombre());
                binding.EtApellido.setText(usuario.getApellido());
                binding.EtDni.setText(String.valueOf(usuario.getDni()));
                binding.EtMail.setText(usuario.getMail());
                binding.EtClave.setText(usuario.getClave());
                usuarioactual = usuario;
            }
        }

        binding.BtRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.registrarUsuario(binding.EtNombre.getText().toString(), binding.EtApellido.getText().toString(), binding.EtDni.getText().toString(), binding.EtMail.getText().toString(), binding.EtClave.getText().toString());
                mv.limpiarCampos(); // Limpiamos los campos despues de registrar
            }
        });

        mv.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {


            }
        });


        binding.BtVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                // Limpiamos la pila de actividades y va al MainActivity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }
}
