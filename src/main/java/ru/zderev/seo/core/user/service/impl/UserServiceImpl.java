package ru.zderev.seo.core.user.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zderev.seo.core.messages.Message;
import ru.zderev.seo.core.user.User;
import ru.zderev.seo.core.user.service.UserService;
import ru.zderev.seo.core.user.web.UserRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo repo;
    private PasswordEncoder encoder;

    public UserServiceImpl(UserRepo repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public User updateUser(User user, User userFromDb) {
//        BeanUtils.copyProperties(user, userFromDb, "id");
        user.setActivity(true);
        copyNonNullProperties(user, userFromDb);
        User updateUser = repo.save(userFromDb);
        return updateUser;
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

    public void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
