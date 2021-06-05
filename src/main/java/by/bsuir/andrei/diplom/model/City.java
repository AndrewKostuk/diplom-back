package by.bsuir.andrei.diplom.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter

@NoArgsConstructor
public class City extends BaseEntity {
    @Column(unique = true)
    private String name;
//    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
//    private List<UserProfile> userProfiles = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }
}
