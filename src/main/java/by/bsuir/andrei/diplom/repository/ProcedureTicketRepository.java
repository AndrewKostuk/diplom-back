package by.bsuir.andrei.diplom.repository;

import by.bsuir.andrei.diplom.model.ProcedureTicket;
import by.bsuir.andrei.diplom.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcedureTicketRepository extends JpaRepository<ProcedureTicket, Long> {
    List<ProcedureTicket> findAllByUser_Id(Long userId);

    List<ProcedureTicket> findAllByProcedure_IdAndStatus(Long procedureId, Status status);
}
