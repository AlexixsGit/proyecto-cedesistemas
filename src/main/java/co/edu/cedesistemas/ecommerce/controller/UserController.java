package co.edu.cedesistemas.ecommerce.controller;

import co.edu.cedesistemas.ecommerce.model.Store;
import co.edu.cedesistemas.ecommerce.model.User;
import co.edu.cedesistemas.ecommerce.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody String userString) throws JsonProcessingException {
        User user = objectMapper.readValue(userString, User.class);
        return new ResponseEntity<>(this.userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/users/by-email")
    public ResponseEntity<User> userByEmail(@Param("email") String email) throws JsonProcessingException {
        return new ResponseEntity<>(this.userService.getByEmail(email), HttpStatus.OK);
    }
}
