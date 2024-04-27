package ru.zderev.seo.core.user.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zderev.seo.core.user.User;
import ru.zderev.seo.core.user.service.impl.UserServiceImpl;

@RestController
@RequestMapping("user")
public class UserController {
    private UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("new-user")
    public String createUser(@RequestBody User user) {
        service.createUser(user);
        System.out.print(user.toString());
        return "User " + user.getEmail() + " created";
    }
}
