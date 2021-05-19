package by.bsuir.andrei.diplom.repository;

import by.bsuir.andrei.diplom.model.DoctorTicket;
import by.bsuir.andrei.diplom.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DoctorTicketRepository extends JpaRepository<DoctorTicket, Long> {

    List<DoctorTicket> findAllByDoctor_Specialization_IdAndStatusAndRoomNumberNot(Long specId, Status status, Integer excludedRoomNumber);

    List<DoctorTicket> findAllByUser_Id(Long userId);

    List<DoctorTicket> findAllByDoctor_Specialization_Id_AndDoctor_HouseCallAndStatus(Long specId, Boolean houseCall, Status status);


    @Transactional
    @Modifying
    @Query("UPDATE DoctorTicket d " +
            "SET d.status = 'FREE', d.user = NULL WHERE d.id = ?1")
    int cancelTicket(Long doctorTicketId);
}
