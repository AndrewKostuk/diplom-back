package by.bsuir.andrei.diplom.dto;

import by.bsuir.andrei.diplom.model.BaseEntity;
import by.bsuir.andrei.diplom.model.Doctor;
import by.bsuir.andrei.diplom.model.DoctorTicket;
import by.bsuir.andrei.diplom.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorTicketDto extends BaseEntity {
    private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm dd.MM.yyyy")
    private LocalDateTime dateTime;
    private Integer roomNumber;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String speciality;

    public static DoctorTicketDto from(DoctorTicket doctorTicket) {
        DoctorTicketDto doctorTicketDto = new DoctorTicketDto();
        doctorTicketDto.setId(doctorTicket.getId());
        doctorTicketDto.setUserId(doctorTicket.getUser() == null ? 0 : doctorTicket.getUser().getId());
        doctorTicketDto.setDateTime(doctorTicket.getDateTime());
        doctorTicketDto.setRoomNumber(doctorTicket.getRoomNumber());
        doctorTicketDto.setFirstName(doctorTicket.getDoctor().getFirstName());
        doctorTicketDto.setLastName(doctorTicket.getDoctor().getLastName());
        doctorTicketDto.setPatronymic(doctorTicket.getDoctor().getPatronymic());
        doctorTicketDto.setSpeciality(doctorTicket.getDoctor().getSpecialization().getName());
        return doctorTicketDto;
    }

    public static List<DoctorTicketDto> from(List<DoctorTicket> doctorTickets) {
        return doctorTickets.stream().map(DoctorTicketDto::from).collect(Collectors.toList());
    }

    public static DoctorTicket to(DoctorTicketDto doctorTicketDto) {
        DoctorTicket doctorTicket = new DoctorTicket();
        doctorTicket.setId(doctorTicketDto.getId());
        User user = new User();
        user.setId(doctorTicketDto.getUserId());
        doctorTicket.setUser(user);
        doctorTicket.setDateTime(doctorTicketDto.getDateTime());
        doctorTicket.setRoomNumber(doctorTicketDto.getRoomNumber());
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorTicketDto.getFirstName());
        doctor.setLastName(doctorTicketDto.getLastName());
        doctor.setPatronymic(doctorTicketDto.getPatronymic());
        doctorTicket.setDoctor(doctor);
        return doctorTicket;
    }
}