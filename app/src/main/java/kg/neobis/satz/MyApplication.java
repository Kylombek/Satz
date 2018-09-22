package kg.neobis.satz;

import android.app.Application;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    public static Api api;
    public static Retrofit retrofit = null;
    private static final String BASE_URL = "https://translate.yandex.net/api/v1.5/";

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

    }
        public static Retrofit getRetrofit() {
            return retrofit;

        }

        public Api getApi() {
            return api;
        }
    }

