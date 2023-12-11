package com.nmdsolucoesdigitais.todolist.user;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCryptParser;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserRepository repository;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserModel userModel){
       var user = repository.findByUsername(userModel.getName());

       if(user !=null){
           System.out.println("Usuario já existe na baste");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario Já Existe");
       }
       
       var passwordHashred = BCrypt.withDefaults().hashToString(12,userModel.getPassword()
       .toCharArray());
       userModel.setPassword(passwordHashred);



        var userCreated = repository.save(userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }


    @GetMapping("/find")
    public List<UserModel> getAll(){
        var users = repository.findAll();

        return users;
    }
    

}
