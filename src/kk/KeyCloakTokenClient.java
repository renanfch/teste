package kk;

@FeignClient(
        name = "feignspringauth.KeyCloakTokenClient",
        url = "${feign.auth.keycloak.base-url}",
        configuration = {FeignJaxRsContractConfiguration.class}
)
public interface KeyCloakTokenClient {
    String AUTH_REALMS_REALM_PROTOCOL_OPENID_CONNECT_TOKEN_PATH = "/auth/realms/{realm}/protocol/openid-connect/token";

    @POST
    @Cacheable(
            cacheNames = {"feignspringauth.KeyCloakTokenClient.token"},
            key = "#clientId",
            cacheManager = "feignspringauth.tokenCacheManager"
    )
    @Path("/auth/realms/{realm}/protocol/openid-connect/token")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces({"application/json"})
    KeyCloakTokenDto generateToken(@PathParam("realm") String realm, @FormParam("grant_type") String grantType, @FormParam("client_id") String clientId, @FormParam("client_secret") String clientSecret);
}
