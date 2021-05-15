package by.bsuir.andrei.diplom.dto;

import by.bsuir.andrei.diplom.model.BaseEntity;
import by.bsuir.andrei.diplom.model.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpecializationDto extends BaseEntity {
    private String name;

    public static SpecializationDto from(Specialization specialization) {
        SpecializationDto specializationDto = new SpecializationDto();
        specializationDto.setId(specialization.getId());
        specializationDto.setName(specialization.getName());
        return specializationDto;
    }

    public static List<SpecializationDto> from(List<Specialization> specialization) {
        return specialization.stream().map(SpecializationDto::from).collect(Collectors.toList());
    }


    public static Specialization to(SpecializationDto specializationDto) {
        Specialization specialization = new Specialization();
        specialization.setId(specializationDto.getId());
        specialization.setName(specializationDto.getName());
        return specialization;
    }
}




