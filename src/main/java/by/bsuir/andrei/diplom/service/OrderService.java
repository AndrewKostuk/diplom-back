package by.bsuir.andrei.diplom.service;

import by.bsuir.andrei.diplom.dto.DoctorTicketDto;
import by.bsuir.andrei.diplom.dto.SpecializationDto;
import by.bsuir.andrei.diplom.model.DoctorTicket;
import by.bsuir.andrei.diplom.model.Specialization;
import by.bsuir.andrei.diplom.model.Status;
import by.bsuir.andrei.diplom.model.User;
import by.bsuir.andrei.diplom.repository.DoctorTicketRepository;
import by.bsuir.andrei.diplom.repository.SpecializationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final SpecializationRepository specializationRepository;
    private final DoctorTicketRepository doctorTicketRepository;

    public List<SpecializationDto> findAllSpecialities() {
        List<Specialization> specializations = specializationRepository.findAll();
        return SpecializationDto.from(specializations);
    }

    public List<SpecializationDto> findAllHouseCallSpecializations() {
        List<Specialization> houseCallSpecializations = specializationRepository.findAllByDoctors_HouseCall(true);
        return SpecializationDto.from(houseCallSpecializations);
    }

    public List<DoctorTicketDto> findAllTicketsBySpecializationAndStatus(Long specId) {
        //todo: update time of tickets
        List<DoctorTicket> tickets = doctorTicketRepository.findAllByDoctor_Specialization_IdAndStatusAndRoomNumberNot(specId, Status.FREE, 0);
        return DoctorTicketDto.from(tickets);
    }

    public List<DoctorTicketDto> findAllHouseCallTicketsBySpecializationAndStatus(Long specId) {
        List<DoctorTicket> tickets = doctorTicketRepository.findAllByDoctor_Specialization_Id_AndDoctor_HouseCallAndStatus(specId, true, Status.FREE);
        return DoctorTicketDto.from(tickets);
    }

    public DoctorTicketDto confirmTicket(Long ticketId, User user) {
        DoctorTicket ticket = doctorTicketRepository.findById(ticketId).orElseThrow(() ->
                new IllegalStateException("ticket not found by id " + ticketId));
        ticket.setUser(user);
        ticket.setStatus(Status.RESERVED);
        return DoctorTicketDto.from(doctorTicketRepository.save(ticket));
    }

    public void cancelDoctorTicket(Long doctorTicketId) {
        doctorTicketRepository.cancelTicket(doctorTicketId);
    }
}
