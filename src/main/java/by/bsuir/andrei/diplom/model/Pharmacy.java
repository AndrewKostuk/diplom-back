package by.bsuir.andrei.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy extends BaseEntity {
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;
    private Double price;
    private Boolean needRecipe;

    @Enumerated(EnumType.STRING)
    private Manufacturer manufacturer;
}
