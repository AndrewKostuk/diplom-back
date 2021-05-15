package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DoctorTicket extends BaseTicket {

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne(mappedBy = "doctorTicket")
    private Visit visit;

    @Override
    public String toString() {
        return "DoctorTicket{" +
                "user=" + (super.getUser() == null ? "null" : super.getUser().getId()) +
                ", dateTime=" + super.getDateTime().toString() +
                ", status=" + super.getStatus().name() +
                ", roomNumber=" + super.getRoomNumber() +
                ", doctor=" + doctor.getId() +
                ", visit=" + (visit == null ? "null" : visit.getId()) +
                '}';
    }
}