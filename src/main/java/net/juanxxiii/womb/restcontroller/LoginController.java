package net.juanxxiii.womb.restcontroller;

import lombok.extern.java.Log;
import net.juanxxiii.womb.database.entities.UserLoginSystem;
import net.juanxxiii.womb.database.repositories.UserLoginRepository;
import net.juanxxiii.womb.exceptions.PasswordMalFormedException;
import net.juanxxiii.womb.security.Encrypter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/womb/system/users")
@CrossOrigin
@Log
public class LoginController {

    private UserLoginRepository userLoginRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginController(UserLoginRepository userLoginRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userLoginRepository = userLoginRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("/signup")
    public void signUp(@RequestBody UserLoginSystem userLoginSystem) {
        userLoginSystem.setPassword(bCryptPasswordEncoder.encode(userLoginSystem.getPassword()));
        userLoginRepository.save(userLoginSystem);
    }


}
