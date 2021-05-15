package by.bsuir.andrei.diplom.dto;

import by.bsuir.andrei.diplom.model.Analysis;
import by.bsuir.andrei.diplom.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class AnalysisDto extends BaseEntity {
    private String name;

    public static AnalysisDto from(Analysis analysis) {
        AnalysisDto analysisDto = new AnalysisDto();
        analysisDto.setId(analysis.getId());
        analysisDto.setName(analysis.getName());
        return analysisDto;
    }

    public static Set<AnalysisDto> from(Set<Analysis> analyses) {
        return analyses.stream().map(AnalysisDto::from).collect(Collectors.toSet());
    }
}
