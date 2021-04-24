package net.juanxxiii.womb.restcontroller;

import lombok.extern.java.Log;
import net.juanxxiii.womb.common.utils.Copy;
import net.juanxxiii.womb.database.entities.UserLoginSystem;
import net.juanxxiii.womb.database.entities.Users;
import net.juanxxiii.womb.database.repositories.UserLoginRepository;
import net.juanxxiii.womb.exceptions.PasswordMalFormedException;
import net.juanxxiii.womb.exceptions.ResourceNotFoundException;
import net.juanxxiii.womb.security.Encrypter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

    @PatchMapping("/systemuser/{username}")
    public ResponseEntity<?> partialUpdateSystemUser(@PathVariable("username") String username, @RequestBody UserLoginSystem userLoginSystem) {
        UserLoginSystem user = userLoginRepository.findByUsername(username);
        int id = user.getId();
        if (user != null) {
            Copy.copyNonNullProperties(userLoginSystem,user);
            user.setId(id);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userLoginRepository.updateUserSystem(user.getUsername(), user.getPassword(), user.getId());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
