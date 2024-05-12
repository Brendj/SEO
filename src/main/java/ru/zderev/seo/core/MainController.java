package ru.zderev.seo.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zderev.seo.core.messages.web.MessageRepo;
import ru.zderev.seo.core.user.User;
import ru.zderev.seo.core.user.service.UserService;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final MessageRepo repo;
    private final UserService service;

    @Value("${spring.profile.active}")
    private String profile;

    @Autowired
    public MainController(MessageRepo messageRepo, UserService service) {
        this.repo = messageRepo;
        this.service = service;
    }

    @GetMapping
    public String main(Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        // Получите объект Authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Получите имя пользователя
        String username = auth.getName();

        // Используйте имя пользователя для получения полной информации о пользователе
        User user = service.findByEmail(username);

        if (profile != null) {
            // Передайте объект User вместо объекта Principal
            data.put("profile", user);
            data.put("messages", repo.findAll());
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}