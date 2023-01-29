package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.IdNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class AdminController {

    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
         return ResponseEntity.ok().body(userService.getUsers());
   }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid User user) {
        userService.addUser(user);
        return ResponseEntity.ok().build();

    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid User user){
        userService.changeUser(user);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) throws IdNotFoundException {
          return ResponseEntity.ok()
                .body(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }



}
