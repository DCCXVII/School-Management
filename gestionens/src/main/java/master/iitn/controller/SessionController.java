package master.iitn.controller;

import master.iitn.model.User;

public final class SessionController {
    private static SessionController instance;
    private User user;

    private SessionController(User user) {
        this.user = user;
    }

    public static SessionController getInstance(User user) {
        if (instance == null) {
            instance = new SessionController(user);
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void cleanUserSession() {
        user = null;
    }
}
