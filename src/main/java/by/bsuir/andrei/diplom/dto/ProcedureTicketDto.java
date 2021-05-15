package by.bsuir.andrei.diplom.dto;

import by.bsuir.andrei.diplom.model.BaseEntity;
import by.bsuir.andrei.diplom.model.ProcedureTicket;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProcedureTicketDto extends BaseEntity {

    private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm dd.MM.yyyy")
    private LocalDateTime dateTime;
    private Integer roomNumber;
    private String name;

    public static ProcedureTicketDto from(ProcedureTicket procedureTicket) {
        ProcedureTicketDto procedureTicketDto = new ProcedureTicketDto();
        procedureTicketDto.setId(procedureTicket.getId());
        procedureTicketDto.setUserId(procedureTicket.getUser() == null ? null : procedureTicket.getUser().getId());
        procedureTicketDto.setDateTime(procedureTicket.getDateTime());
        procedureTicketDto.setRoomNumber(procedureTicket.getRoomNumber());
        procedureTicketDto.setName(procedureTicket.getProcedure().getName());
        return procedureTicketDto;
    }

    public static List<ProcedureTicketDto> from(List<ProcedureTicket> tickets) {
        return tickets.stream().map(ProcedureTicketDto::from).collect(Collectors.toList());
    }
}
