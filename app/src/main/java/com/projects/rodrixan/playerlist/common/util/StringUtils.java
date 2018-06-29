package com.projects.rodrixan.playerlist.common.util;

import android.util.Log;

import com.projects.rodrixan.playerlist.common.Constants;
import com.projects.rodrixan.playerlist.common.rest.PlayerService;
import com.projects.rodrixan.playerlist.model.ErrorResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public final class StringUtils {
    private static final String TAG = StringUtils.class.getSimpleName();

    private static final String NAME_SEPARATOR = " ";

    private StringUtils() {
    }

    public static <T> ErrorResponse parseErrorFromService(final Response<T> response, String
            defaultMessage) {

        if (response == null || response.errorBody() == null || PlayerService.getRetrofit()
                == null) {
            return new ErrorResponse(defaultMessage);
        }

        final Converter<ResponseBody, ErrorResponse> converter = PlayerService.getRetrofit()
                .responseBodyConverter(ErrorResponse.class, new Annotation[0]);

        final ErrorResponse error;

        try {
            if (converter == null) {
                return new ErrorResponse(defaultMessage);
            }
            error = converter.convert(response.errorBody());
        }
        catch (final IOException e) {
            Log.e(TAG, "Error while parsing error response: " + e.getMessage(), e);
            return new ErrorResponse(defaultMessage);
        }

        return error;
    }

    public static String parseDDMMYYYDate(Date date) {
        if (date == null) {
            return "";
        }
        final DateFormatSymbols sym = DateFormatSymbols.getInstance();
        return new SimpleDateFormat(Constants.DATE_FORMAT_DATA, sym).format(date);
    }
}
