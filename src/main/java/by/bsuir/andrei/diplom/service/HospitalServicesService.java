package by.bsuir.andrei.diplom.service;

import by.bsuir.andrei.diplom.dto.AnalysisTicketDto;
import by.bsuir.andrei.diplom.dto.ProcedureTicketDto;
import by.bsuir.andrei.diplom.model.*;
import by.bsuir.andrei.diplom.repository.AnalysisRepository;
import by.bsuir.andrei.diplom.repository.AnalysisTicketRepository;
import by.bsuir.andrei.diplom.repository.ProcedureRepository;
import by.bsuir.andrei.diplom.repository.ProcedureTicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class HospitalServicesService {

    private AnalysisRepository analysisRepository;
    private ProcedureRepository procedureRepository;
    private AnalysisTicketRepository analysisTicketRepository;
    private ProcedureTicketRepository procedureTicketRepository;

    public List<Analysis> getAllAnalyses() {
        return analysisRepository.findAll();
    }

    public List<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }

    public List<AnalysisTicketDto> findAllAnalysisTickets(Long analysisId) {
        List<AnalysisTicket> analysisTickets = analysisTicketRepository.findAllByAnalysis_IdAndStatus(analysisId, Status.FREE);
        updateAnalysisTickets(analysisTickets);
        return AnalysisTicketDto.from(analysisTickets);
    }

    private void updateAnalysisTickets(List<AnalysisTicket> tickets) {
        if (tickets != null && !tickets.isEmpty()) {
            for (BaseTicket ticket : tickets) {
                if (ticket.getDateTime().isBefore(LocalDateTime.now())) ticket.setStatus(Status.FINISHED);
            }
            analysisTicketRepository.saveAll(tickets);
        }
    }

    public List<ProcedureTicketDto> findAllProcedureTickets(Long procedureId) {
        List<ProcedureTicket> procedureTicket = procedureTicketRepository.findAllByProcedure_IdAndStatus(procedureId, Status.FREE);
        updateProcedureTickets(procedureTicket);
        return ProcedureTicketDto.from(procedureTicket);
    }

    private void updateProcedureTickets(List<ProcedureTicket> tickets) {
        if (tickets != null && !tickets.isEmpty()) {
            for (BaseTicket ticket : tickets) {
                if (ticket.getDateTime().isBefore(LocalDateTime.now())) ticket.setStatus(Status.FINISHED);
            }
            procedureTicketRepository.saveAll(tickets);
        }
    }

    public AnalysisTicketDto confirmAnalysis(Long ticketId, User user) {
        AnalysisTicket ticket = analysisTicketRepository.findById(ticketId).orElseThrow(() ->
                new IllegalStateException("ticket not found by id " + ticketId));
        ticket.setUser(user);
        ticket.setStatus(Status.RESERVED);
        return AnalysisTicketDto.from(analysisTicketRepository.save(ticket));
    }

    public ProcedureTicketDto confirmProcedure(Long ticketId, User user) {
        ProcedureTicket ticket = procedureTicketRepository.findById(ticketId).orElseThrow(() ->
                new IllegalStateException("ticket not found by id " + ticketId));
        ticket.setUser(user);
        ticket.setStatus(Status.RESERVED);
        return ProcedureTicketDto.from(procedureTicketRepository.save(ticket));
    }

    public void cancelAnalysis(Long analysisTicketId) {
        analysisTicketRepository.cancelTicket(analysisTicketId);
    }

    public void cancelProcedure(Long procedureTicketId) {
        procedureTicketRepository.cancelTicket(procedureTicketId);
    }
}
