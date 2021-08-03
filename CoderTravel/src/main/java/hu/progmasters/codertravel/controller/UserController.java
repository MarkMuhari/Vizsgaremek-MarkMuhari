package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.dto.UserCreateCommand;
import hu.progmasters.codertravel.dto.UserInfo;
import hu.progmasters.codertravel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfo findUserById(@PathVariable("id") Integer id) {
        return userService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserInfo createUser(UserCreateCommand createCommand) {
        return userService.saveUser(createCommand);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserInfo updateUser(@RequestBody UserCreateCommand updateCommand) {
        return userService.updateUser(updateCommand);
    }
}
