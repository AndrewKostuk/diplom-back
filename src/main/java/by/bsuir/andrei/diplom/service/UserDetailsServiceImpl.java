package by.bsuir.andrei.diplom.service;

import by.bsuir.andrei.diplom.model.ConfirmationToken;
import by.bsuir.andrei.diplom.model.User;
import by.bsuir.andrei.diplom.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    public String signUp(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            if (user.getFirstName().equals(existingUser.getFirstName())
                    && user.getLastName().equals(existingUser.getLastName())
                    && user.getPatronymic().equals(existingUser.getPatronymic())) {
                if (!bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                    throw new IllegalStateException("wrong password");
                }
                if (existingUser.getEnabled()) {
                    throw new IllegalStateException("you are already registered and confirmed. Just sign in");
                }
                user.setId(existingUser.getId());
            } else {
                throw new IllegalStateException("email is already taken");
            }
        } else {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
        String token = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                createdAt,
                createdAt.plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

}
