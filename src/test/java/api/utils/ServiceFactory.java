package api.utils;

import api.services.Service;
import helpers.config.TestConfiguration;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ServiceFactory {

    private static final JacksonConverterFactory converterFactory = JacksonConverterFactory.create();

    public static <S extends Service> S create(Class<S> serviceClass) {
        ApiInterceptor apiInterceptor = new ApiInterceptor();
        OkHttpClient httpClient = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(apiInterceptor)
            .build();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TestConfiguration.getApiUrl())
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(new ApiRetrofitAdapterFactory())
            .build();
        return retrofit.create(serviceClass);
    }

}
