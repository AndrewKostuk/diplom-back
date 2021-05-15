package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis extends BaseEntity {
    private String name;

    /**
     * Возможно убрать позже
     */
//    @ManyToMany(mappedBy = "diagnoses")
//    private Set<Visit> visits = new HashSet<>();

    @Override
    public String toString() {
        return "Diagnosis{" +
                "name='" + name + '\'' +
                '}';
    }
}
