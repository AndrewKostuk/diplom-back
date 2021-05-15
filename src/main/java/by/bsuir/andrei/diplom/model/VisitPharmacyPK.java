package by.bsuir.andrei.diplom.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VisitPharmacyPK implements Serializable {

    @Column(name = "visit_id")
    private Long visitId;

    @Column(name = "pharmacy_id")
    private Long pharmacyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        VisitPharmacyPK that = (VisitPharmacyPK) o;
        return Objects.equals(visitId, that.visitId) &&
                Objects.equals(pharmacyId, that.pharmacyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitId, pharmacyId);
    }

    @Override
    public String toString() {
        return "VisitPharmacyPK{" +
                "visitId=" + visitId +
                ", pharmacyId=" + pharmacyId +
                '}';
    }
}
