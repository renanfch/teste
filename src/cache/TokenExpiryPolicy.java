package cache;

import java.util.concurrent.TimeUnit;

public class TokenExpiryPolicy implements Expiry<String, ExpirationToken> {
    protected static final int EXPIRATION_MARGIN_IN_SECONDS = 15;

    public TokenExpiryPolicy() {
    }

    public long expireAfterCreate(final @NonNull String key, final @NonNull ExpirationToken authorizationTokenDto, final long currentTime) {
        if (key == null) {
            throw new NullPointerException("key is marked non-null but is null");
        } else if (authorizationTokenDto == null) {
            throw new NullPointerException("authorizationTokenDto is marked non-null but is null");
        } else {
            return this.getExpireTimeFromAuthToken(authorizationTokenDto);
        }
    }

    public long expireAfterUpdate(@NonNull String key, final @NonNull ExpirationToken authorizationTokenDto, final long currentTime, final long currentDuration) {
        if (key == null) {
            throw new NullPointerException("key is marked non-null but is null");
        } else if (authorizationTokenDto == null) {
            throw new NullPointerException("authorizationTokenDto is marked non-null but is null");
        } else {
            return this.getExpireTimeFromAuthToken(authorizationTokenDto);
        }
    }

    public long expireAfterRead(final @NonNull String key, final ExpirationToken authorizationTokenDto, final long currentTime, final long currentDuration) {
        if (key == null) {
            throw new NullPointerException("key is marked non-null but is null");
        } else {
            return currentDuration;
        }
    }

    private long getExpireTimeFromAuthToken(final ExpirationToken authorizationTokenDto) {
        return TimeUnit.SECONDS.toNanos((long)(authorizationTokenDto.getExpiresIn() - 15));
    }
}
