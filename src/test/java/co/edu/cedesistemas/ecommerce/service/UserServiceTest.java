package co.edu.cedesistemas.ecommerce.service;

import co.edu.cedesistemas.ecommerce.common.TestUtil;
import co.edu.cedesistemas.ecommerce.model.User;
import co.edu.cedesistemas.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private static final String DEFAULT_EMAIL = "user@cedesistemas.edu.co";

    @Test
    public void testCreateProduct() {
        User user = TestUtil.buildUser(DEFAULT_EMAIL, "Juan", "Perex");
        when(userRepository.save(user)).thenReturn(user);

        User created = userService.createUser(user);

        assertThat(created, notNullValue());
        assertThat(created.getName(), equalTo("Juan"));
    }

    @Test
    public void testGetByEmail() {
        User user = TestUtil.buildUser(DEFAULT_EMAIL, "Juan", "Perex");
        when(userRepository.save(user)).thenReturn(user);

        User created = userService.createUser(user);
        assertThat(created, notNullValue());
        assertThat(created.getName(), equalTo("Juan"));

        when(userRepository.findByEmail(DEFAULT_EMAIL)).thenReturn(Optional.of(user));

        User found = userService.getByEmail(DEFAULT_EMAIL);

        assertThat(found, notNullValue());
        assertThat(found.getName(), equalTo("Juan"));
    }
}
