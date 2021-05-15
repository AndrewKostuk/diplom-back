package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Visit extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private DoctorTicket doctorTicket;

    @ManyToMany
    @JoinTable(name = "visit_diagnosis",
            joinColumns = {@JoinColumn(name = "visit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "diagnosis_id", referencedColumnName = "id")})
    private Set<Diagnosis> diagnoses = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "visit_analysis",
            joinColumns = {@JoinColumn(name = "visit_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "analysis_id", referencedColumnName = "id")})
    private Set<Analysis> analyses = new HashSet<>();


    @OneToMany(mappedBy = "visit")
    private List<VisitProcedure> procedures = new ArrayList<>();

    @OneToMany(mappedBy = "visit")
    private List<VisitPharmacy> pharmacies = new ArrayList<>();

    @Override
    public String toString() {
        return "Visit{" +
                "diagnoses=" + diagnoses.toString() +
                ", analyses=" + analyses.toString() +
                ", procedures=" + procedures.toString() +
                ", pharmacies=" + pharmacies.toString() +
                '}';
    }
}
