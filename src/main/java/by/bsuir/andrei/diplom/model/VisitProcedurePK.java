package by.bsuir.andrei.diplom.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class VisitProcedurePK implements Serializable {

    @Column(name = "visit_id")
    private Long visitId;

    @Column(name = "procedure_id")
    private Long procedureId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        VisitProcedurePK that = (VisitProcedurePK) o;
        return Objects.equals(visitId, that.visitId) &&
                Objects.equals(procedureId, that.procedureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitId, procedureId);
    }

    @Override
    public String toString() {
        return "VisitProcedurePK{" +
                "visitId=" + visitId +
                ", procedureId=" + procedureId +
                '}';
    }
}
