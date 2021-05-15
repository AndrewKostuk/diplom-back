package by.bsuir.andrei.diplom.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends NamedEntity implements UserDetails {

    private String email;
    private String password;

    /**
     * we don't add an extra column to user table
     * to avoid null values if the user doesn't have an actual profile.
     * Instead of this we just create an extra table
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_user_profile",
            joinColumns =
                    {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "profile_id", referencedColumnName = "id")})
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<DoctorTicket> tickets = new ArrayList<>();

    private Boolean locked = false;
    private Boolean enabled = false;

    public User(String fistName, String lastName, String patronymic, String email, String password, Role role) {
        super(fistName, lastName, patronymic);
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userProfile=" + userProfile +
                ", role=" + role.toString() +
                ", locked=" + locked +
                ", enabled=" + enabled +
                '}';
    }
}
