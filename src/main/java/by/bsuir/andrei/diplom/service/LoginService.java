package by.bsuir.andrei.diplom.service;

import by.bsuir.andrei.diplom.dto.LoginDto;
import by.bsuir.andrei.diplom.model.User;
import by.bsuir.andrei.diplom.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean findRegisteredUser(LoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getUsername());
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            return existingUser.getEnabled() && bCryptPasswordEncoder.matches(loginDto.getPassword(), existingUser.getPassword());
        }
        return false;
    }
}
