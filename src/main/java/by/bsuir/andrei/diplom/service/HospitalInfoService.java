package by.bsuir.andrei.diplom.service;

import by.bsuir.andrei.diplom.model.HospitalInfo;
import by.bsuir.andrei.diplom.repository.HospitalInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HospitalInfoService {
    private final HospitalInfoRepository hospitalInfoRepository;

    public HospitalInfo getInfo() {
        HospitalInfo response = hospitalInfoRepository.findById(1L).orElseThrow(() ->
                new IllegalStateException("hospital not found"));

        return response;
    }
}
