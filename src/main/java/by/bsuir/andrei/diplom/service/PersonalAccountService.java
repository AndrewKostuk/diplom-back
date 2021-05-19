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
        if (user.getUserProfile() == null) {
            UserProfileDto u = new UserProfileDto();
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setPatronymic(user.getPatronymic());
            return u;
        } else {
            user.getUserProfile().setUser(user);
            return UserProfileDto.from(user.getUserProfile());
        }
    }

    public UserProfileDto updateOrSaveProfile(UserProfileDto userProfileDto, User user) {
        UserProfile userProfile = UserProfileDto.to(userProfileDto);
        City city = cityRepository.findByName(userProfileDto.getCity()).orElse(null);
//        .orElseThrow(() ->
//                new IllegalStateException("city not found by name " + userProfileDto.getCity()));
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
        Long userId = user.getId();
        Map<String, Object> response = new HashMap<>();
        response.put("tickets", getActualDoctorTickets(userId));
        response.put("home", getActualHomeTickets(userId));
        response.put("analyses", getActualAnalysisTickets(userId));
        response.put("procedure", getActualProcedureTickets(userId));
        return response;
    }

    public List<DoctorTicketDto> getActualDoctorTickets(Long userId) {
        List<DoctorTicket> doctorTickets = doctorTicketRepository.findAllByUser_Id(userId);
        updateDoctorTickets(doctorTickets);
        List<DoctorTicket> plannedDoctorTickets = doctorTickets.stream().filter(doctorTicket -> doctorTicket.getStatus().equals(Status.RESERVED) && doctorTicket.getRoomNumber() != 0).collect(Collectors.toList());
        return DoctorTicketDto.from(plannedDoctorTickets);
    }

    public List<DoctorTicketDto> getActualHomeTickets(Long userId) {
        List<DoctorTicket> doctorTickets = doctorTicketRepository.findAllByUser_Id(userId);
        updateDoctorTickets(doctorTickets);
        List<DoctorTicket> homeTickets = doctorTickets.stream().filter(doctorTicket -> doctorTicket.getStatus().equals(Status.RESERVED) && doctorTicket.getRoomNumber() == 0).collect(Collectors.toList());
        return DoctorTicketDto.from(homeTickets);
    }

    public List<AnalysisTicketDto> getActualAnalysisTickets(Long userId) {
        List<AnalysisTicket> analysisTickets = analysisTicketRepository.findAllByUser_Id(userId);
        updateAnalysisTickets(analysisTickets);
        List<AnalysisTicket> plannedAnalysisTickets = analysisTickets.stream().filter(analysisTicket -> analysisTicket.getStatus().equals(Status.RESERVED)).collect(Collectors.toList());
        return AnalysisTicketDto.from(plannedAnalysisTickets);
    }


    public List<ProcedureTicketDto> getActualProcedureTickets(Long userId) {
        List<ProcedureTicket> procedureTickets = procedureTicketRepository.findAllByUser_Id(userId);
        updateProcedureTickets(procedureTickets);
        List<ProcedureTicket> plannedProcedureTickets = procedureTickets.stream().filter(procedureTicket -> procedureTicket.getStatus().equals(Status.RESERVED)).collect(Collectors.toList());
        return ProcedureTicketDto.from(plannedProcedureTickets);
    }


    public void updateDoctorTickets(List<DoctorTicket> tickets) {
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
