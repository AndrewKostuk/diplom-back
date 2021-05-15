package by.bsuir.andrei.diplom.dto;

import by.bsuir.andrei.diplom.model.BaseEntity;
import by.bsuir.andrei.diplom.model.VisitPharmacy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PharmacyDto extends BaseEntity {
    private String name;
    private Integer amountPerDay;
    private String food;

    public static PharmacyDto from(VisitPharmacy pharmacy) {
        PharmacyDto pharmacyDto = new PharmacyDto();
        pharmacyDto.setId(pharmacy.getPharmacy().getId());
        pharmacyDto.setName(pharmacy.getPharmacy().getName());
        pharmacyDto.setAmountPerDay(pharmacy.getAmountPerDay());
        pharmacyDto.setFood(pharmacy.getFood().name());
        return pharmacyDto;
    }

    public static List<PharmacyDto> from(List<VisitPharmacy> pharmacy) {
        return pharmacy.stream().map(PharmacyDto::from).collect(Collectors.toList());
    }
}
