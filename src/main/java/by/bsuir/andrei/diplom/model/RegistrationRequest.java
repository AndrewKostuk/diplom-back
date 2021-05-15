package by.bsuir.andrei.diplom.model;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String email;
    private final String password;
}
