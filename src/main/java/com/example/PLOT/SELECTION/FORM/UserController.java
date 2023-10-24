package com.example.PLOT.SELECTION.FORM;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    EmailService emailService;



    @PostMapping("/register/typeA/{typeAPlot}")
    public String registerUserTypeA(@RequestBody SelectionForm form, @PathVariable int typeAPlot ) throws IOException{
        if(typeAPlot > 166 || typeAPlot < 119){
            throw new IllegalStateException("Plot number is greater than 166");
        }
        List<User> users = userRepository.getAllUsers();
        for(User user: users){
            if(Integer.valueOf(user.getTypeAPlots()).equals(typeAPlot)){
                throw new IllegalStateException("Plot already taken");
                }
            }
        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPhone_no(form.getPhone_no());
        user.setTypeSelection(form.getTypeSelection());
        user.setTypeAPlots(typeAPlot);
        userRepository.save(user);
        sendEmail(user, typeAPlot);
        return "User Registered Successfully";
       
    }

    @PostMapping("/register/typeB/{typeBPlot}")
    public String registerUserTypeB(@RequestBody SelectionForm form, @PathVariable int typeBPlot ) throws IOException{
        if(typeBPlot > 118 || typeBPlot < 67){
            throw new IllegalStateException("Plot number is greater than 118");
        }
        List<User> users = userRepository.getAllUsers();
        for(User user: users){
            if(Integer.valueOf(user.getTypeBPlots()).equals(typeBPlot)){
                throw new IllegalStateException("Plot already taken");
                }
            }
        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPhone_no(form.getPhone_no());
        user.setTypeSelection(form.getTypeSelection());
        user.setTypeBPlots(typeBPlot);
        userRepository.save(user);
        sendEmail(user, typeBPlot);
        return "User Registered Successfully";
       
    }

    @PostMapping("/register/typeC/{typeCPlot}")
     public String registerTypeCUser(@RequestBody SelectionForm form, @PathVariable int typeCPlot) throws IOException{
        if(typeCPlot > 66 || typeCPlot < 1){
            throw new IllegalStateException("Plot number is greater than 66");
        }
        List<User> users = userRepository.getAllUsers();
        for(User user: users){
            if(Integer.valueOf(user.getTypeCPlots()).equals(typeCPlot)){
                throw new IllegalStateException("Plot already taken");
                }
            }
        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPhone_no(form.getPhone_no());
        user.setTypeSelection(form.getTypeSelection());
        user.setTypeCPlots(typeCPlot);
        userRepository.save(user);
        sendEmail(user, typeCPlot);
        return "User Registered Successfully";
       
    }
  
    @GetMapping("/takenPlots/c")
    public List<Integer> getAllTakenPlotsC(){
        List<User> users = userRepository.getAllUsers();
        List<Integer> takenPlots = new ArrayList<>();
        for(User user: users){
            if(user.getTypeCPlots() != 0)
            {
            takenPlots.add(user.getTypeCPlots());
        }

        }
        return takenPlots;
    }
     @GetMapping("/takenPlots/b")
    public List<Integer> getAllTakenPlotsB(){
        List<User> users = userRepository.getAllUsers();
        List<Integer> takenPlots = new ArrayList<>();
       
        for(User user: users){
            if(user.getTypeBPlots() != 0 )
            {
            takenPlots.add(user.getTypeBPlots());
        }

        }
        return takenPlots;
    }

     @GetMapping("/takenPlots/a")
    public List<Integer> getAllTakenPlotsA(){
        List<User> users = userRepository.getAllUsers();
        List<Integer> takenPlots = new ArrayList<>();
        for(User user: users){
            if(user.getTypeAPlots() != 0 )
            {
            takenPlots.add(user.getTypeAPlots());
        }

        }
        return takenPlots;
    }

    public void sendEmail(User user, int plot) throws IOException {
         Email email = new Email();
        email.setMailSubject("PLOT SELECTION");
        String message = Files.asCharSource(new File("src/main/resources/templates/index.html"), StandardCharsets.UTF_8).read();
        message = message.replace("[[name]]",user.getName());
        message = message.replace("[[number]]",user.getPhone_no());
        message = message.replace("[[email]]",user.getEmail());
        message = message.replace("[[type]]",user.getTypeSelection());
        message = message.replace("[[plot number]]",String.valueOf(plot));
        email.setMailContent(message);
        email.setMailTo("emmanuelajayi205@gmail.com");
        emailService.sendEmail(email);
        //email.setMailFrom("test");
    
    }
   
}
