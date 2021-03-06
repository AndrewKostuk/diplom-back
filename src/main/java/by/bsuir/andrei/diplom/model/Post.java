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
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {
    @Column(name = "name", unique = true)
    private String name;
    @OneToMany(mappedBy = "post")
    private List<Doctor> doctors = new ArrayList<>();

    @Override
    public String toString() {
        return "Post{" +
                "name='" + name + '\'' +
                '}';
    }
}

