package by.bsuir.andrei.diplom.controller;

import by.bsuir.andrei.diplom.dto.AnalysisTicketDto;
import by.bsuir.andrei.diplom.dto.ProcedureTicketDto;
import by.bsuir.andrei.diplom.model.Analysis;
import by.bsuir.andrei.diplom.model.Procedure;
import by.bsuir.andrei.diplom.model.User;
import by.bsuir.andrei.diplom.service.HospitalServicesService;
import by.bsuir.andrei.diplom.service.PersonalAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class HospitalServicesController {

    private final HospitalServicesService hs;
    private final PersonalAccountService personalAccountService;

    @GetMapping("/analysis/all")
    public ResponseEntity<List<Analysis>> getAnalyses() {
        List<Analysis> analyses = hs.getAllAnalyses();
        return new ResponseEntity<>(analyses, HttpStatus.OK);
    }

    @GetMapping("/procedure/all")
    public ResponseEntity<List<Procedure>> getProcedures() {
        List<Procedure> procedures = hs.getAllProcedures();
        return new ResponseEntity<>(procedures, HttpStatus.OK);
    }

    @GetMapping("/analysis/{id}")
    public ResponseEntity<List<AnalysisTicketDto>> getAllAnalysisTickets(@PathVariable("id") Long analysisId) {
        List<AnalysisTicketDto> tickets = hs.findAllAnalysisTickets(analysisId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/procedure/{id}")
    public ResponseEntity<List<ProcedureTicketDto>> getAllProcedureTickets(@PathVariable("id") Long procedureId) {
        List<ProcedureTicketDto> tickets = hs.findAllProcedureTickets(procedureId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PutMapping("/analysis/confirm/{ticketId}")
    public ResponseEntity<AnalysisTicketDto> confirmAnalysis(@PathVariable Long ticketId, Authentication auth) {
        User user = (User) auth.getPrincipal();
        AnalysisTicketDto confirmedTicketDto = hs.confirmAnalysis(ticketId, user);
        return new ResponseEntity<>(confirmedTicketDto, HttpStatus.OK);
    }

    @PutMapping("/procedure/confirm/{ticketId}")
    public ResponseEntity<ProcedureTicketDto> confirmProcedure(@PathVariable Long ticketId, Authentication auth) {
        User user = (User) auth.getPrincipal();
        ProcedureTicketDto confirmedTicketDto = hs.confirmProcedure(ticketId, user);
        return new ResponseEntity<>(confirmedTicketDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/analysis/free/{id}", method = RequestMethod.PUT)
    public ResponseEntity<List<AnalysisTicketDto>> cancelAnalysisTicket(@PathVariable("id") Long analysisTicketId, Authentication auth) {
        User user = (User) auth.getPrincipal();
        hs.cancelAnalysis(analysisTicketId);
        List<AnalysisTicketDto> tickets = personalAccountService.getActualAnalysisTickets(user.getId());
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @RequestMapping(value = "/procedure/free/{id}", method = RequestMethod.PUT)
    public ResponseEntity<List<ProcedureTicketDto>> cancelProcedureTicket(@PathVariable("id") Long procedureTicketId, Authentication auth) {
        User user = (User) auth.getPrincipal();
        hs.cancelProcedure(procedureTicketId);
        List<ProcedureTicketDto> tickets = personalAccountService.getActualProcedureTickets(user.getId());
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
