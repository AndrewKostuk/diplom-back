package by.bsuir.andrei.diplom.service;

import by.bsuir.andrei.diplom.model.ConfirmationToken;
import by.bsuir.andrei.diplom.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository ctr;


    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        ctr.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return ctr.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return ctr.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
