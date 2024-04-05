package com.apzda.cloud.uc.security.authentication;

import com.apzda.cloud.gsvc.security.token.JwtAuthenticationToken;
import com.apzda.cloud.uc.test.TestApp;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author fengz (windywany@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 **/
@SpringBootTest
@ContextConfiguration(classes = TestApp.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles({ "test" })
class DefaultAuthenticationProviderTest {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    
    @Test
    void authenticate_must_be_ok() {
        // given
        val token = JwtAuthenticationToken.unauthenticated("admin", "123456");

        // when
        val authed = authenticationProvider.authenticate(token);

        // then
        assertThat(authed).isNotNull();
    }

    @Test
    void authenticate_must_be_failure() {
        // given
        val token = JwtAuthenticationToken.unauthenticated("admin", "123457");

        // when
        assertThatThrownBy(() -> {
            authenticationProvider.authenticate(token);
        }).hasMessage("Username or Password is incorrect");

    }

}