package com.example.PLOT.SELECTION.FORM;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @PostMapping("/register")
    public String registerUser(@RequestBody SelectionForm form){

        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPhone_no(form.getPhone_no());
        user.setTypeSelection(form.getTypeSelection());
        userRepository.save(user);
        return "User Registered Successfully";
       
    }

    @PostMapping("/register/typeC/{typeCPlots}")
     public String registerTypeCUser(@RequestBody SelectionForm form, @RequestParam int typeCPlots){
        if(typeCPlots > 66 || typeCPlots < 1){
            throw new IllegalStateException("Plot number is greater than 66");
        }
        List<User> users = userRepository.getAllUsers();
        for(User user: users){
            if(Integer.valueOf(user.getTypeCPlots()).equals(typeCPlots)){
                throw new IllegalStateException("Plot already taken");
                }
            }
        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPhone_no(form.getPhone_no());
        user.setTypeSelection(form.getTypeSelection());
        user.setTypeCPlots(typeCPlots);
        userRepository.save(user);
        return "User Registered Successfully";
       
    }
    @GetMapping("/takenPlots")
    public List<Integer> getAllTakenPlots(){
        List<User> users = userRepository.getAllUsers();
        List<Integer> takenPlots = new ArrayList<>();
        for(User user: users){
            if(user.getTypeCPlots() != 0 || Objects.nonNull(user.getTypeCPlots()))
            {
            takenPlots.add(user.getTypeCPlots());
        }

        }
        return takenPlots;
    }
    
}
