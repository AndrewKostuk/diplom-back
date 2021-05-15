package by.bsuir.andrei.diplom.service;

import by.bsuir.andrei.diplom.dto.*;
import by.bsuir.andrei.diplom.model.*;
import by.bsuir.andrei.diplom.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonalAccountService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final CityRepository cityRepository;
    private final DoctorTicketRepository doctorTicketRepository;
    private final AnalysisTicketRepository analysisTicketRepository;
    private final ProcedureTicketRepository procedureTicketRepository;

    public UserProfileDto getProfile(User user) {
        if (user.getUserProfile() == null) return null;
        else {
            user.getUserProfile().setUser(user);
            return UserProfileDto.from(user.getUserProfile());
        }
    }

    public UserProfileDto updateOrSaveProfile(UserProfileDto userProfileDto, User user) {
        UserProfile userProfile = UserProfileDto.to(userProfileDto);
        City city = cityRepository.findByName(userProfileDto.getCity()).orElseThrow(() ->
                new IllegalStateException("city not found by name " + userProfileDto.getCity()));
        userProfile.setCity(city);
        userProfile.setUser(user);
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
        UserProfileDto returnedUserProfileDto = UserProfileDto.from(savedUserProfile);
        user.setUserProfile(userProfile);
        userRepository.save(user);
        return returnedUserProfileDto;
    }

    public List<VisitDto> getUserHistory(User user) {
        List<DoctorTicket> tickets = doctorTicketRepository.findAllByUser_Id(user.getId());
        updateDoctorTickets(tickets);
        List<Visit> visits = tickets.stream().filter(doctorTicket -> doctorTicket.getDateTime().isBefore(LocalDateTime.now()) && doctorTicket.getVisit() != null).map(DoctorTicket::getVisit).collect(Collectors.toList());
        return VisitDto.from(visits);
    }

    public Map<String, Object> getPlannedVisits(User user) {
        List<DoctorTicket> doctorTickets = doctorTicketRepository.findAllByUser_Id(user.getId());
        updateDoctorTickets(doctorTickets);
        List<DoctorTicket> plannedDoctorTickets = doctorTickets.stream().filter(doctorTicket -> doctorTicket.getStatus().equals(Status.RESERVED) && doctorTicket.getRoomNumber() != 0).collect(Collectors.toList());
        List<DoctorTicket> homeTickets = doctorTickets.stream().filter(doctorTicket -> doctorTicket.getStatus().equals(Status.RESERVED) && doctorTicket.getRoomNumber() == 0).collect(Collectors.toList());

        List<AnalysisTicket> analysisTickets = analysisTicketRepository.findAllByUser_Id(user.getId());
        updateAnalysisTickets(analysisTickets);
        List<AnalysisTicket> plannedAnalysisTickets = analysisTickets.stream().filter(analysisTicket -> analysisTicket.getStatus().equals(Status.RESERVED)).collect(Collectors.toList());

        List<ProcedureTicket> procedureTickets = procedureTicketRepository.findAllByUser_Id(user.getId());
        updateProcedureTickets(procedureTickets);
        List<ProcedureTicket> plannedProcedureTickets = procedureTickets.stream().filter(procedureTicket -> procedureTicket.getStatus().equals(Status.RESERVED)).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("tickets", DoctorTicketDto.from(plannedDoctorTickets));
        response.put("home", DoctorTicketDto.from(homeTickets));
        response.put("analyses", AnalysisTicketDto.from(plannedAnalysisTickets));
        response.put("procedure", ProcedureTicketDto.from(plannedProcedureTickets));
        return response;
    }

    private void updateDoctorTickets(List<DoctorTicket> tickets) {
        if (tickets != null && !tickets.isEmpty()) {
            for (BaseTicket ticket : tickets) {
                if (ticket.getDateTime().isBefore(LocalDateTime.now())) ticket.setStatus(Status.FINISHED);
            }
            doctorTicketRepository.saveAll(tickets);
        }
    }

    private void updateAnalysisTickets(List<AnalysisTicket> tickets) {
        if (tickets != null && !tickets.isEmpty()) {
            for (BaseTicket ticket : tickets) {
                if (ticket.getDateTime().isBefore(LocalDateTime.now())) ticket.setStatus(Status.FINISHED);
            }
            analysisTicketRepository.saveAll(tickets);
        }
    }

    private void updateProcedureTickets(List<ProcedureTicket> tickets) {
        if (tickets != null && !tickets.isEmpty()) {
            for (BaseTicket ticket : tickets) {
                if (ticket.getDateTime().isBefore(LocalDateTime.now())) ticket.setStatus(Status.FINISHED);
            }
            procedureTicketRepository.saveAll(tickets);
        }
    }
}
