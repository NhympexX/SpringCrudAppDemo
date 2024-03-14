package com.example.demo.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService service;

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

        if(service.updateUser(user,id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id){
        if(service.deleteUserById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<User> Login(@RequestBody LoginDto loginDto){
        if(service.findByUidAndPsw(loginDto.getUsername(),loginDto.getPassword()).isPresent()){
            return new ResponseEntity<>(service.findByUidAndPsw(loginDto.getUsername(),loginDto.getPassword()).get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
