package by.bsuir.andrei.diplom.dto;

import by.bsuir.andrei.diplom.model.BaseEntity;
import by.bsuir.andrei.diplom.model.Gender;
import by.bsuir.andrei.diplom.model.UserProfile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto extends BaseEntity {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;
    private String city;
    private String street;
    private Integer house;
    private Integer flat;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthDate;
    private Gender gender;

    public static UserProfileDto from(UserProfile userProfile) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setId(userProfile.getId());
        userProfileDto.setFirstName(userProfile.getUser().getFirstName());
        userProfileDto.setLastName(userProfile.getUser().getLastName());
        userProfileDto.setPatronymic(userProfile.getUser().getPatronymic());
        userProfileDto.setPhone(userProfile.getPhone());
        userProfileDto.setCity(userProfile.getCity().getName());
        userProfileDto.setStreet(userProfile.getStreet());
        userProfileDto.setHouse(userProfile.getHouse());
        userProfileDto.setFlat(userProfile.getFlat());
        userProfileDto.setBirthDate(userProfile.getBirthDate());
        userProfileDto.setGender(userProfile.getGender());
        return userProfileDto;
    }

    public static UserProfile to(UserProfileDto userProfileDto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userProfileDto.getId());
        userProfile.setPhone(userProfileDto.getPhone());
        userProfile.setStreet(userProfileDto.getStreet());
        userProfile.setHouse(userProfileDto.getHouse());
        userProfile.setFlat(userProfileDto.getFlat());
        userProfile.setBirthDate(userProfileDto.getBirthDate());
        userProfile.setGender(userProfileDto.getGender());
        return userProfile;
    }
}
