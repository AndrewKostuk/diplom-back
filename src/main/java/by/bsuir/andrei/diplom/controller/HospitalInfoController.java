package by.bsuir.andrei.diplom.controller;

import by.bsuir.andrei.diplom.model.HospitalInfo;
import by.bsuir.andrei.diplom.service.HospitalInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/contact")
public class HospitalInfoController {

    private final HospitalInfoService hospitalInfoService;

    @GetMapping("")
    public HospitalInfo getInfo() {
        return hospitalInfoService.getInfo();
    }
}
