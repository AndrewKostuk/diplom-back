package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VisitProcedure implements Serializable {
    @EmbeddedId
    private VisitProcedurePK id;

    @ManyToOne
    @MapsId("visit_id")
    private Visit visit;

    @ManyToOne
    @MapsId("procedure_id")
    private Procedure procedure;

    private Integer procedureAmount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        VisitProcedure that = (VisitProcedure) o;
        return Objects.equals(visit.getId(), that.getVisit().getId()) &&
                Objects.equals(procedure.getId(), that.getProcedure().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(visit, procedure);
    }

    @Override
    public String toString() {
        return "{" +
                "procedure=" + procedure.getName() +
                ", procedureAmount=" + procedureAmount +
                '}';
    }
}
