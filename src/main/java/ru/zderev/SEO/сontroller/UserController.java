package ru.zderev.SEO.сontroller;

import org.springframework.web.bind.annotation.*;
import ru.zderev.SEO.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("seo")
public class UserController {
    private int usersSize(List<Map<String,String>> users) {
        return users.size()+1;
    }
    private List<Map<String,String>> users = new ArrayList<>() {{
        add(new HashMap<>() {{
            put("id", "1");
            put("text", "first");
        }});
        add(new HashMap<>() {{
            put("id", "2");
            put("text", "second");
        }});
        add(new HashMap<>() {{
            put("id", "3");
            put("text", "third");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> String() {
        return users;
    }

    @GetMapping("{id}")
    public Map<String,String> getUser(@PathVariable String id) {
        return getId(id);
    }

    private Map<String, String> getId(String id) {
        return users.stream()
                .filter(users -> users.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String,String> createUser(@RequestBody Map<String,String> user) {
        user.put("id", String.valueOf(usersSize(users)));
        users.add(user);
        return user;
    }

    @PutMapping("{id}")
    public Map<String,String> updateUser(@PathVariable String id, @RequestBody Map<String,String> user) {
        Map<String,String> userFromList = getId(id);
        userFromList.putAll(user);
        userFromList.put("id", id);
        return userFromList;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id) {
        Map<String,String> user = getId(id);
        users.remove(user);
    }
}
