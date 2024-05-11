package com.wilgon.registrarusuariologin.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wilgon.registrarusuariologin.modelo.Usuario;
import com.wilgon.registrarusuariologin.request.ApiClient;
import com.wilgon.registrarusuariologin.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient apiClient;
    private MutableLiveData<Usuario> usuarioM;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
        apiClient = new ApiClient();


    }

    public LiveData<Usuario> getUsuarioM() {
        if (usuarioM == null) {
            usuarioM = new MutableLiveData<>();
        }
        return usuarioM;
    }





    /*
    public void loginUsuario(String mail, String clave) {
        Usuario usuario = apiClient.loginUsuario(context, mail, clave);
        if (usuario != null) {
            usuarioM.setValue(usuario);
            Intent intent = new Intent(context, RegistroActivity.class);
            intent.putExtra("usuario",usuario);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        } else {
            Toast.makeText(context, "Ingrese valores correctos", Toast.LENGTH_LONG).show();
        }
    }
}
*/


    public void loginUsuario(String mail, String clave) {
        Usuario usuario = apiClient.loginUsuario(context, mail, clave);
        if (usuario != null) {
            usuarioM.setValue(usuario);
            Intent intent = new Intent(context, RegistroActivity.class);
            intent.putExtra("usuario", usuario);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("llamada", "login");
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Ingrese valores correctos", Toast.LENGTH_LONG).show();
        }
    }
}






