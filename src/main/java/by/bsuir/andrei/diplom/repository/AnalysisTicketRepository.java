package by.bsuir.andrei.diplom.repository;

import by.bsuir.andrei.diplom.model.AnalysisTicket;
import by.bsuir.andrei.diplom.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalysisTicketRepository extends JpaRepository<AnalysisTicket, Long> {
    List<AnalysisTicket> findAllByUser_Id(Long userId);

    List<AnalysisTicket> findAllByAnalysis_IdAndStatus(Long analysisId, Status status);
}
