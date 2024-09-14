package com.example.rest.Controller;

import com.example.rest.Models.User;
import com.example.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
   private UserRepo userRepo;

    @GetMapping("/")
    public String getPage(){
        return "Hello World";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody  User user){
        userRepo.save(user);
        return "User successfully Saved";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setOccupation(user.getOccupation());
        updatedUser.setAge(user.getAge());
        userRepo.save(updatedUser);
        return "user successfully updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deletedUser = userRepo.findById(id).get();
        userRepo.deleteById(id);
        return "user deleted with the id: " + id;
    }

}
