package com.laioffer.twitch.user;


import com.laioffer.twitch.model.RegisterBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.OK)
    public void register(@RequestBody RegisterBody body) {
        userService.register(body.username(), body.password(), body.firstName(), body.lastName());
    }

    @GetMapping("/checklogin")
    public ResponseEntity<?> checkLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // User is authenticated
            return ResponseEntity.ok().build();
        } else {
            // User is not authenticated
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

