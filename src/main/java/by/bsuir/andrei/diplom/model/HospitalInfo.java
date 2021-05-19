package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalInfo extends BaseEntity {
    @ManyToOne
    private City city;
    private Integer house;
    private String phone;
    private String site;
    private String street;

    @ManyToMany
    @JoinTable(name = "hospital_working_time",
            joinColumns = {@JoinColumn(name = "hospital_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "working_time_id", referencedColumnName = "id")})
    private Set<WorkingTime> schedule = new HashSet<>();
}
