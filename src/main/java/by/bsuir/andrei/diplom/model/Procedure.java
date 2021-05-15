package by.bsuir.andrei.diplom.model;

import javax.persistence.Entity;

@Entity
public class Procedure extends Service {
    @Override
    public String toString() {
        return "Procedure{" +
                "name='" + super.getName() + '\'' +
                ", price=" + super.getPrice() +
                ", needReferral=" + super.getNeedReferral() +
                '}';
    }
}
