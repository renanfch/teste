package kk;

import java.text.MessageFormat;
import java.util.Objects;

public class KeyCloakClientCredentialsAuthInterceptor implements RequestInterceptor {
    static final String GRANT_TYPE = "client_credentials";
    static final String TOKEN_TYPE_PATTERN = "Bearer {0}";
    private final KeyCloakTokenClient authorizationTokenClient;
    private final KeyCloakClientCredentialsProperties clientCredentialsKeyCloakDto;

    public KeyCloakClientCredentialsAuthInterceptor(final KeyCloakTokenClient authorizationTokenClient, final KeyCloakClientCredentialsProperties clientCredentialsKeyCloakDto) {
        this.authorizationTokenClient = (KeyCloakTokenClient) Objects.requireNonNull(authorizationTokenClient);
        this.clientCredentialsKeyCloakDto = (KeyCloakClientCredentialsProperties)Objects.requireNonNull(clientCredentialsKeyCloakDto);
    }

    public void apply(final RequestTemplate requestTemplate) {
        KeyCloakTokenDto token = this.authorizationTokenClient.generateToken(this.clientCredentialsKeyCloakDto.getRealm(), "client_credentials", this.clientCredentialsKeyCloakDto.getClientId(), this.clientCredentialsKeyCloakDto.getClientSecret());
        requestTemplate.header("Authorization", new String[]{MessageFormat.format("Bearer {0}", token.getAccessToken())});
    }
}
