package by.bsuir.andrei.diplom.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Service extends BaseEntity {
    private String name;
    private Double price;
    private Boolean needReferral;
}
