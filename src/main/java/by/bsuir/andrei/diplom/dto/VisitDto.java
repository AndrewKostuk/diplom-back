package by.bsuir.andrei.diplom.dto;

import by.bsuir.andrei.diplom.model.BaseEntity;
import by.bsuir.andrei.diplom.model.Diagnosis;
import by.bsuir.andrei.diplom.model.Visit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitDto extends BaseEntity {
    private DoctorTicketDto doctorTicketDto;
    private Set<Diagnosis> diagnoses;
    private Set<AnalysisDto> analyses;
    private List<PharmacyDto> pharmacies;
    private List<ProcedureDto> procedures;

    public static VisitDto from(Visit visit) {
        VisitDto visitDto = new VisitDto();
        if (visit == null) return null;
        visitDto.setId(visit.getId());
        visitDto.setDoctorTicketDto(DoctorTicketDto.from(visit.getDoctorTicket()));
        visitDto.setDiagnoses(visit.getDiagnoses());
        visitDto.setAnalyses(AnalysisDto.from(visit.getAnalyses()));
        visitDto.setPharmacies(PharmacyDto.from(visit.getPharmacies()));
        visitDto.setProcedures(ProcedureDto.from(visit.getProcedures()));
        return visitDto;
    }

    public static List<VisitDto> from(List<Visit> visits) {
        return visits.stream().map(VisitDto::from).collect(Collectors.toList());
    }
}
