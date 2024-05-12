package ru.zderev.seo.core.messages.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zderev.seo.core.messages.Message;
import ru.zderev.seo.core.util.WsSender;
import ru.zderev.seo.core.dto.EventType;
import ru.zderev.seo.core.dto.ObjectType;
import ru.zderev.seo.core.view.Views;

import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("seo")
public class MessageController {
    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> wsSender;
    @Autowired
    public MessageController(MessageRepo messageRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }
    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> String() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getMessage(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message createUser(@RequestBody Message message) {
        Message updateMessage = messageRepo.save(message);
        System.out.println("createUser: eventType = CREATE, message = " + updateMessage);
        wsSender.accept(EventType.CREATE, updateMessage);
        return updateMessage;
    }

    @PutMapping("{id}")
    public Message updateUser(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        Message updateMessage = messageRepo.save(messageFromDb);
        System.out.println("updateUser: eventType = UPDATE, message = " + updateMessage);
        wsSender.accept(EventType.UPDATE, updateMessage);
        return updateMessage;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Message message) {
        messageRepo.delete(message);
        System.out.println("deleteUser: eventType = REMOVE, message = " + message);
        wsSender.accept(EventType.REMOVE, message);
    }
}
