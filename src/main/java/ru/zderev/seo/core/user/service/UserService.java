package ru.zderev.seo.core.user.service;

import ru.zderev.seo.core.user.User;

public interface UserService {
    public void createUser(User user);

    public User updateUser(User user, User userFromDb);
    User findByEmail(String email);
}
