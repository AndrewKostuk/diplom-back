package by.bsuir.andrei.diplom.repository;

import by.bsuir.andrei.diplom.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    List<Specialization> findAllByDoctors_HouseCall(Boolean houseCall);
}
