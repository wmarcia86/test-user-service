package com.nissum.test.userservice.security;

import com.nissum.test.userservice.repository.UserRepository;
import com.nissum.test.userservice.util.constants.MessageEnum;
import com.nissum.test.userservice.util.exception.ValidationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * JwtUserDetailsService Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    private String token;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.nissum.test.userservice.model.entity.User user =
                userRepository.findByEmail(userName).orElseThrow(() ->
                    new UsernameNotFoundException(MessageEnum.USERNAME_NOT_FOUND.getMessage()));

        if (!user.isActive()) {
            throw new UsernameNotFoundException(MessageEnum.USER_DESACTIVATED.getMessage());
        }

        token = user.getToken();

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .credentialsExpired(false)
                .accountLocked(false)
                .accountExpired(false)
                .disabled(false)
                .build();
    }

    public String getToken() {
        return token;
    }

}
