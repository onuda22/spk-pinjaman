package mini_project.spk.controller;

import lombok.RequiredArgsConstructor;
import mini_project.spk.model.User;
import mini_project.spk.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public User create(@RequestBody User request){
        return userService.create(request);
    }

    @GetMapping("/users")
    public List<User> getAll(){
        return userService.getAll();
    }
}
