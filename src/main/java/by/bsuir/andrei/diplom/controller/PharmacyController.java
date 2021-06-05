package by.bsuir.andrei.diplom.controller;

import by.bsuir.andrei.diplom.model.Pharmacy;
import by.bsuir.andrei.diplom.service.PharmacyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @GetMapping("")
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyService.getAllPharmacies();
    }
}
