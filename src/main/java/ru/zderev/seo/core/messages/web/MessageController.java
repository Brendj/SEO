package ru.zderev.seo.core.messages.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.zderev.seo.core.messages.Message;
import ru.zderev.seo.core.view.Views;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("seo")
public class MessageController {
    private final MessageRepo messageRepo;
    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }
    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> String() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getMessage(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message createUser(@RequestBody Message message) {
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message updateUser(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Message message) {
        messageRepo.delete(message);
    }
}
