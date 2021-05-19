package by.bsuir.andrei.diplom.controller;

import by.bsuir.andrei.diplom.dto.UserProfileDto;
import by.bsuir.andrei.diplom.dto.VisitDto;
import by.bsuir.andrei.diplom.model.User;
import by.bsuir.andrei.diplom.service.PersonalAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/profile")
public class PersonalAccountController {

    private final PersonalAccountService personalAccountService;

    @GetMapping("")
    public ResponseEntity<UserProfileDto> getProfile(Authentication auth) {
        User user = (User) auth.getPrincipal();
        UserProfileDto userProfileDto = personalAccountService.getProfile(user);
        return new ResponseEntity<>(userProfileDto, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<UserProfileDto> editProfile(@Validated(UserProfileDto.Existing.class) @RequestBody UserProfileDto userProfileDto, Authentication auth) {
        User user = (User) auth.getPrincipal();
        UserProfileDto returnedUserProfileDto = personalAccountService.updateOrSaveProfile(userProfileDto, user);
        return new ResponseEntity<>(returnedUserProfileDto, HttpStatus.OK);
    }

    @GetMapping("/plannedVisits")
    public ResponseEntity<Map<String, Object>> getPlannedVisits(Authentication auth) {
        User user = (User) auth.getPrincipal();
        Map<String, Object> response = personalAccountService.getPlannedVisits(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/myHistory")
    public ResponseEntity<List<VisitDto>> getUserHistory(Authentication auth) {
        User user = (User) auth.getPrincipal();
        List<VisitDto> visits = personalAccountService.getUserHistory(user);
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }
}
