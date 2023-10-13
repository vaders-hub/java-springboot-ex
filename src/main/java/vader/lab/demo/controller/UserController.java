package vader.lab.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import vader.lab.demo.payload.SignupRequest;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        log.info("signUpRequest: " + signUpRequest);
        return new ResponseEntity<>("User registered Successfully!", HttpStatus.OK);
    }
}
