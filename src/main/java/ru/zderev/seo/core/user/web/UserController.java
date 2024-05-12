package ru.zderev.seo.core.user.web;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import ru.zderev.seo.core.dto.EventType;
import ru.zderev.seo.core.messages.Message;
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
        System.out.println(user);
        service.createUser(user);
        System.out.print(user.toString());
        return "User " + user.getEmail() + " created";
    }

    @PutMapping("{id}")
    public String updateUser(@PathVariable("id") User userFromDb, @RequestBody User user) {
        service.updateUser(user, userFromDb);
        return "User " + user.getFirstName() + " updated";
    }
}
