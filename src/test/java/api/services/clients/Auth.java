package api.services.clients;

import static api.services.ClientsService.PATH_SERVICE;

import api.models.clients.auth.AuthResponse;
import api.utils.ApiResponse;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Auth {

    @POST(PATH_SERVICE + "/auth")
    ApiResponse<AuthResponse> customerAuth();

    @POST(PATH_SERVICE + "/refresh")
    ApiResponse<AuthResponse> customerRefresh(@Header("Authorization") String authorizationHeader);

}
