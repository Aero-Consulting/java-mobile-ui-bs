package api.models.clients.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Token {

    @JsonProperty("accessToken")
    private String accessToken;
    @JsonProperty("refreshToken")
    private String refreshToken;
    @JsonProperty("tokenType")
    private String tokenType;
    @JsonProperty("expiresIn")
    private Integer expiresIn;
    @JsonProperty("expiresAt")
    private Integer expiresAt;
    @JsonProperty("sessionState")
    private String sessionState;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("refreshExpiresIn")
    private Integer refreshExpiresIn;

}
