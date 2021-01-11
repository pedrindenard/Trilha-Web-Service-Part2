package br.com.alura.agenda.firebase;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import br.com.alura.agenda.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG;

public class AgendaInstaceIDService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(final String token) {
        Call<Void> call = new RetrofitInicializador().getDispositivoService().enviaToken(token);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Token enviado", token);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Token falhou", t.getMessage());
            }
        });
    }
}