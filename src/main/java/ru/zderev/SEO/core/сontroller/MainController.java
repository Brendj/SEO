package ru.zderev.SEO.core.сontroller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zderev.SEO.core.domain.User;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> attributeValue = new HashMap<>();
        attributeValue.put("profile", user);
        attributeValue.put("messages", null);
        model.addAttribute("forntendData", attributeValue);
        return "index";
    }
}
