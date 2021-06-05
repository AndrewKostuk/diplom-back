package by.bsuir.andrei.diplom.service;

import by.bsuir.andrei.diplom.model.Pharmacy;
import by.bsuir.andrei.diplom.repository.PharmacyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    public List<Pharmacy> getAllPharmacies(){
        return pharmacyRepository.findAll();
    }
}
