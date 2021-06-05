package by.bsuir.andrei.diplom.service;

import by.bsuir.andrei.diplom.dto.DoctorTicketDto;
import by.bsuir.andrei.diplom.dto.SpecializationDto;
import by.bsuir.andrei.diplom.model.*;
import by.bsuir.andrei.diplom.repository.DoctorTicketRepository;
import by.bsuir.andrei.diplom.repository.SpecializationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final SpecializationRepository specializationRepository;
    private final DoctorTicketRepository doctorTicketRepository;

    public List<SpecializationDto> getAllSpecs() {
        List<Specialization> specializations = specializationRepository.findAll();
        return SpecializationDto.from(specializations);
    }

    public List<SpecializationDto> getAllHouseSpec() {
        List<Specialization> houseCallSpecializations = specializationRepository.findAllByDoctors_HouseCall(true);
        return SpecializationDto.from(houseCallSpecializations);
    }

    public List<DoctorTicketDto> getAllTicketsBySpec(Long specId) {
        List<DoctorTicket> tickets = doctorTicketRepository.findAllByDoctor_Specialization_IdAndStatusAndRoomNumberNot(specId, Status.FREE, 0);
        updateDoctorTickets(tickets);
        return DoctorTicketDto.from(tickets);
    }

    public List<DoctorTicketDto> getAllHouseTicketsBySpec(Long specId) {
        List<DoctorTicket> tickets = doctorTicketRepository.findAllByDoctor_Specialization_Id_AndDoctor_HouseCallAndStatus(specId, true, Status.FREE);
        updateDoctorTickets(tickets);
        return DoctorTicketDto.from(tickets);
    }

    public DoctorTicketDto confirmTicket(Long ticketId, User user) {
        DoctorTicket ticket = doctorTicketRepository.findById(ticketId).orElseThrow(() ->
                new IllegalStateException("ticket not found by id " + ticketId));
        ticket.setUser(user);
        ticket.setStatus(Status.RESERVED);
        return DoctorTicketDto.from(doctorTicketRepository.save(ticket));
    }

    public void cancelTicket(Long doctorTicketId) {
        doctorTicketRepository.cancelTicket(doctorTicketId);
    }

    public void updateDoctorTickets(List<DoctorTicket> tickets) {
        if (tickets != null && !tickets.isEmpty()) {
            for (BaseTicket ticket : tickets) {
                if (ticket.getDateTime().isBefore(LocalDateTime.now())) ticket.setStatus(Status.FINISHED);
            }
            doctorTicketRepository.saveAll(tickets);
        }
    }
}
