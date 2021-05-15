package by.bsuir.andrei.diplom.repository;

import by.bsuir.andrei.diplom.model.DoctorTicket;
import by.bsuir.andrei.diplom.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorTicketRepository extends JpaRepository<DoctorTicket, Long> {

    List<DoctorTicket> findAllByDoctor_Specialization_IdAndStatusAndRoomNumberNot(Long specId, Status status, Integer excludedRoomNumber);

    List<DoctorTicket> findAllByUser_Id(Long userId);

    List<DoctorTicket> findAllByDoctor_Specialization_Id_AndDoctor_HouseCallAndStatus(Long specId, Boolean houseCall, Status status);



//    @Transactional
//    @Modifying
//    @Query("UPDATE DoctorTicket d " +
//            "SET d.user = ?2, d.status = 'RESERVED' WHERE d.id = ?1")
//    DoctorTicket updateUserIdAndStatus(Long ticketId, Long userId);
}
