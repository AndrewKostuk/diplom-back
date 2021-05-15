package by.bsuir.andrei.diplom.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseTicket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer roomNumber;
}
