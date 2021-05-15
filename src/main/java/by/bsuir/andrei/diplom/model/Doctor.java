package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctor extends NamedEntity {
    private Integer roomNumber;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;
    private Boolean houseCall;

    @OneToMany(mappedBy = "doctor")
    private List<DoctorTicket> tickets = new ArrayList<>();

    @Override
    public String toString() {
        return "Doctor{" +
                "roomNumber=" + roomNumber +
                ", post=" + post.toString() +
                ", specialization=" + specialization.toString() +
                ", shift=" + shift.toString() +
                ", houseCall=" + houseCall +
                '}';
    }
}
