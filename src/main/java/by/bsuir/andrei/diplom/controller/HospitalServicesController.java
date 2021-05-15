package by.bsuir.andrei.diplom.controller;


import by.bsuir.andrei.diplom.dto.AnalysisTicketDto;
import by.bsuir.andrei.diplom.dto.ProcedureTicketDto;
import by.bsuir.andrei.diplom.model.Analysis;
import by.bsuir.andrei.diplom.model.Procedure;
import by.bsuir.andrei.diplom.model.User;
import by.bsuir.andrei.diplom.service.HospitalServicesService;
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

    private final HospitalServicesService hospitalServicesService;

    @GetMapping("/analysis/all")
    public ResponseEntity<List<Analysis>> getAnalyses() {
        List<Analysis> analyses = hospitalServicesService.getAllAnalyses();
        return new ResponseEntity<>(analyses, HttpStatus.OK);
    }

    @GetMapping("/procedure/all")
    public ResponseEntity<List<Procedure>> getProcedures() {
        List<Procedure> procedures = hospitalServicesService.getAllProcedures();
        return new ResponseEntity<>(procedures, HttpStatus.OK);
    }

    @GetMapping("/analysis/{id}")
    public ResponseEntity<List<AnalysisTicketDto>> getAllAnalysisTickets(@PathVariable("id") Long analysisId) {
        List<AnalysisTicketDto> tickets = hospitalServicesService.findAllAnalysisTickets(analysisId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/procedure/{id}")
    public ResponseEntity<List<ProcedureTicketDto>> getAllProcedureTickets(@PathVariable("id") Long procedureId) {
        List<ProcedureTicketDto> tickets = hospitalServicesService.findAllProcedureTickets(procedureId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PutMapping("/analysis/confirm/{ticketId}")
    public ResponseEntity<AnalysisTicketDto> confirmAnalysis(@PathVariable Long ticketId, Authentication auth) {
        User user = (User) auth.getPrincipal();
        AnalysisTicketDto confirmedTicketDto = hospitalServicesService.confirmAnalysis(ticketId, user);
        return new ResponseEntity<>(confirmedTicketDto, HttpStatus.OK);
    }

    @PutMapping("/procedure/confirm/{ticketId}")
    public ResponseEntity<ProcedureTicketDto> confirmProcedure(@PathVariable Long ticketId, Authentication auth) {
        User user = (User) auth.getPrincipal();
        ProcedureTicketDto confirmedTicketDto = hospitalServicesService.confirmProcedure(ticketId, user);
        return new ResponseEntity<>(confirmedTicketDto, HttpStatus.OK);
    }
}
