package eduspace.vendor;

import eduspace.model.user;

public class loggedIn {
    private static user user;

    public static user getUser() {
        return user;
    }

    public static void setUser(user user) {
        loggedIn.user = user;
    }
}
