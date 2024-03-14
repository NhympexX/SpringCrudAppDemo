package com.example.demo.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public boolean updateUser(User user,Integer id){
        Optional<User> _user = getUserById(id);
        if(_user.isPresent()){
            User foundUser = _user.get();
            foundUser.setName(user.getName());
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(user.getPassword());
            foundUser.setUsername(user.getUsername());
            createUser(foundUser);
            return true;
    }
        return false;
    }
    public Optional<User> getUserById(Integer id){
        return repository.findById(id);
    }
    public  boolean deleteUserById(Integer id){
        Optional<User> user  = getUserById(id);
        if(user.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;

    }
    public Optional<User> findByUidAndPsw(String username,String password){
        if(repository.findByUidAndPsw(username,password).isPresent()){
            return repository.findByUidAndPsw(username,password);
        }
        return null;
    }
}
