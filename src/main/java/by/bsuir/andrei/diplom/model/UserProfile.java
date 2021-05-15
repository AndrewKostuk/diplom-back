package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile extends BaseEntity {
    private String phone;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    private String street;
    private Integer house;
    private Integer flat;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(mappedBy = "userProfile", fetch = FetchType.EAGER)
    private User user;

    @Override
    public String toString() {
        return "UserProfile{" +
                "phone='" + phone + '\'' +
                ", city=" + city.getName() +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", flat=" + flat +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", user=" + (user == null ? "null" : user.getId()) +
                '}';
    }
}
