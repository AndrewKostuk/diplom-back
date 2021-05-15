package by.bsuir.andrei.diplom.dto;

import by.bsuir.andrei.diplom.model.AnalysisTicket;
import by.bsuir.andrei.diplom.model.BaseEntity;
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
public class AnalysisTicketDto extends BaseEntity {

    private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm dd.MM.yyyy")
    private LocalDateTime dateTime;
    private Integer roomNumber;
    private String name;

    public static AnalysisTicketDto from(AnalysisTicket analysisTicket) {
        AnalysisTicketDto analysisTicketDto = new AnalysisTicketDto();
        analysisTicketDto.setId(analysisTicket.getId());
        analysisTicketDto.setUserId(analysisTicket.getUser() == null ? null : analysisTicket.getUser().getId());
        analysisTicketDto.setDateTime(analysisTicket.getDateTime());
        analysisTicketDto.setRoomNumber(analysisTicket.getRoomNumber());
        analysisTicketDto.setName(analysisTicket.getAnalysis().getName());
        return analysisTicketDto;
    }

    public static List<AnalysisTicketDto> from(List<AnalysisTicket> tickets) {
        return tickets.stream().map(AnalysisTicketDto::from).collect(Collectors.toList());
    }
}
