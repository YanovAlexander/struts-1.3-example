package ua.com.struts.service;

import com.authy.AuthyApiClient;
import com.authy.AuthyException;
import com.authy.api.User;

public class AuthyService {

    private static final String PRODUCTION_API_KEY = "Uco4bRwcML65DyBrXWf6Xwj77jPhaAtx";

    private final AuthyApiClient client;

    public AuthyService() {
        this.client = new AuthyApiClient(PRODUCTION_API_KEY);
    }

    public int sendRegistrationRequest(String email, String phoneNumber, String countryCode) {
        try {
            User user = client.getUsers().createUser(email, phoneNumber, countryCode);
            if (user.isOk()) {
                return user.getId();
            } else {
                throw new RuntimeException(user.getError().getMessage());
            }
        } catch (AuthyException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
