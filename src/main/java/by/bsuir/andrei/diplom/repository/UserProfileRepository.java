package by.bsuir.andrei.diplom.repository;

import by.bsuir.andrei.diplom.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    // Optional<User> findUserProfileByUserId
}
