package cache;

@Configuration
public class TokenCacheConfiguration {
    public static final String BEAN_FEINGSPRINGAUTH_CAFFEINE_CONFIG = "feignspringauth.caffeineConfig";
    public static final String BEAN_FEINGSPRINGAUTH_TOKEN_CACHE_MANAGER = "feignspringauth.tokenCacheManager";

    public TokenCacheConfiguration() {
    }

    @Bean({"feignspringauth.caffeineConfig"})
    public Caffeine caffeineConfig() {
        TokenExpiryPolicy expiryPolicy = new TokenExpiryPolicy();
        return Caffeine.newBuilder().expireAfter(expiryPolicy);
    }

    @Bean({"feignspringauth.tokenCacheManager"})
    public CacheManager cacheManager(@Qualifier("feignspringauth.caffeineConfig") final Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
