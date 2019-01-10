package com.sample.pooja.sampleapplication.api;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.pooja.sampleapplication.utils.AppConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements AppConstants {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    private static Context context;
    private static Gson gson = new GsonBuilder().setLenient().create();
    private static Retrofit.Builder builder;

    private static Retrofit retrofit;

   /* public static <S> S createService(Context ctx, Class<S> serviceClass) {
        return createService(ctx, serviceClass);
    }
*/
    public static <S> S createService(Context ctx,
                                      Class<S> serviceClass) {
        builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson));

        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {

            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
