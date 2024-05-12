package ru.zderev.seo.core.user.service;

import ru.zderev.seo.core.user.User;

public interface UserService {
    public void createUser(User user);
    User findByEmail(String email);
}
