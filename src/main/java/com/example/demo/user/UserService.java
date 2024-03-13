package com.example.demo.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers(){
        return repository.findAll() ;
    }
    public void createUser(User user){

        repository.save(user);
    }
    public Optional<User> getUserById(Integer id){
        return repository.findById(id);
    }
    public  void deleteUserById(Integer id){
        repository.deleteById(id);
    }
}
