package co.edu.cedesistemas.ecommerce.service;

import co.edu.cedesistemas.ecommerce.model.User;
import co.edu.cedesistemas.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    public User getByEmail(String defaultEmail) {
        return this.userRepository.findByEmail(defaultEmail).orElse(null);
    }
}
