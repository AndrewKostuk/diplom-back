package by.bsuir.andrei.diplom.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class ProcedureTicket extends BaseTicket {

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private Procedure procedure;
}
