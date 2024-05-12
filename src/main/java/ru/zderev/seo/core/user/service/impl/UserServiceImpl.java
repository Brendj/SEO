package ru.zderev.seo.core.user.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zderev.seo.core.user.User;
import ru.zderev.seo.core.user.service.UserService;
import ru.zderev.seo.core.user.web.UserRepo;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo repo;
    private PasswordEncoder encoder;

    public UserServiceImpl(UserRepo repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public void createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> userOptional = repo.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user;
        } else {
            throw new RuntimeException("Not Found");
        }
    }
}
