package by.bsuir.andrei.diplom.repository;

import by.bsuir.andrei.diplom.model.AnalysisTicket;
import by.bsuir.andrei.diplom.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnalysisTicketRepository extends JpaRepository<AnalysisTicket, Long> {
    List<AnalysisTicket> findAllByUser_Id(Long userId);

    List<AnalysisTicket> findAllByAnalysis_IdAndStatus(Long analysisId, Status status);

    @Transactional
    @Modifying
    @Query("UPDATE AnalysisTicket a " +
            "SET a.status = 'FREE', a.user = NULL WHERE a.id = ?1")
    int cancelTicket(Long id);
}
