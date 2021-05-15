package by.bsuir.andrei.diplom.controller;

import by.bsuir.andrei.diplom.dto.LoginDto;
import by.bsuir.andrei.diplom.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public boolean login(@RequestBody LoginDto loginForm) {
        return loginService.findRegisteredUser(loginForm);
    }
}
