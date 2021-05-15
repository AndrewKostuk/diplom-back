package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City extends BaseEntity {
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "city")
    private List<UserProfile> userProfiles = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }
}
