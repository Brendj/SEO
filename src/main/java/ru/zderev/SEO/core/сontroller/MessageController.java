package ru.zderev.SEO.core.сontroller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zderev.SEO.core.domain.Message;
import ru.zderev.SEO.core.repo.MessageRepo;

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

    private int usersSize(List<Map<String,String>> users) {
        return users.size()+1;
    }

    @GetMapping
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
