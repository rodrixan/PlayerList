package com.projects.rodrixan.playerlist.common.rest;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.projects.rodrixan.playerlist.common.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean(scope = EBean.Scope.Singleton)
public class PlayerService {
    private static final String TAG = PlayerService.class.getSimpleName();

    private static volatile Retrofit retrofit;
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Constants.BASE_URL);
    private PlayerApi apiService;

    @AfterInject
    public void privateRestClientAfterInject() {
        Log.d(TAG, "initialize");
        this.apiService = createService(PlayerApi.class);
    }

    private static <S> S createService(Class<S> serviceClass) {
        final Gson gson = createGson();
        OkHttpClient client = getOkHttpClient();

        retrofit = builder.client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(serviceClass);
    }

    @NonNull
    private static Gson createGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Date.class,
                new DateDeserializer());
        return gsonBuilder.create();
    }

    @NonNull
    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(Constants.HTTP_LOGGING_LEVEL);
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public PlayerApi getApiService() {
        if (apiService == null) {
            privateRestClientAfterInject();
        }
        return apiService;
    }

    static class DateDeserializer implements JsonDeserializer<Date> {

        private static final String[] DATE_FORMATS = new String[]{Constants.DATE_FORMAT_BODY,};

        @Override
        public Date deserialize(JsonElement jsonElement, Type typeOF, JsonDeserializationContext
                context) throws JsonParseException {
            for (String format : DATE_FORMATS) {
                try {
                    return new SimpleDateFormat(format).parse(jsonElement.getAsString());
                }
                catch (ParseException e) {
                    Log.d(TAG,
                            "Can't parse date: " + jsonElement.getAsString() + " to [" + format +
                                    "] format");
                }
            }
            Log.e(TAG,
                    "Unparseable date: " + jsonElement.getAsString() + ". Supported formats: " +
                            "" + Arrays
                            .toString(DATE_FORMATS));
            return null;
        }
    }
}
