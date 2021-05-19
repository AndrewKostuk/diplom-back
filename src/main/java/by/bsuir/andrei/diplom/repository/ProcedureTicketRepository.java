package by.bsuir.andrei.diplom.repository;

import by.bsuir.andrei.diplom.model.ProcedureTicket;
import by.bsuir.andrei.diplom.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProcedureTicketRepository extends JpaRepository<ProcedureTicket, Long> {
    List<ProcedureTicket> findAllByUser_Id(Long userId);

    List<ProcedureTicket> findAllByProcedure_IdAndStatus(Long procedureId, Status status);

    @Transactional
    @Modifying
    @Query("UPDATE ProcedureTicket a " +
            "SET a.status = 'FREE', a.user = NULL WHERE a.id = ?1")
    int cancelTicket(Long id);
}
