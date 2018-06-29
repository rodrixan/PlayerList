package com.projects.rodrixan.playerlist.common;

import okhttp3.logging.HttpLoggingInterceptor;

public final class Constants {
    private Constants() {
    }

    public static String BASE_URL = "https://api.myjson.com/";
    public static final HttpLoggingInterceptor.Level HTTP_LOGGING_LEVEL = HttpLoggingInterceptor
            .Level.BODY;

    public static final String DATE_FORMAT_BODY = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DATA = "dd/MM/yyyy";

}
