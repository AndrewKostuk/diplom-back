package by.bsuir.andrei.diplom.controller;

import by.bsuir.andrei.diplom.dto.DoctorTicketDto;
import by.bsuir.andrei.diplom.dto.SpecializationDto;
import by.bsuir.andrei.diplom.model.User;
import by.bsuir.andrei.diplom.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<SpecializationDto>> getAllSpecializations() {
        List<SpecializationDto> specsDto = orderService.findAllSpecialities();
        return new ResponseEntity<>(specsDto, HttpStatus.OK);
    }

    @GetMapping("/house/all")
    public ResponseEntity<List<SpecializationDto>> getAllHouseSpecializations() {
        List<SpecializationDto> specsDto = orderService.findAllHouseCallSpecializations();
        return new ResponseEntity<>(specsDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DoctorTicketDto>> getAllTicketsBySpecialization(@PathVariable("id") Long specId) {
        List<DoctorTicketDto> ticketsDto = orderService.findAllTicketsBySpecializationAndStatus(specId);
        return new ResponseEntity<>(ticketsDto, HttpStatus.OK);
    }

    @GetMapping("/house/{id}")
    public ResponseEntity<List<DoctorTicketDto>> getAllHouseTicketsBySpecialization(@PathVariable("id") Long specId) {
        List<DoctorTicketDto> ticketsDto = orderService.findAllHouseCallTicketsBySpecializationAndStatus(specId);
        return new ResponseEntity<>(ticketsDto, HttpStatus.OK);
    }

    @PutMapping("/confirm/{ticketId}")
    public ResponseEntity<DoctorTicketDto> makeOrder(@PathVariable Long ticketId, Authentication auth) {
        User user = (User) auth.getPrincipal();
        DoctorTicketDto confirmedTicketDto = orderService.confirmTicket(ticketId, user);
        return new ResponseEntity<>(confirmedTicketDto, HttpStatus.OK);
    }
}