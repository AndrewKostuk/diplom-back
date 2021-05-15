package by.bsuir.andrei.diplom.model;

import lombok.*;

import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class NamedEntity extends BaseEntity {

    private String firstName;
    private String lastName;
    private String patronymic;
}
