package ru.project.SEO.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seo")
public class MessageController {
    @GetMapping
    public String list() {
        return "seo";
    }
}
