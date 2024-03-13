package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers(){
        return service.getUsers();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        service.createUser(user);
    }
    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") Integer id, @RequestBody User user){
        Optional<User> _user = service.getUserById(id);
        if(_user.isPresent()){
            User foundUser = _user.get();
            foundUser.name = user.name;
            foundUser.email = user.email;
            foundUser.password = user.password;
            foundUser.username = user.username;
            service.createUser(foundUser);
           return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id){
        Optional<User> _user = service.getUserById(id);
        if(_user.isPresent()){
            service.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
