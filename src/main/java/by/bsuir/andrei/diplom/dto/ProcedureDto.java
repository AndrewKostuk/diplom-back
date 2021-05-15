package by.bsuir.andrei.diplom.dto;

import by.bsuir.andrei.diplom.model.BaseEntity;
import by.bsuir.andrei.diplom.model.VisitProcedure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProcedureDto extends BaseEntity {
    private String name;
    private Integer amount;

    public static ProcedureDto from(VisitProcedure procedure) {
        ProcedureDto procedureDto = new ProcedureDto();
        procedureDto.setId(procedure.getProcedure().getId());
        procedureDto.setName(procedure.getProcedure().getName());
        procedureDto.setAmount(procedure.getProcedureAmount());
        return procedureDto;
    }

    public static List<ProcedureDto> from(List<VisitProcedure> procedure) {
        return procedure.stream().map(ProcedureDto::from).collect(Collectors.toList());
    }
}
