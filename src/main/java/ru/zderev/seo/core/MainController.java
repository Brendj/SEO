package ru.zderev.seo.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zderev.seo.core.messages.web.MessageRepo;
import ru.zderev.seo.core.user.User;

import javax.swing.*;
import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final MessageRepo repo;

    @Value("${spring.profile.active}")
    private String profile;

    @Autowired
    public MainController(MessageRepo messageRepo) {
        this.repo = messageRepo;
    }

    @GetMapping
    public String main(Model model, Principal user) {
        HashMap<Object, Object> data = new HashMap<>();

        // TODO: зашифровать пароль
        data.put("profile", user);
        data.put("messages", repo.findAll());

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}
