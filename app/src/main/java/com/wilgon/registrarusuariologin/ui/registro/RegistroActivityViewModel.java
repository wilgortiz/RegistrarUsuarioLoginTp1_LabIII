package com.wilgon.registrarusuariologin.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.wilgon.registrarusuariologin.modelo.Usuario;
import com.wilgon.registrarusuariologin.request.ApiClient;
import com.wilgon.registrarusuariologin.ui.login.MainActivity;


public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient apiClient;

    private MutableLiveData<Usuario> usuarioM;
    private MutableLiveData<Bitmap> fotoM;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
        apiClient = new ApiClient();

    }

    public LiveData<Bitmap> getFoto() {
        if (fotoM == null) {
            fotoM = new MutableLiveData<>();
        }
        return fotoM;
    }

    public LiveData<Usuario> getUsuarioM() {
        if (usuarioM == null) {
            usuarioM = new MutableLiveData<>();
        }
        return usuarioM;
    }

    public void registrarUsuario(String nombre, String apellido, String dni, String mail, String clave) {
        Long dniLong = Long.parseLong(dni);
        Usuario usuario = new Usuario(nombre, apellido, dniLong, mail, clave);

        apiClient.guardarUsuario(usuario, context);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void leerUsuario() {
        if (apiClient.leerUsuario(context) == null) {
            Toast.makeText(context, "esta vacio", Toast.LENGTH_LONG).show();
        } else {
            usuarioM.setValue(apiClient.leerUsuario(context));
        }
    }


    public void limpiarCampos() {
        usuarioM.setValue(new Usuario("", "", 0L, "", ""));
    }


}

