package api.utils;

import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertNotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiRetrofitAdapterFactory extends CallAdapter.Factory {

    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Type modelType = Object.class;
        if (ParameterizedType.class.isAssignableFrom(returnType.getClass())) {
            Type[] types = ((ParameterizedType) returnType).getActualTypeArguments();
            modelType = types[0];
        }
        return new ApiResponseCallAdapter(modelType);
    }

    class ApiResponseCallAdapter<T> implements CallAdapter<ResponseBody, ApiResponse<T>> {

        private final Type returnType;

        public ApiResponseCallAdapter(Type returnType) {
            this.returnType = returnType;
        }

        @Override
        public Type responseType() {
            return ResponseBody.class;
        }

        @SneakyThrows
        @Override
        public ApiResponse<T> adapt(Call<ResponseBody> call) {
            AtomicReference<Response> result = new AtomicReference<Response>();
            step(step -> {
                Request request = call.request();
                step.name("Отправить запрос " + request.method() + " " + request.url());
                Response response = call.execute();
                result.set(response);
            });
            assertNotNull(result.get(), "Response is null");
            return new ApiResponse(result.get(), this.returnType);
        }

    }

}
