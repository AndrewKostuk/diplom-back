package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shift extends BaseEntity {
    private LocalTime startTime;
    private LocalTime endTime;
    @OneToMany(mappedBy = "shift")
    private List<Doctor> doctors = new ArrayList<>();

    public Shift(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
