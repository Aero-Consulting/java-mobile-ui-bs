package api.utils;

import static io.qameta.allure.Allure.addAttachment;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import helpers.JsonHelper;
import io.qameta.allure.Step;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ApiResponse<T> {

    public static final String ALLURE_JSON_TYPE = "application/json";

    private final Response response;
    private final Type responseType;
    private MediaType contentType;
    private String responseBody;

    public ApiResponse(Response<ResponseBody> response, Type modelType) {
        this.response = response;
        this.responseType = modelType;
        try {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    ResponseBody body = response.body();
                    this.contentType = body.contentType();
                    this.responseBody = body.string();
                }
            } else {
                ResponseBody body = response.errorBody();
                this.contentType = body.contentType();
                this.responseBody = body.string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Запрос выполнен успешно")
    public ApiResponse<T> responseShouldBeSuccessful() {
        assertTrue(this.response.isSuccessful());
        return this;
    }

    @Step("Проверить код ответа {statusCode}")
    public ApiResponse<T> responseShouldHaveStatusCode(int statusCode) {
        assertEquals(this.response.code(), statusCode);
        return this;
    }

    @Step("Получить значение заголовока '{header}'")
    public String getHeader(String header) {
        Headers headers = this.response.headers();
        addAttachment("Headers", headers.toString());
        String actual = this.response.headers().get(header);
        assertNotNull(actual);
        return actual;
    }

    public T get() {
        T model = JsonHelper.readFromJson(this.responseBody, this.responseType);
        return model;
    }

}
