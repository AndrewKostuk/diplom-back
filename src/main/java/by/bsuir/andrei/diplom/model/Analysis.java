package by.bsuir.andrei.diplom.model;

import javax.persistence.Entity;

@Entity
public class Analysis extends Service {
    @Override
    public String toString() {
        return "Analysis{" +
                "name='" + super.getName() + '\'' +
                ", price=" + super.getPrice() +
                ", needReferral=" + super.getNeedReferral() +
                '}';
    }
}
