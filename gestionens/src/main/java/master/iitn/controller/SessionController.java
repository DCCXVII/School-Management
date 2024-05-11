package master.iitn.controller;

import master.iitn.model.User;

public final class SessionController {
    private static SessionController instance = new SessionController();
    private User user;

    private SessionController() {
    }

    public static SessionController getInstance() {

        return instance;
    }

    public User getUser() {
        return user;

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void cleanUserSession() {
        this.user = null;
    }
}