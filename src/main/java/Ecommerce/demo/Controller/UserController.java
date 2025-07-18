package Ecommerce.demo.Controller;

import Ecommerce.demo.model.User;
import Ecommerce.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public User getUserInfo(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        User user = userService.getUserInfo(jwt);
        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
    }

    @PostMapping("/register")
    public  User register(@RequestBody User user) {
        return userService.register(user);
    }


}
