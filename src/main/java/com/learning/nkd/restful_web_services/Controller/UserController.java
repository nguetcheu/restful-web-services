package com.learning.nkd.restful_web_services.Controller;

import com.learning.nkd.restful_web_services.Entity.User;
import com.learning.nkd.restful_web_services.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}
