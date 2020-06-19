package co.edu.cedesistemas.ecommerce.repository;

import co.edu.cedesistemas.ecommerce.common.TestUtil;
import co.edu.cedesistemas.ecommerce.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryIT {
    @Autowired
    private UserRepository userRepository;

    private static final String DEFAULT_EMAIL = "theemail@cedesistemas.edu.co";
    private static final String DEFAULT_NAME = "Marta";
    private static final String DEFAULT_LAST_NAME = "Hoyos";

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(userRepository, notNullValue());
    }

    @Test
    public void testCreateUser() {
        User user = TestUtil.buildUser(DEFAULT_EMAIL, DEFAULT_NAME, DEFAULT_LAST_NAME);
        user = userRepository.save(user);
        assertThat(user, notNullValue());
        assertThat(user.getEmail(), equalTo(DEFAULT_EMAIL));
    }

    @Test
    public void testFindByEmail() {
        testCreateUser();
        User found = userRepository.findByEmail(DEFAULT_EMAIL).orElse(null);
        assertThat(found, notNullValue());
        assertThat(found.getName(), equalTo(DEFAULT_NAME));
    }
}
