package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VisitPharmacy implements Serializable {

    @EmbeddedId
    private VisitPharmacyPK id;

    @ManyToOne
    @MapsId("visit_id")
    private Visit visit;

    @ManyToOne
    @MapsId("pharmacy_id")
    private Pharmacy pharmacy;

    private Integer dose;
    private Integer amountPerDay;
    @Enumerated(EnumType.STRING)
    private Food food;
    private Integer courseDuration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        VisitPharmacy that = (VisitPharmacy) o;
        return Objects.equals(visit.getId(), that.getVisit().getId()) &&
                Objects.equals(pharmacy.getId(), that.getPharmacy().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(visit, pharmacy);
    }

    @Override
    public String toString() {
        return "{" +
                "pharmacy=" + pharmacy.getName() +
                ", amountPerDay=" + amountPerDay +
                ", food=" + food.name() +
                '}';
    }
}
