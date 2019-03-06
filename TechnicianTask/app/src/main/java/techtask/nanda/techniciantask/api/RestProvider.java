package techtask.nanda.techniciantask.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import techtask.nanda.techniciantask.config.Constants;

/**
 * Created by ASUS on 11/12/2018.
 */

public class RestProvider {
    private static OkHttpClient client;
    private static Retrofit retrofitTeknisiController = null;
    public static final int TIMEOUT = 30;   //seconds timeout

    public static OkHttpClient okHttpClient(){
        client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        return client;
    }

    public static Retrofit getTeknisiController(){
        OkHttpClient client = okHttpClient();

        if(retrofitTeknisiController == null){
            retrofitTeknisiController = new Retrofit.Builder()
                    .baseUrl(Constants.TEKNISI_CONTROLLER_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitTeknisiController;
    }
}
